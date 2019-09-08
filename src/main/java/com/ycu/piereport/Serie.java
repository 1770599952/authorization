package com.ycu.piereport;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Serie {

	private String name;
	private String type;
	private List<SerieData> data;
	
	public Serie() {
		this.data = new ArrayList<>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SerieData> getData() {
		return data;
	}
	public void setData(List<SerieData> data) {
		this.data = data;
	}
	
	
	
}
