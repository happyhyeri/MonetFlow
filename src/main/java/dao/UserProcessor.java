package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Avatar;
import vo.User;
import vo.UserWithAvatar;



public class UserProcessor {
	//1.유저 저장하기
	public boolean save(User one) 
			throws ClassNotFoundException {
		boolean result = false;
		// 1. 데이터 베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) { // 이렇게 함으로써 close를 안해줘도 됨.

			// 2. 필요한 작업요청을 전송하고 응답을 받으면 됨.
			String sql = "insert into users values (?,?,?,?,?,?)";
			System.out.println("sql = " + sql);
			PreparedStatement pstmst = conn.prepareStatement(sql);

			pstmst.setString(1, one.getId());
			pstmst.setString(2, one.getPassword());
			pstmst.setInt(3, one.getBirth());
			pstmst.setString(4, one.getGender());
			pstmst.setString(5, one.getNickname());
			pstmst.setString(6, one.getAvatarId());
			
			int n = pstmst.executeUpdate(); // 요청 전송하고 DB에서 응답을 받아옴. ==> 성공하면 1을 도출하고 실패하면 erorr 등장.
			if (n == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
		//2.
		public User findByID(String key) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)
		
		// 1. DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
				"oracle");) {

			// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
			String sql = "SELECT * FROM USERS WHERE id=?";// pk로 찾는 SELELCT작업은 있거나 없거나
			// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			
			pstmt.setString(1, key);

			// 3. 실행후 응답 받기
			ResultSet rs = pstmt.executeQuery();
			// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
			// boolean r = rs.next();
			if (rs.next()) {
				
				String id = rs.getString(1); // rs.getInt("id");
				String password = rs.getString(2); // rs.getString("alt");
				int birth = rs.getInt(3); // rs.getInt("imageUrl");
				String gender = rs.getString(4); // rs.getInt("id");
				String nickname = rs.getString(5); // rs.getInt("id");
				String avatarId = rs.getString(6); // rs.getInt("id");
				
				return new User(id, password, birth,gender,nickname,avatarId); 
				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
}

		public boolean update(User one) throws ClassNotFoundException {
			boolean result = false;
			// 0.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
					"oracle");) {
				String sql = "UPDATE users SET password=? ,birth=?, gender = ?, nickname=?, avartarid=? WHERE ID=?";
				PreparedStatement pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, one.getPassword());
				pstmt.setInt(2, one.getBirth());
				pstmt.setString(3, one.getGender());
				pstmt.setString(4, one.getNickname());
				pstmt.setString(5, one.getAvatarId());
				pstmt.setString(6, one.getId());
				
				int r = pstmt.executeUpdate();

				if (r == 1) {
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
	
		//2.
				public User findUserWithAvatarById(String key) throws ClassNotFoundException { // 객체를 반환시켜줌 (1행 전체를 찾아주니까)
				
				// 1. DB 연결
				Class.forName("oracle.jdbc.driver.OracleDriver");
				try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@43.201.95.196:1521:xe", "MONEYFLOW",
						"oracle");) {

					// 2. 처리할 쿼리 작성 후 전송가능한 객체로 준비
					String sql = "SELECT u.*,a.alt,a.image_url FROM USERS U  LEFT JOIN AVATARS A ON U.AVATAR_ID = A.ID WHERE U.ID=?";// pk로 찾는 SELELCT작업은 있거나 없거나
					// PK의 경우에는 하나 또는 없음이 나오기 때문에 if문 사용
					PreparedStatement pstmt = conn.prepareStatement(sql);			
					
					pstmt.setString(1, key);

					// 3. 실행후 응답 받기
					ResultSet rs = pstmt.executeQuery();
					// ResultSet 사용하는 거는.. Iterator 쓰는 방법이랑 비슷함.
					// boolean r = rs.next();
					if (rs.next()) {
						User user = new User();
						user.setId( rs.getString("id")); // rs.getInt("code")
						user.setPassword(rs.getString("password"));
						user.setNickname(rs.getString("nickname"));
						user.setBirth(rs.getInt("birth"));
						user.setAvatarId(rs.getString("avatar_id"));
						user.setGender(rs.getString("gender"));
						
						Avatar a = new Avatar();
							a.setId(rs.getString("avatar_id"));
							a.setAlt(rs.getString("alt"));
							a.setImageUrl(rs.getString("image_url"));
						
						user.setAvatar(a);
						
						return user;
						
					} else {
						return null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}