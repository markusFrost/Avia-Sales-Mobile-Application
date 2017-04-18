package com.aviasales.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.xml.registry.infomodel.User;

import com.avia.entities.UserResponse;
import com.avia.entities.USER;
import com.google.gson.Gson;

public class UserDb {
	private Connection conn;
	private Statement stmt;
	private Gson gson;
	
	public UserDb() {
		
		    
		connectToDB();
		
	}
	
	public String connectToDB() {
		
		gson = new Gson();
		
		String msg = "";
		
		String host = "127.2.117.130"; //в phpMyAdmin он вверху
		String port = "3306"; // и порт тоже там же
		String username = "admin8ba7j97";
		String password = "wZ3xij6nt54x";
		
		String dbName = "avia";

		String url = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
		try {
			
			Class.forName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
			
			conn = DriverManager.getConnection(url, username, password);
			
			msg += "Connection was success\n";
			
			stmt = conn.createStatement();
			
							
			
		} catch (SQLException e) {
			
			msg += "SQLException " + e.getMessage() + "\n";
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			msg += "ClassNotFoundException " + e.getMessage() + "\n";
			
			e.printStackTrace();
		}
		
		return msg;
		
	}
	
	public String LoginUser(USER user){
		
		String sql = "SELECT * " + " FROM T_USER " + 
					" WHERE Email =  '" + user.getEmail() + "' and Password = '" + user.getPassword() + "'";
		
		UserResponse UserResponse = new UserResponse();
		
		try{
			
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) {

			UserResponse.setCode(200);
			UserResponse.setMessage("Auth success");
			
			String guid = rs.getString("UserId"); 

			String name = rs.getString("Name");
			
			UUID uuid = UUID.fromString(guid);
			user.setUserId(uuid);

			user.setName (name);
			
			UserResponse.setUser(user);
        }
		else {
			UserResponse.setCode(404);
			UserResponse.setMessage("You do not registred");
		}
		
		}catch (Exception e){
			
			UserResponse.setCode(404);
			UserResponse.setMessage("You do not registred");
		}
		
		String json = gson.toJson(UserResponse);
		
		return json;
	}
	
	

	public String CreateUser(USER user) {
		
		UserResponse userResponse = new UserResponse();
		
		if (validateEmail(user.getEmail())){
			
			userResponse.setMessage("User with this email already exists");
			userResponse.setCode(404);
		}
		else{
		
		
		String msg = "output\n";
		 try
		 {
			 UUID uuid = UUID.randomUUID();
		     
		     user.setUserId(uuid);
			 
             String sql = "INSERT INTO T_USER " +
                     "(UserId, Name, Email, Password) VALUES ('"  + user.getUserId().toString() 
                     + "', '" + user.getName() + "', '" + user.getEmail() + "', '"+ user.getPassword() +"')";
             
             msg += "sql = " + sql + "\n";
             
             if (conn != null){
             //stmt = conn.createStatement();
             
             stmt.executeUpdate(sql);
             
             userResponse.setUser(user);
             userResponse.setCode(201);
             userResponse.setMessage("User successfull created");
            
             }
             else {
            	
            	 userResponse.setCode(404);
            	 userResponse.setMessage("Can not create user");
             }
             
         } catch (SQLException ex) 
         {
        	userResponse.setCode(404);
        	userResponse.setMessage("Can not create user");
         }
		 catch (Exception e)
		 {
		     userResponse.setCode(404);
		     userResponse.setMessage("Can not create user");
		     
		 }
		}
		 
		 String json = gson.toJson(userResponse);
         
         return json;
	}
	
	private boolean validateEmail(String email){
		
		String sql = "SELECT * " + " FROM T_USER " + 
				" WHERE Email =  '" + email + "'";
	
	
	try{
		
	ResultSet rs = stmt.executeQuery(sql);
	
	if (rs.next()){
		
		return true;
	}
	else{
		
		return false;
	}
	}
	catch (Exception e){}
	return true;
	}
}
