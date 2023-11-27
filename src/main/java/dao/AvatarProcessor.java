package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Avatar;
import vo.User;

public class AvatarProcessor {
	
	// 1.전체 찾기
	public List<Avatar> findAll() throws ClassNotFoundException { // 객체를 반환시켜줌 (1행을 갖고오니까)

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT * FROM AVATARS"; // pk로 찾는 SELELCT작업은 있거나 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			List<Avatar> list = new ArrayList<>(); // ==> 1. 리스트 만들고
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			while (rs.next()) { // ==>2. 반복문 돌면서 빼오고
				String id = rs.getString(1); // rs.getInt("id");
				String alt = rs.getString(2); // rs.getString("alt");
				String imageUrl = rs.getString(3); // rs.getInt("imageUrl");
				
				Avatar one = new Avatar(id, alt, imageUrl); // ==> 3.객체 생성해서 리스트에 넣기
				list.add(one);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 2. 아바타 객체 찾기
	public Avatar findBykey(String avatarId) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)
	

		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT * FROM AVATARS WHERE id=?";// pk로 찾는 SELELCT작업은 있거나 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, avatarId);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			if (rs.next()) {
				String id = rs.getString(1); // rs.getInt("id");
				String alt = rs.getString(2); // rs.getString("alt");
				String imageUrl = rs.getString(3); // rs.getInt("imageUrl");
				
				return new Avatar(id, alt, imageUrl); 
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	


}
