package com.avia.entities;

public class UserResponse {
	
	private USER user;
	
	public USER getUser() {
		return user;
	}

	public void setUser(USER user) {
		this.user = user;
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
