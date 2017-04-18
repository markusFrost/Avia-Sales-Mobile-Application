package com.avia.entities;

import java.util.*;

public class USER
{
	private UUID userId;
	private String name;
	private String email;
	private String password;
	private List<TICKET> tickets;
	

	public UUID getUserId()
	{
		return userId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCity()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public List<TICKET> getTickets()
	{
		return tickets;
	}
	
	public USER()
	{
		//this(UUID.randomUUID());
	}
	
	public USER(UUID userId)
	{
		this.userId = userId;
		tickets = new ArrayList<TICKET>();
	}

	public void setUserId(UUID uuid) {
		
		this.userId = uuid;
		
	}

	public String getPassword() {
		return password;
	}
}
