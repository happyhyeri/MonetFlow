package controller.menu;

import java.io.IOException;
import java.util.List;

import dao.AvatarProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Avatar;

	@WebServlet("/menu/register")
	public class RegisterController  extends HttpServlet {

		@Override
		protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			AvatarProcessor avatarProcessor = new AvatarProcessor();
			
			try {
				//아바타 등록기록 전부 가져오기
				List<Avatar> avatars = avatarProcessor.findAll();
				System.out.println(avatars.size());
				request.setAttribute("avatars", avatars);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/menu/register.jsp").forward(request, response);
		}
	}

