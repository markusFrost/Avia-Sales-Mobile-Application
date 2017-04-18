package com.avia.entities;

import java.util.*;

public class TICKET_TO_FLIGHT_LINK
{
	private UUID ticketToFlightId;
	private TICKET ticket;
	private FLIGHT flight;
	
	
	public UUID getTicketToFlightId()
	{
		return ticketToFlightId;
	}
	
	public TICKET getTicket()
	{
		return ticket;
	}
	
	public FLIGHT getFlight()
	{
		return flight;
	}
	
	public TICKET_TO_FLIGHT_LINK(TICKET ticket, FLIGHT flight)
	{
		this(UUID.randomUUID(), ticket, flight);
	}
	
	public TICKET_TO_FLIGHT_LINK(UUID ticketToFlightId, TICKET ticket, FLIGHT flight)
	{
		this.ticketToFlightId = ticketToFlightId;
		this.ticket = ticket;
		this.flight = flight;
	}
}
