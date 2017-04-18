package com.avia.exchange;

import java.util.List;

import com.avia.entities.FlightItem;

public class FlightResponse {
	
	private List<List<FlightItem>> listTo;
	
	private List<List<FlightItem>> listBack;
	

	public List<List<FlightItem>> getListTo() {
		return listTo;
	}

	public void setListTo(List<List<FlightItem>> listTo) {
		this.listTo = listTo;
	}

	public List<List<FlightItem>> getListBack() {
		return listBack;
	}

	public void setListBack(List<List<FlightItem>> listBack) {
		this.listBack = listBack;
	}

	private String message;
	private int code;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
