package controller;

import java.io.IOException;
import java.sql.Date;

import dao.KeepTicketProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.KeepTicket;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Date now = new Date(System.currentTimeMillis());
		request.setAttribute("time", now);
		
		if(request.getSession().getAttribute("logonUser") == null) {
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}else {
		
			request.getRequestDispatcher("/WEB-INF/private/index.jsp").forward(request, response);
		}
	}
}
		

