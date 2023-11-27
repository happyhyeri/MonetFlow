package controller.menu;

import java.io.IOException;
import java.util.List;

import dao.AvatarProcessor;
import dao.UserProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Avatar;
import vo.User;

@WebServlet("/menu/registerResult")
public class RegisterResultController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String avatarId = request.getParameter("avatarId");
		
		int birthYear = Integer.parseInt(birth);
		
		if (id == null || password == null || nickname == null || gender == null || avatarId == null) {
			// 파라미터 유효성 검사
		}
		User one = new User(id, password, birthYear, gender, nickname, avatarId);
		
		
		UserProcessor userProcessor = new UserProcessor();
			User found = userProcessor.findByID(id);
			if (found == null) {	// 이 아이디로 등록된 유저가 없다는 상황
				
				userProcessor.save(one);
				request.setAttribute("saveUser",one);
				request.setAttribute("result", 1);
				
				request.getRequestDispatcher("/WEB-INF/menu/registerResult.jsp").forward(request, response);
			} else {	// 이미 이 아이디가 있다면..?
				request.setAttribute("error", true);
				request.setAttribute("tempUser", found);
//				
				AvatarProcessor avatarProcessor = new AvatarProcessor();
				List<Avatar> avatars = avatarProcessor.findAll();
				request.setAttribute("avatars", avatars);
				request.getRequestDispatcher("/WEB-INF/menu/register.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", -1);
			request.getRequestDispatcher("/WEB-INF/menu/registerResult.jsp").forward(request, response);
		}

	}

}	
			
			
	