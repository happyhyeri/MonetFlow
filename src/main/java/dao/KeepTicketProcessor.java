package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.KeepTicket;

public class KeepTicketProcessor {

	public boolean save(KeepTicket one) throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) { // 이렇게 함으로써 close를 안해줘도 됨.

			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "insert into keep_tickets values (?,?,?)";
			System.out.println("sql = " + sql);
			PreparedStatement pstmst = conn.prepareStatement(sql);

			pstmst.setString(1, one.getCode());
			pstmst.setString(2, one.getUserId());
			pstmst.setDate(3, one.getExpired_at());

			int n = pstmst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. ==> 성공하면 1을 도출하고 실패하면 erorr 등장.
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	
	public KeepTicket findByCode(String ticketCode) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT * FROM keep_tickets WHERE code=?";// pk로 찾는 SELELCT작업은 있거나 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ticketCode);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			if (rs.next()) {

				String code = rs.getString(1); // rs.getInt("id");
				String userId = rs.getString(2); // rs.getString("alt");
				Date expired_at = rs.getDate(3); // rs.getInt("imageUrl");

				return new KeepTicket(code, userId, expired_at);

			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
