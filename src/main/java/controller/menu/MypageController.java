package controller.menu;

import java.io.IOException;
import java.util.List;

import dao.AvatarProcessor;
import dao.UserProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Avatar;
import vo.User;

@WebServlet("/private/myPage")
public class MypageController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User one = (User) request.getSession().getAttribute("logonUser");
		UserProcessor userProcessor = new UserProcessor();
		try {
			User found = userProcessor.findUserWithAvatarById(one.getId());
			request.getSession().setAttribute("logonUser", found);	
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		  
	   request.getRequestDispatcher("/WEB-INF/private/myPage.jsp").forward(request, response);
			
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User one = (User) request.getSession().getAttribute("logonUser");
		UserProcessor userProcessor = new UserProcessor();
		
		User found;
		try {
			found = userProcessor.findUserWithAvatarById(one.getId());
			request.getSession().setAttribute("logonUser", found);	
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String nickname = request.getParameter("nickname");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		String avatarId = request.getParameter("avrtarId");
		
		int birthYear = Integer.parseInt(birth);
		
		User user = new User(one.getId(), one.getPassword(), birthYear, gender, nickname, avatarId);
		
		
		Boolean result;
				
			try {
				result = userProcessor.update(user);
				request.getSession().setAttribute("result", result);	
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		request.getRequestDispatcher("/WEB-INF/private/index.jsp").forward(request, response);
		
	}
}
