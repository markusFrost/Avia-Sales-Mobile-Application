package avia.androi.innopolis.com.aviasales.models.responses;

import java.util.List;

import avia.androi.innopolis.com.aviasales.models.Flight;

public class FlightResponse {

	private List<List<Flight>> listTo;

	private List<List<Flight>> listBack;


	public List<List<Flight>> getListTo() {
		return listTo;
	}

	public void setListTo(List<List<Flight>> listTo) {
		this.listTo = listTo;
	}

	public List<List<Flight>> getListBack() {
		return listBack;
	}

	public void setListBack(List<List<Flight>> listBack) {
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
