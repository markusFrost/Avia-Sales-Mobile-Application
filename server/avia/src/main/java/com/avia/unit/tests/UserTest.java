package com.avia.unit.tests;

import com.avia.entities.USER;
import com.avia.entities.UserResponse;
import com.aviasales.db.UserDb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserTest {
	

	public static String testCreateUser() {

		String msg = "";

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		USER user = generateUser();

		UserDb db = new UserDb();

		String json = db.CreateUser(user);

		UserResponse response = gson.fromJson(json, UserResponse.class);

		if (response.getCode() == 201) {

			msg += response.getMessage() + " userId = " + response.getUser().getUserId() + "\n";
		} else {

			msg += response.getMessage() + "\n";
		}

		return msg;
	}
	
	public static String  loginUser(){
		
		String msg = "";

		Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		USER user = generateUser();

		UserDb db = new UserDb();

		String json = db.LoginUser(user);

		UserResponse response = gson.fromJson(json, UserResponse.class);

		if (response.getCode() == 200) {

			msg += response.getMessage() + " userId = " + response.getUser().getUserId() + "\n";
		} else {

			msg += response.getMessage() + "\n";
		}

		return msg;
	}
	

	private static USER generateUser() {

		USER user = new USER();

		user.setEmail("alerks@mail.ru");
		user.setName("Aleks");
		user.setPassword("1234");

		return user;
	}

}
