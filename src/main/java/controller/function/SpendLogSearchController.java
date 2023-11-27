package controller.function;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.xalan.templates.ElemCallTemplate;

import dao.CategoryProcessor;
import dao.SpendLogProcessor;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SpendLog;
import vo.User;

@WebServlet("/private/main/search")
public class SpendLogSearchController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest reqeust, HttpServletResponse response) throws ServletException, IOException {
		String userId = ((User)reqeust.getSession().getAttribute("logonUser")).getId();

		String begin = reqeust.getParameter("begin");
		String end = reqeust.getParameter("end");
		String sort = reqeust.getParameter("sort");
		

		LocalDate now = LocalDate.now();
		
		//우선순위 캐스팅을 먼저 시켜주기 위해 --원래는 캐스팅이 가장 나중에 됨.
		SpendLogProcessor spendLogProcessor = new SpendLogProcessor();
		CategoryProcessor categoryProcessor = new CategoryProcessor();
		
		List<SpendLog> list = null;
		sort = (sort==null|| sort.equals(""))? "SpendAt":sort;
		
		Date endDate = (end==null||end.equals(""))? Date.valueOf(now) :	Date.valueOf(end); 
		
		Date beginDate = (begin == null || begin.equals(""))?
				Date.valueOf(now.minusMonths(1)) : Date.valueOf(begin);
		try {
			list = spendLogProcessor.findByUserIdAndConditions(userId, beginDate, endDate, sort);
		
			reqeust.setAttribute("spendLogs", list);
			int total = 0;
			if(list!=null) {
			for (SpendLog one : list) {
				total += one.getAmt();
				}
			}
			reqeust.setAttribute("total", total);
			
			reqeust.setAttribute("categoryList", categoryProcessor.findAll());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
			reqeust.getRequestDispatcher("/WEB-INF/private/main/accountList.jsp").forward(reqeust, response);
	}
	
}