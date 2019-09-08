package com.ycu.piereport;

public class OrderPieBean {

	private String category;
	private Long count;
		
	public OrderPieBean() {
		super();
	}
	public OrderPieBean(String category, Long count) {
		super();
		this.category = category;
		this.count = count;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
