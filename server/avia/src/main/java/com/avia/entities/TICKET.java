package com.avia.entities;

import java.util.*;

public class TICKET
{
	private UUID ticketId;
	private USER user;
	private Date buyingDate;
	private List<FLIGHT> flights;

	
	public UUID getTicketId()
	{
		return ticketId;
	}
	
	public USER getUser()
	{
		return user;
	}
	
	public void setUser(USER user)
	{
		this.user = user;
	}
	
	public Date getBuyingDate()
	{
		return buyingDate;
	}
	
	public void setBuyingDate(Date buyingDate)
	{
		this.buyingDate = buyingDate;
	}
	
	public List<FLIGHT> getFlights()
	{
		return flights;
	}
	
	public TICKET()
	{
		this(UUID.randomUUID());
	}

	public TICKET(UUID ticketId)
	{
		this.ticketId = ticketId;
		flights = new ArrayList<FLIGHT>();
	}
}
