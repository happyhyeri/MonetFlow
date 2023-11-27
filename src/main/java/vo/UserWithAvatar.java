package vo;

public class UserWithAvatar {
	private User user;
	private Avatar avatar;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Avatar getAvatar() {
		return avatar;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	
	public UserWithAvatar() {
		super();
	}
	
	public UserWithAvatar(User user, Avatar avatar) {
		super();
		this.user = user;
		this.avatar = avatar;
	}
	
	
	
}
