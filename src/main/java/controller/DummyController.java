package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import dao.SpendLogProcessor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SpendLog;

@WebServlet("/dummy/make")
public class DummyController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String[] desc = new String[] {};
		SpendLogProcessor dao = new SpendLogProcessor();
		Random r = new Random();//
		try {
			for (int cnt = 1; cnt <= 30; cnt++) {

				SpendLog log = new SpendLog();
				log.setUserId("totoro");
				log.setAmt(r.nextInt(1, 200) * 500);
				log.setCategoryId(r.nextInt(1, 14));
				int month = r.nextInt(1, 12);
				int day = r.nextInt(1, 30);
				String dateStr = "2023-" + 
						String.format("%02d", month) + "-" + String.format("%02d", day);
				log.setSpendAt(Date.valueOf(dateStr));
				log.setUseDesc("테스트");

				dao.save(log);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}