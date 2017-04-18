package com.aviasales.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.avia.entities.City;
import com.avia.entities.FlightItem;
import com.avia.exchange.FlightRequest;
import com.avia.exchange.FlightResponse;
import com.aviasales.utils.*;
import com.aviasales.utils.TImeUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FlightDb {
	// maximum count of transfers
	private static final int MAX_DEPTH = 2;
	private static final int HOUR_IN_MS = 3600000;
	private static final int MIN_TRANSFER_TIME = 1 * HOUR_IN_MS; // 1 hour
	private static final int MAX_TRANSFER_TIME = 8 * HOUR_IN_MS; // 8 hours
	private Connection conn;
	private static Statement stmt;
	private Gson gson;

	public FlightDb(){
		
		connectToDB();
	}
	
public String connectToDB() {
		
		gson = new GsonBuilder().disableHtmlEscaping().create();
		
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

    public String getFlights(FlightRequest request){
    	
    	FlightResponse response = new FlightResponse();
    	
    	long timeDepatrure = request.getDateDeparture();
    	
    	
    	City cityFrom = getCityByName(request.getCityFrom());
    	City cityTo = getCityByName(request.getCityTo());
    	
    	
    	
    	if (cityFrom == null || cityTo == null){
    		
    		response.setCode(404);
    		response.setMessage("No cities found");
    	}
    	else{
    		
    		List<List<FlightItem>> listRightTo = getFLights(cityFrom, cityTo, timeDepatrure);
    		
    		List<List<FlightItem>> listRightBack = new ArrayList<List<FlightItem>>();
    		
    		if (request.isRoundTrip()){
    			
    			listRightBack = getFLights(cityTo, cityFrom,  request.getDateBackReturn());
    		}
    		
    		response.setListTo(listRightTo);
    		response.setListBack(listRightBack);
    	}
    	
    	//response.setList(list);
    	
    	String json = gson.toJson(response);
		
		return json;
    }
    
    private List<List<FlightItem>> getFLights(City cityFrom, City cityTo, long timeDepatrure)
    {
    	List<List<FlightItem>> mainList = new ArrayList<List<FlightItem>>();
    	mainList.addAll(getFLights(cityFrom, cityTo, timeDepatrure, 0));

    	if (mainList.size() == 0){

    	mainList.addAll(getFLights(cityFrom, cityTo, timeDepatrure, 1));

    	return TransportUtils.formAnswerByFlightsIds(mainList, cityFrom, cityTo);
    }

    return mainList;
    	
    	
    	
    }
    
    private List<List<FlightItem>> getFLights(City cityFrom, City cityTo, long timeDepatrure, int maxDepth){
    	
    	List<FlightItem> list = new ArrayList<FlightItem>();
    	
    	List<List<FlightItem>> mainList = new ArrayList<List<FlightItem>>();
    	
    	long timeStart = TImeUtils.getStartOfDay(timeDepatrure);
    	long timeEnd = TImeUtils.getEndOfDay(timeDepatrure);
    	
    	String sql = "";
    	if (maxDepth == 0)
    	{
    		sql = " SELECT * FROM T_FLIGHT WHERE ( CityFromId='" + cityFrom.getCityId().toString() + 
    				"' ) and  ( CityToId = '" + cityTo.getCityId().toString() + "' ) and ( dateDeparture > " + 
    				timeStart + " ) and (dateDeparture < " + timeEnd + " )";
    	}
    	else
    	{
    		sql = " SELECT * FROM T_FLIGHT WHERE ( dateDeparture > " + timeStart + " ) and  (dateDeparture < " + timeEnd + " )";
    	}

		try
		{
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				FlightItem flight = new FlightItem();

				flight.setCityFrom(cityFrom);
				flight.setCityTo(cityTo);

				flight.setDateArr(rs.getLong("dateArrival"));
				flight.setDateDep(rs.getLong("dateDeparture"));

				flight.setFlightId(UUID.fromString(rs.getString("FlightId")));

				flight.setFreePlaceCount(rs.getInt("FreePlaceCount"));
				flight.setPricePerTicket(rs.getDouble("Price"));

				list = new ArrayList<FlightItem>();
				list.add(flight);
				
				List<FlightItem> newList = new ArrayList<FlightItem>();
				
				newList.add(flight);
				
				mainList.add(newList);
			}

		} catch (Exception e)
		{
		}

		// creating a graph with available flights
		Node nodeFrom = new Node(cityFrom);
		Node nodeTo = new Node(cityTo);

		// building a graph
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(nodeFrom);
		nodes.add(nodeTo);
		NextCity(list, nodes, nodeFrom, nodeTo, 0, MAX_DEPTH);

		// finding routes with no more than N transfers
		List<List<FlightItem>> routes = FindRoutes(nodeFrom, nodeTo, MAX_DEPTH);

		if (routes.size() > 0){
		   mainList.addAll(routes);
		}
		return mainList;
	}

	public City getCityByName(String name)
	{

		String sql = "select * from T_CITY where Name = '" + name + "'";

		try
		{
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next())
			{

				City city = new City();
				city.setCode(rs.getString("Code"));
				city.setName(name);
				city.setCityId((UUID.fromString(rs.getString("CityId"))));

				return city;
			}
		} catch (Exception e)
		{
		}

		return null;
	}

	/*
	 * Here we try to find a flight to go to some next city. A node is somehow
	 * equal to a city. A flight is an arc.
	 */
	private static void NextCity(List<FlightItem> allFlights, List<Node> allNodes, Node nodeFrom, Node nodeToStop,
			int currentDepth, int maxDepth)
	{
		// if we are in the destination city or reached transfer limit, no need
		// to do anything
		if (nodeFrom.getCity().compareTo(nodeToStop.getCity()) == 0 || currentDepth == maxDepth)
		{
			return;
		}

		List<FlightItem> goodFlights = new ArrayList<FlightItem>();

		// search for available flights
		for (FlightItem flight : allFlights)
		{
			if (flight.getCityFrom().compareTo(nodeFrom.getCity()) == 0)
			{
				// try to find already existing Arc with this flight. It might
				// happen, if it's not our first time here
				boolean isFound = false;
				for (Arc arc : nodeFrom.getArcs())
				{
					if (flight.getFlightId().compareTo(arc.getFlight().getFlightId()) == 0)
					{
						isFound = true;
						break;
					}
				}

				if (!isFound)
				{
					goodFlights.add(flight);
				}
			}
		}

		// processing good flights

		for (FlightItem flight : goodFlights)
		{
			// find a node or add a new one to the list of all nodes
			Node nodeTo = null;
			for (Node node : allNodes)
			{
				if (node.getCity().compareTo(flight.getCityTo()) == 0)
				{
					nodeTo = node;
				}
			}

			if (nodeTo == null)
			{
				nodeTo = new Node(nodeFrom.getCity());
				allNodes.add(nodeTo);
			}

			Arc arc = new Arc(nodeTo, flight);
			nodeFrom.getArcs().add(arc);
			allFlights.remove(flight);
		}

		// trying to go further
		for (Arc arc : nodeFrom.getArcs())
		{
			NextCity(allFlights, allNodes, arc.getNodeTo(), nodeToStop, currentDepth + 1, maxDepth);
		}
	}

	private static List<List<FlightItem>> FindRoutes(Node nodeFrom, Node nodeToStop, int maxDepth)
	{
		List<List<FlightItem>> foundRoutes = new ArrayList<List<FlightItem>>();
		List<FlightItem> currentRoute = new ArrayList<FlightItem>();

		for (Arc arc : nodeFrom.getArcs())
		{
			currentRoute.add(arc.getFlight());
			NextNode(foundRoutes, currentRoute, nodeFrom, nodeToStop, 0, maxDepth);
			currentRoute.remove(arc.getFlight());
		}

		return foundRoutes;
	}

	private static void NextNode(List<List<FlightItem>> foundRoutes, List<FlightItem> currentRoute, Node nodeFrom, Node nodeToStop, int currentDepth, int maxDepth)
	{
		// if we are in the destination city, add a new route
		if (nodeFrom.getCity().compareTo(nodeToStop.getCity()) == 0)
		{
			List<FlightItem> goodRoute = new ArrayList<FlightItem>();

			for (FlightItem flight : currentRoute)
			{
				goodRoute.add(flight);
			}

			foundRoutes.add(goodRoute);

			return;
		} else if (currentDepth == maxDepth)
		{
			return;
		}

		// else try to move forward
		for (Arc arc : nodeFrom.getArcs())
		{
			long previousArrivalTime = currentRoute.get(currentRoute.size() - 1).getDateArr();
			long nextDepartureTime = arc.getFlight().getDateDep();
			if (previousArrivalTime + MIN_TRANSFER_TIME <= nextDepartureTime && previousArrivalTime + MAX_TRANSFER_TIME >= nextDepartureTime)
			{
				currentRoute.add(arc.getFlight());
				NextNode(foundRoutes, currentRoute, arc.getNodeTo(), nodeToStop, currentDepth + 1, maxDepth);
				currentRoute.remove(arc.getFlight());
			}
		}
	}
	
	public String addFlights(FlightItem item){
		
		String msg = "";
		
		
		String sql = "insert into t_flight " + " ( " + 
		"FlightId" + " , " + "CityFromId" + " , " + "CityToId" + " , " + "dateDeparture" + " , " 
				+ "dateArrival" + " , " + "FreePlaceCount" + " , " + "Price" + " ) " + " values " + 
		" ( " + 
				"'"  + item.getFlightId() + "'" + " , " + 
				"'"  +  item.getCityFrom().getCityId() + "'" + " , " + 
				"'"  + item.getCityTo().getCityId() + "'" + " , " + 
				 + item.getDateDep()  + " , " + 
				  + item.getDateArr()  + " , " + 
			     + item.getFreePlaceCount() + " , " + 
				 + item.getPricePerTicket()  +
		 " ) ";
		
		try {
			stmt.executeUpdate(sql);
			
			msg += "Flight was sucessfull created\n";
		} catch (SQLException e) {
			
			msg += e.getMessage() + "\n" + sql + "\n";
		}
		
		return msg;
	}
	
	public String  addCity (City city){
		
		String msg = "";
		
		String sql = " insert into t_city " + " ( " + 
		"CityId" + " , " + "Name" + " , " + "Code" + " ) " + " values " + 
				" ( " + 
					"'" + city.getCityId() +  "'" + " , " + 
					"'" + city.getName() + "'" + " , " + 
					"'" + city.getCode() + "'" + 
		" ) ";
		
		try {
			stmt.executeUpdate(sql);
			
			msg += "City was sucessfull created\n";
		} catch (SQLException e) {
			
			msg += e.getMessage() + "\n" + sql + "\n";
		}
		
		return msg;
	}
}
