package com.aviasales.utils;

import java.util.*;
import com.avia.entities.City;

public class Node
{
	private City city;
	private List<Arc> arcs = new ArrayList<Arc>();
	
	public City getCity()
	{
		return city;
	}
	
	public List<Arc> getArcs()
	{
		return arcs;
	}
	
	public Node(City city)
	{
		this.city = city;
	}
}
