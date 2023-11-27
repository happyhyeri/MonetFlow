package controller.menu;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

import dao.AvatarProcessor;
import dao.KeepTicketProcessor;
import dao.UserProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Avatar;
import vo.KeepTicket;
import vo.User;
import vo.UserWithAvatar;

@WebServlet("/menu/loginResult")
public class LoginResultController extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPassword = request.getParameter("loginPassword");
		
		//로그인 유지 하시겠습니까? 체크박스 내용
		String keep = request.getParameter("keep");
		
		try {
			UserProcessor userProcessor = new UserProcessor();
			User found = userProcessor.findUserWithAvatarById(loginId);
			
			if(found!=null && found.getPassword().equals(loginPassword)) {
				
				request.getSession().setAttribute("logonUser", found);
		
				
				if(keep != null) { 	//사용자가 체크박스를 선택해서 요청을 보낸경우. 
									//랜덤 코드 만들어서
									//디비에 저장하고,
									//그 코드 값을 cookie에 담아서 send
					
					//KeepTicket:쿠키 저장해서 데이터 베이스에 보내기 위한 작업중.
					String code = UUID.randomUUID().toString();
					String userId = loginId;
					Date expired_at = new Date(System.currentTimeMillis()+ 1000L*60*60*24*24);
					//int형식의 범위를 넘기 때문에 long타입으로 바꿔줘야함.  
					
					KeepTicket ticket = new KeepTicket (code, userId, expired_at);
					
					//데이터베이스 저장중..
					KeepTicketProcessor keepTicketProcessor = new KeepTicketProcessor();
					
					keepTicketProcessor.save(ticket);
					
					//쿠키 생성및 설정
					Cookie cookie = new Cookie("ticketCode",code);
					cookie.setPath(request.getServletContext().getContextPath());
					cookie.setMaxAge(60*60*24*30);
					
					response.addCookie(cookie);
					
					
				}
				
				response.sendRedirect(request.getServletContext().getContextPath()+"/index");
			
				
				
			}else {
				request.setAttribute("error", true);
				request.getRequestDispatcher("/WEB-INF/menu/login.jsp").forward(request, response);
			}
			

			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String loginId = request.getParameter("loginId");
//		String loginPassword = request.getParameter("loginPassword");
//		
//		UserProcessor userProcessor = new UserProcessor();
//		
//		try {
//			User found = userProcessor.findByID(loginId);
//			boolean loginResult;
//			
//			if (found == null || !found.getPassword().equals(loginPassword)) {
//				loginResult = false;
//				request.setAttribute("loginResult", false);
//				
//			}else {
//				loginResult = true;
//				 userProcessor.save(found);
//				 request.setAttribute("loginResult", true);
//				 request.setAttribute("logonUser" , found);
//				 response.sendRedirect(request.getServletContext().getContextPath()+"/index");
//			}
//			
//				
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		request.getRequestDispatcher("/WEB-INF/menu/loginResult.jsp").forward(request, response);
//	}
//}
