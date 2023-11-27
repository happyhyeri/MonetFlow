package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Avatar;
import vo.Category;
import vo.SpendLog;
import vo.User;

public class SpendLogProcessor {

	public boolean save(SpendLog one) throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) { // 이렇게 함으로써 close를 안해줘도 됨.

			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "insert into Spend_Logs values (SPEND_LOGS_SEQ.NEXTVAL,?,?,?,?,?)";
			System.out.println("sql = " + sql);
			PreparedStatement pstmst = conn.prepareStatement(sql);

			pstmst.setString(1, one.getUserId());
			pstmst.setDate(2, one.getSpendAt());
			pstmst.setInt(3, one.getAmt());
			pstmst.setString(4, one.getUseDesc());
			pstmst.setInt(5, one.getCategoryId());

			int n = pstmst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. ==> 성공하면 1을 도출하고 실패하면 erorr 등장.
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	//회원 가계부 기록 뽑아내기
	public List<SpendLog> findByUser(String key) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT a.*, b.name FROM Spend_Logs a LEFT JOIN categorys b ON a.category_id = b.id WHERE USER_id=? order by spend_at desc";// pk로
																																						// 찾는
																																						// SELELCT작업은
																																						// 있거나
																																						// 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<SpendLog>();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			while (rs.next()) {
				SpendLog spandLog = new SpendLog();
				spandLog.setNo(rs.getInt("no")); // rs.getInt("code")
				spandLog.setUserId(rs.getString("user_id"));
				spandLog.setSpendAt(rs.getDate("spend_at"));
				spandLog.setAmt(rs.getInt("atm"));
				spandLog.setUseDesc(rs.getString("use_desc"));
				spandLog.setCategoryId(rs.getInt("category_id"));

				Category a = new Category();

				a.setName(rs.getString("name"));

				spandLog.setCategory(a);

				list.add(spandLog);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteByNo(int No) // 객체 자체를 저장해주기 위해서
			throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) { // 이렇게 함으로써 close를 안해줘도 됨.

			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "delete from spend_Logs where no = ?";

			System.out.println("sql = " + sql);

			PreparedStatement pstmst = conn.prepareStatement(sql);
			pstmst.setInt(1, No);
			int n = pstmst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. ==> 성공하면 1을 도출하고 실패하면 erorr 등장.
			if (n >= 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public SpendLog findByNo(int no) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT * FROM Spend_Logs WHERE no=? ";// pk로 찾는 SELELCT작업은 있거나 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();

			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			SpendLog spandLog = new SpendLog();

			if (rs.next()) {
				spandLog.setNo(rs.getInt("no")); // rs.getInt("code")
				spandLog.setUserId(rs.getString("user_id"));
				spandLog.setSpendAt(rs.getDate("spend_at"));
				spandLog.setAmt(rs.getInt("atm"));
				spandLog.setUseDesc(rs.getString("use_desc"));
				spandLog.setCategoryId(rs.getInt("category_id"));
			}
			return spandLog;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<SpendLog> findByUserAndSpendAt(String key, Date begin, Date end) throws ClassNotFoundException { // 객체를
																													// 반환시켜줌
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {
			
				   String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID";
					sql += " WHERE USER_ID=? AND SPEND_AT BETWEEN ? AND ?";
					sql += " ORDER BY SPEND_AT desc, NO ASC";	   
				   	
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);
			pstmt.setDate(2, begin);
			pstmt.setDate(3, end);

			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<SpendLog>();

			while (rs.next()) {
				SpendLog spandLog = new SpendLog();
				spandLog.setNo(rs.getInt("no")); // rs.getInt("code")
				spandLog.setUserId(rs.getString("user_id"));
				spandLog.setSpendAt(rs.getDate("spend_at"));
				spandLog.setAmt(rs.getInt("atm"));
				spandLog.setUseDesc(rs.getString("use_desc"));
				spandLog.setCategoryId(rs.getInt("category_id"));

				Category a = new Category();

				a.setName(rs.getString("name"));

				spandLog.setCategory(a);

				list.add(spandLog);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<SpendLog> findByUserIdAndConditions(String key, Date begin, Date end, String sort) throws ClassNotFoundException { // 객체를
		// 반환시켜줌
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID";
			sql += " WHERE USER_ID=? AND SPEND_AT BETWEEN ? AND ?";
				
			switch(sort) {
			case "atm"-> sql += " Order by atm desc, spend_at desc";
			case "spendAt" -> sql +=" Order by spend_at desc, no desc ";
			}

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);
			pstmt.setDate(2, begin);
			pstmt.setDate(3, end);
			

			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<SpendLog>();

			while (rs.next()) {
				SpendLog spandLog = new SpendLog();
				spandLog.setNo(rs.getInt("no")); // rs.getInt("code")
				spandLog.setUserId(rs.getString("user_id"));
				spandLog.setSpendAt(rs.getDate("spend_at"));
				spandLog.setAmt(rs.getInt("atm"));
				spandLog.setUseDesc(rs.getString("use_desc"));
				spandLog.setCategoryId(rs.getInt("category_id"));

				Category a = new Category();

				a.setName(rs.getString("name"));

				spandLog.setCategory(a);

				list.add(spandLog);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	//카테고리 리스트 포함시킨것. (카테고리 선택한 것 마다 값을 넣어주기 위해 반복문 돌림. 이용자가 몇개 넣었는지 max값을 모르니까)
	public List<SpendLog> findByUserIdAndConditionsWithArray(String key, Date begin, Date end, String sort, int[] ids) throws ClassNotFoundException { // 객체를
		// 반환시켜줌
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			String sql = "SELECT s.*, c.NAME FROM SPEND_LOGS s JOIN CATEGORYS c ON s.CATEGORY_ID = c.ID";
			sql += " WHERE USER_ID=? AND SPEND_AT BETWEEN ? AND ?";

			if ( ids.length > 0) {
				sql += " AND CATEGORY_ID IN (";
				for (int i = 0; i < ids.length; i++) {
					sql += "?";
					if (i != ids.length - 1) {
						sql += ",";
					}
				}
				sql += ")";
			}
			
			switch(sort) {
			case "atm"-> sql += " Order by atm desc, spend_at desc";
			case "spendAt" -> sql +=" Order by spend_at desc, no desc ";
			}

			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, key);
			pstmt.setDate(2, begin);
			pstmt.setDate(3, end);
			if (ids.length > 0) {
				int idx= 4;
				for(int v : ids) {
					pstmt.setInt(idx++ , v);
				}
			}
			ResultSet rs = pstmt.executeQuery();
			List<SpendLog> list = new ArrayList<SpendLog>();

			while (rs.next()) {
				SpendLog spandLog = new SpendLog();
				spandLog.setNo(rs.getInt("no")); // rs.getInt("code")
				spandLog.setUserId(rs.getString("user_id"));
				spandLog.setSpendAt(rs.getDate("spend_at"));
				spandLog.setAmt(rs.getInt("atm"));
				spandLog.setUseDesc(rs.getString("use_desc"));
				spandLog.setCategoryId(rs.getInt("category_id"));

				Category a = new Category();

				a.setName(rs.getString("name"));

				spandLog.setCategory(a);

				list.add(spandLog);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
