package controller.main;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.CategoryProcessor;
import dao.SpendLogProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SpendLog;
import vo.User;

@WebServlet("/private/main/accountList")
public class AccountListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//---------------------------------------삭제 성공여부 알려주기
		
		User one = (User) request.getSession().getAttribute("logonUser");
		// 삭제 성공과 실패일 경우 다르게 표현하기 위해서
		String error = request.getParameter("error");
		String success = request.getParameter("success");

		if (error != null) { // error 값이 있다는 것
			request.setAttribute("deleteResult", false);
		} else if (success != null) {
			request.setAttribute("deleteResult", true);
		}
		// ----------------------------------------목록 선택 검색 (날짜) 및 목록 나오게 하기
		String userId = one.getId();

		String begin = request.getParameter("begin");
		String end = request.getParameter("end");
		String sort = request.getParameter("sort");
		String[] categoryIds = request.getParameterValues("categoryId");
		
		
		LocalDate now = LocalDate.now();
		
		SpendLogProcessor spendLogProcessor = new SpendLogProcessor();
		CategoryProcessor categoryProcessor = new CategoryProcessor();
		try {
			List<SpendLog> spendLogs = spendLogProcessor.findByUser(one.getId());
			request.setAttribute("spendLogs", spendLogs);
			
			sort = (sort==null|| sort.equals(""))? "SpendAt":sort;
			
			Date endDate = (end==null||end.equals(""))? Date.valueOf(now) :	Date.valueOf(end); 
			
			Date beginDate = (begin == null || begin.equals(""))?
					Date.valueOf(now.minusYears(1)) : Date.valueOf(begin);
			spendLogs = spendLogProcessor.findByUserIdAndConditions(userId, beginDate, endDate, sort);
			request.setAttribute("spendLogs", spendLogs);
			
			categoryIds = categoryIds == null ? new String[0] : categoryIds;
			
			int[] iCategoryIds = new int[categoryIds.length];
			for(int i=0; i<categoryIds.length; i++) {
				iCategoryIds[i] = Integer.parseInt(categoryIds[i]);
			}
			
			
			//------------------------------합계내기 
			int total = 0;
			if(spendLogs!=null) {
			for (SpendLog l : spendLogs) {
				total += l.getAmt();
				}
			}
			request.setAttribute("total", total);
			
			request.setAttribute("categoryList", categoryProcessor.findAll());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/private/main/accountList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] v = request.getParameterValues("no");

		User one = (User) request.getSession().getAttribute("logonUser");
		SpendLogProcessor spendLogProcessor = new SpendLogProcessor();

		boolean deleteResult = false;
		if (v != null) {
			int[] values = new int[v.length];

			for (int i = 0; i < v.length; i++) {
				int sum = 0;
				values[i] = Integer.parseInt(v[i]);

				SpendLog log;
				try {
					log = spendLogProcessor.findByNo(values[i]);

					if (one.getId().equals(log.getUserId())) {
						spendLogProcessor.deleteByNo(values[i]);
						deleteResult = true;
						request.setAttribute("deleteResult", deleteResult);

					}

				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}

			}
		}

		if (deleteResult) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/private/main/accountList?success");
		} else if (deleteResult = false) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/private/main/accountList?error");
		} else {

			request.getRequestDispatcher("/WEB-INF/private/main/accountList.jsp").forward(request, response);

		}

	}
}
