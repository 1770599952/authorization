package com.ycu.piereport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SerieData {

	private Long value;
	private String name;
	
	public SerieData() {
		super();
	}
	
	public SerieData(Long value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
