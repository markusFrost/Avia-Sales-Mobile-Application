package com.aviasales.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper
{
	public DbHelper() 
	{
		
	}

	public String connectToDb()
	{
		String msg = "";
		
		String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		
		String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

		String url = String.format(":mysql://%s:%s/jbossas", host, port);
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");

			 
			Connection conn = DriverManager.getConnection(url, username, password);
			
			Statement stmt = conn.createStatement();
			
			ResultSet  rs = stmt.executeQuery("select * from T_USER");
			 
			msg += "Connection success";
			
			msg += "\n" + username + "\t" + password + "\n";
			
            while (rs.next()) {
                int count = rs.getInt(1);
               msg += "Total number of books in the table : " + count;
            }
            
		} catch (SQLException e)
		{
			msg = e.getMessage();
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			msg = e.getMessage();
			e.printStackTrace();
		}
		
		return msg;
		
	}
}
