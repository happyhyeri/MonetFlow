package filter;

import java.io.IOException;
import java.sql.Date;

import dao.AvatarProcessor;
import dao.KeepTicketProcessor;
import dao.UserProcessor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Avatar;
import vo.KeepTicket;
import vo.User;

//별도의 로그인 없이 쿠키로 로그인 유지를 위해서 작동시킴.

@WebFilter({ "/*" })//하나의 값만 넣을때는 'urlPatterns =' 이거 안 적고도 가능함. 
public class TicketCodeCookieFilterh extends HttpFilter{
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 쿠키가 있다면 전처리 후
		// 이 요청에 ticketCode 쿠키가 있는지를 확인.
				Cookie found = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null && cookies.length > 0) {
					for (Cookie one : cookies) {
						if (one.getName().equals("ticketCode")) {
							found = one;
							break;
						}
					}
				}
				// ========================================================
				// 쿠키 생성시 new cookie("name",value)형식임.
				// 있으면, 값 찾아서 유저 찾고 세션에 올려서 통과
				if (found != null) { //위조방지때문에 
					String code = found.getValue(); 
					KeepTicketProcessor keepTicketProcessor = new KeepTicketProcessor();
					try {
						KeepTicket foundTicket =  keepTicketProcessor.findByCode(code);
						Date now = new Date(System.currentTimeMillis());
						
						//System.out.println(foundTicket.getExpired_at().before(now));--false뜸.
						
						//쿠키 있는지 찾아서 있으면 KeepTicket객체에 저장된 userId 뽑아내서 그걸로 user객체 찾아서 
						//세션에 세팅하는 작업
						if(foundTicket != null && foundTicket.getExpired_at().after(now)) {
							String userId = foundTicket.getUserId();
							UserProcessor userProcessor = new UserProcessor();
							User foundUser = userProcessor.findUserWithAvatarById(userId);
							request.getSession().setAttribute("logonUser", foundUser);	
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 100% 통과를 시키는 필터
				// 이것이 없으면 응답을 하지 않고 끝내버린것임. 
				chain.doFilter(request, response);
			}
		}