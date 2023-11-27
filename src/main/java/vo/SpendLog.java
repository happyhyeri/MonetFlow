package vo;

import java.sql.Date;

public class SpendLog {
	private int no;
	private String userId;
	private Date spendAt;
	private int amt;
	private String useDesc;
	private int categoryId;
	
	//join해서 가져온 category 저장
	private Category category;

	public SpendLog() {
		super();
	}

	
	
	public SpendLog(String userId, Date spendAt, int amt, String useDesc, int categoryId) {
		super();
		this.userId = userId;
		this.spendAt = spendAt;
		this.amt = amt;
		this.useDesc = useDesc;
		this.categoryId = categoryId;
	}



	public SpendLog(int no, String userId, Date spendAt, int amt, String useDesc, int categoryId) {
		super();
		this.no = no;
		this.userId = userId;
		this.spendAt = spendAt;
		this.amt = amt;
		this.useDesc = useDesc;
		this.categoryId = categoryId;
	}

	public SpendLog(int no, String userId, Date spendAt, int amt, String useDesc, int categoryId, Category category) {
		super();
		this.no = no;
		this.userId = userId;
		this.spendAt = spendAt;
		this.amt = amt;
		this.useDesc = useDesc;
		this.categoryId = categoryId;
		this.category = category;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getSpendAt() {
		return spendAt;
	}

	public void setSpendAt(Date spendAt) {
		this.spendAt = spendAt;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	public String getUseDesc() {
		return useDesc;
	}

	public void setUseDesc(String useDesc) {
		this.useDesc = useDesc;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
