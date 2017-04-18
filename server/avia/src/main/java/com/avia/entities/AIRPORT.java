package com.avia.entities;

import java.util.*;

/*
 * We do not edit airport info. That is why there are only getters for the fields.
 * We need a constructor just to create a copy of a database record in DataHolder.
 */

public class AIRPORT
{
	private UUID airportId;
	private String name;
	private String isoCountry;
	private String city;
	private String iataCode;
	private double latitude;
	private double longitude;
	private String homeLink;
	private String wikiLink;
	private String comments;
	

	public UUID getAirportId()
	{
		return airportId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getIsoCountry()
	{
		return isoCountry;
	}

	public String getCity()
	{
		return city;
	}
	
	public String getIataCode()
	{
		return iataCode;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public String getHomeLink()
	{
		return homeLink;
	}
	
	public String getWikiLink()
	{
		return wikiLink;
	}
	
	public String getComments()
	{
		return comments;
	}
	
	
	public AIRPORT(UUID airportId, String name, String isoCountry, String city, String iataCode, double latitude, double longitude, String homeLink, String wikiLink, String comments)
	{
		this.airportId = airportId;
		this.name = name;
		this.isoCountry = isoCountry; 
		this.city = city;
		this.iataCode = iataCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.homeLink = homeLink;
		this.wikiLink = wikiLink;
		this.comments = comments;
	}	
}
