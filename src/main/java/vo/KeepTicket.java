package vo;

import java.sql.Date;

public class KeepTicket {
	private String code;
	private String userId;
	private Date expired_at;
	
	public KeepTicket() {
		super();
	}

	public KeepTicket(String code, String userId, Date expired_at) {
		super();
		this.code = code;
		this.userId = userId;
		this.expired_at = expired_at;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getExpired_at() {
		return expired_at;
	}

	public void setExpired_at(Date expired_at) {
		this.expired_at = expired_at;
	}
	
	
	
	
	
	
	
}
