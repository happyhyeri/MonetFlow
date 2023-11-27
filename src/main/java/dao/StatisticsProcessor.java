package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Category;
import vo.SpendLog;
import vo.Statistics;

public class StatisticsProcessor {
	// 개인 월별 지출금액 합계 
	public List<Statistics> MonthlySumFindByUser(String key) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "select to_char(spend_at,'yyyy-mm') as month, sum(atm) as sum ";
				   sql += "from(select s.*,u.gender from spend_logs s left join users u on s.user_id = u.id where s.user_id=?) ";
                   sql += "group by to_char(spend_at,'yyyy-mm') order by month ASC" ;
																																						// 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			List<Statistics> list = new ArrayList<Statistics>();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			while (rs.next()) {
				Statistics statistics = new Statistics();
				statistics.setSum(rs.getInt("sum")); // rs.getInt("code")
				statistics.setMonth(rs.getString("month"));
	

				list.add(statistics);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	// 개인 월별 카테고리별 지출금액 합계 
	public List<Statistics> MonthlyCaterotySumFindByUser (String id , String month) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "select category_id as category, to_char(spend_at,'yyyy-mm')as month , sum(atm) as sum ";
			       sql += "from(select s.*,u.gender from spend_logs s left join users u on s.user_id = u.id where s.user_id= ?) ";
			       sql += "where to_char(spend_at,'yyyy-mm')= ? ";
				   sql += "group by category_id , to_char(spend_at,'yyyy-mm') order by month ASC";

																																						// 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, month);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			List<Statistics> list = new ArrayList<Statistics>();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			while (rs.next()) {
				Statistics statistics = new Statistics();
				statistics.setCategory(rs.getInt("category"));
				statistics.setSum(rs.getInt("sum")); // rs.getInt("code")
				statistics.setMonth(rs.getString("month"));

				list.add(statistics);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


}
