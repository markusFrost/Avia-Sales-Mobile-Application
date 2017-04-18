package com.aviasales.utils;

import com.avia.entities.FlightItem;

public class Arc
{
	private Node nodeTo;
	private FlightItem flight;
	
	public Arc(Node nodeTo, FlightItem flight)
	{
		this.nodeTo = nodeTo;
		this.flight = flight;
	}

	public Node getNodeTo()
	{
		return nodeTo;
	}

	public FlightItem getFlight()
	{
		return flight;
	}
}
