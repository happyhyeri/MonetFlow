package vo;

import java.sql.Date;

public class Statistics {
	
	private int sum;
	private String month;
	private int category;
	
	public Statistics() {
		super();
	}

	public Statistics(int sum, String month) {
		super();
		this.sum = sum;
		this.month = month;
	}

	
	public Statistics(int sum, String month,int category) {
		super();
		this.sum = sum;
		this.month = month;
		this.category = category;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	

	
}
