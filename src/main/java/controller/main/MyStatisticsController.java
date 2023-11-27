package controller.main;

import java.io.IOException;
import java.util.List;

import dao.SpendLogProcessor;
import dao.StatisticsProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SpendLog;
import vo.Statistics;
import vo.User;

@WebServlet("/private/main/myStatistics")
public class MyStatisticsController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User one = (User) request.getSession().getAttribute("logonUser");
		StatisticsProcessor statisticsProcessor = new StatisticsProcessor();
		try {
			List<Statistics> listM = statisticsProcessor.MonthlySumFindByUser(one.getId());
			request.setAttribute("MonthlyLists", listM);

			for (int i = 0; i < listM.size(); i++) {
				Statistics statistics = new Statistics();
				String yearMonth = statistics.getMonth();
				request.setAttribute("yearMonth", yearMonth);
				List<Statistics> listMC = statisticsProcessor.MonthlyCaterotySumFindByUser(one.getId(), yearMonth);
				request.setAttribute("MonthlyCategoryList", listMC);

				SpendLogProcessor spendLogProcessor = new SpendLogProcessor();
				SpendLog spendLog = new SpendLog();
				for (Statistics s : listMC) {
					int c = s.getCategory(); // 여기서 category - int형 categoryId임.
					spendLog = spendLogProcessor.findByNo(c);
					String GetCategoryName = spendLog.getCategory().getName();

					request.setAttribute("GetCategoryName", GetCategoryName);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/private/main/myStatistics.jsp").forward(request, response);
	}
}
