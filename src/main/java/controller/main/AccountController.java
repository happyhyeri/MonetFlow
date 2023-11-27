package controller.main;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.CategoryProcessor;
import dao.SpendLogProcessor;
import dao.UserProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Category;
import vo.SpendLog;
import vo.User;

@WebServlet("/private/main/account")
public class AccountController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date now = new Date(System.currentTimeMillis());
		request.setAttribute("now", now);	
		
		
			
			Category category = new Category();
			CategoryProcessor ctp = new CategoryProcessor();
			List<Category> list;
			try {
				list = ctp.findAll();
				request.getSession().setAttribute("ctpList", list);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//spandlog= 유저의 지출내역관련 정보 있는것.
			

		request.getRequestDispatcher("/WEB-INF/private/main/account.jsp").forward(request, response);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date spendAt = Date.valueOf(request.getParameter("date"));
		int categoryId = Integer.parseInt(request.getParameter("category"));
		int amt = Integer.parseInt(request.getParameter("money"));
		String useDesc = request.getParameter("content");
		
				// SpendLog VO 에 데이터 옮겨담서
				// DAO로 던져서 save 시키고 그 결과값에 따른 사후 처리
		
		User one = (User) request.getSession().getAttribute("logonUser");
		
		SpendLog spendLog = new SpendLog(one.getId(),spendAt,amt,useDesc,categoryId );
		
		SpendLogProcessor spendLogProcessor = new SpendLogProcessor();
		try {
			
			boolean result = spendLogProcessor.save(spendLog);
			request.getSession().setAttribute("saveResult", result);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		request.getRequestDispatcher("/WEB-INF/private/main/account.jsp").forward(request, response);
	}


	}
