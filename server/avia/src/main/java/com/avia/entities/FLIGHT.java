package com.avia.entities;

import java.util.*;

public class FLIGHT
{
	private UUID flightId;
	private AIRPORT departureAirport;
	private AIRPORT arrivalAirport;
	private Date departureTime;
	private Date arrivalTime;
	private double cost;
	private int numberOfSeats;


	public UUID getFlightId()
	{
		return flightId;
	}
	
	public AIRPORT getDepartureAirport()
	{
		return departureAirport;
	}
	
	public void setDepartureAirport(AIRPORT departureAirport)
	{
		this.departureAirport = departureAirport;
	}
	
	public AIRPORT getArrivalAirport()
	{
		return arrivalAirport;
	}

	public void setArrivalAirport(AIRPORT arrivalAirport)
	{
		this.arrivalAirport = arrivalAirport;
	}
	
	public Date getDepartureTime()
	{
		return departureTime;
	}
	
	public void setDepartureTime(Date departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Date getArrivalTime()
	{
		return arrivalTime;
	}
	
	public void setArrivalTime(Date arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public double getCost()
	{
		return cost;
	}
	
	public void setCost(double cost)
	{
		this.cost = cost;
	}
	
	public int getNumberOfSeats()
	{
		return numberOfSeats;
	}
	
	public void setNumberOfSeats(int numberOfSeats)
	{
		this.numberOfSeats = numberOfSeats;
	}
	
	
	public FLIGHT()
	{
		this(UUID.randomUUID());
	}

	public FLIGHT(UUID flightId)
	{
		this.flightId = flightId;
	}
}
