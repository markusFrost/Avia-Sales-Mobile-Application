package com.aviasales.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.avia.entities.Booking;
import com.avia.entities.City;
import com.avia.entities.Constants;
import com.avia.entities.FlightItem;
import com.avia.entities.UserResponse;
import com.avia.exchange.BookingRequest;
import com.avia.exchange.BookingResponse;
import com.avia.exchange.UserRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BookingDb {

	private Connection conn;
	private Statement stmt;
	private Gson gson;
	
	public BookingDb(){
		
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

public String getBookingHistory(UserRequest request){
	
	List<Booking> listBooking = getBookingHistory(request.getUserId());
	
	BookingResponse response = new BookingResponse();
	
	response.setListBooking(listBooking);
	
	if (listBooking == null){
		
		response.setCode(404);
		response.setMessage("Can not find booking history");
	}
	else if (listBooking.isEmpty()){
		
		response.setCode(404);
		response.setMessage("Booking history is empty");
	}
	else {
		
		response.setCode(200);
		response.setMessage("Booking history has found");
		response.setListBooking(listBooking);
	}
	
	
	String json = gson.toJson(response);
		
	return json;
	
}

public String BookFlights(BookingRequest request) {

	UUID uuid = UUID.randomUUID();

	BookingResponse response = new BookingResponse();

	String sql = "";

	try {

		for (UUID id : request.getListFlightIdsStraight()) {

			sql = "insert into T_BOOKING " + "	(BookingId, FlightId, UserId, DateBooking, TicketType) VALUES (" + "'"
					+ uuid.toString() + "'" + " , " + "'" + id.toString() + "'" + " , " + "'" + request.getUserId()
					+ "'" + " , " + request.getDateBooking() + " , "  + Constants.STRAIGHT +  ")";

			stmt.executeUpdate(sql);

			response.setCode(200);
			response.setMessage("Booking history was foud success");

		}
		
		for (UUID id : request.getListFlightIdsBack()) {

			sql = "insert into T_BOOKING " + "	(BookingId, FlightId, UserId, DateBooking, TicketType) VALUES (" + "'"
					+ uuid.toString() + "'" + " , " + "'" + id.toString() + "'" + " , " + "'" + request.getUserId()
					+ "'" + " , " + request.getDateBooking() + " , "  + Constants.BACK +  ")";

			stmt.executeUpdate(sql);

			response.setCode(200);
			response.setMessage("Booking history was foud success");

		}
		
		
		
		response.setListBooking(getBookingHistory(request.getUserId()));
		
	} catch (Exception e) {

		response.setCode(404);
		response.setMessage("Server error: " + e.getMessage());
		System.out.println(sql);
	}
	

	return gson.toJson(response);
}

public String UndoBookFlights(BookingRequest request) {
	

	UUID uuid = UUID.randomUUID();

	BookingResponse response = new BookingResponse();

	String sql = "";

	try {


			sql = "delete from T_BOOKING " + " where " + 
			" BookingId = " + "'" + request.getBookingId() + "'" + 
					" and " + " UserId = " + "'" + request.getUserId() + "'" ; 
			

			stmt.executeUpdate(sql);

			response.setCode(200);
			response.setMessage("Booking was cancel");
			response.setListBooking(getBookingHistory(request.getUserId()));

		
		
		response.setListBooking(getBookingHistory(request.getUserId()));
	} catch (Exception e) {

		response.setCode(404);
		response.setMessage("Server error: " + e.getMessage());
		System.out.println(sql);
	}
	

	return gson.toJson(response);
}

private List<Booking> getBookingHistory(UUID userId) {

	String sql = "select distinct BookingId from T_BOOKING where " + "(" + "UserId = " + "'" + userId.toString()
			+ "'" + ")";

	List<Booking> listBooking = new ArrayList<Booking>();

	try {

		ResultSet rs = stmt.executeQuery(sql);

		List<UUID> listBookingIds = new ArrayList<UUID>();

		while (rs.next()) {

			UUID bookingId = UUID.fromString(rs.getString("BookingId"));

			listBookingIds.add(bookingId);
		}

		for (UUID bookingId : listBookingIds) {

			List<FlightItem> listStraight = getList(bookingId, userId, Constants.STRAIGHT);
			
			List<FlightItem> listBack= getList(bookingId, userId, Constants.BACK);

			long dateBooking = getDateBooking(bookingId, userId);

			Booking booking = new Booking();

			booking.setBookingId(bookingId);
			booking.setDateBook(dateBooking);
			
			booking.setListFlightsTo(listStraight);
			booking.setListFlightsBack(listBack);

			listBooking.add(booking);
		}

		System.out.println(listBooking.size());
		return listBooking;
	} catch (Exception e) {
	}

	return null;
}

private long getDateBooking(UUID bookingId, UUID userId) {

	String sql = "select DateBooking from T_BOOKING where " + "(" + "UserId = " + "'" + userId.toString() + "'"
			+ ")" + " and " + " ( " + " BookingId = " + "'" + bookingId.toString() + "'" + " ) ";

	try {

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {

			long time = rs.getLong("DateBooking");
			return time;
		}

	} catch (Exception e) {
	}
	return 0;
}

private List<FlightItem> getList(UUID bookingId, UUID userId, int ticketType) {

	String sql = "select FlightId from T_BOOKING where " + "(" + "UserId = " + "'" + userId.toString() + "'" + ")"
			+ " and " + " ( " + " BookingId = " + "'" + bookingId.toString() + "'" + " ) " + 
			" and " + " ( " + "TicketType" + " = " + "'" +  ticketType + "'" + " ) ";

	List<FlightItem> listFlights = new ArrayList<FlightItem>();

	try {

		ResultSet rs = stmt.executeQuery(sql);

		List<UUID> listFlightIds = new ArrayList<UUID>();

		while (rs.next()) {

			UUID flightId = UUID.fromString(rs.getString("FlightId"));

			listFlightIds.add(flightId);

			// System.out.println(flightId.toString());

		}

		for (UUID flightId : listFlightIds) {

			FlightItem item = getFlight(flightId);

			listFlights.add(item);
		}

		return listFlights;
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return null;

}

public FlightItem getFlight(UUID flightId) {

	String sql = " SELECT * FROM T_FLIGHT WHERE " + " FlightId = " + "'" + flightId.toString() + "'";

	try {

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {

			FlightItem flight = new FlightItem();

			UUID cityFromId = UUID.fromString(rs.getString("CityFromId"));
			UUID cityToId = UUID.fromString(rs.getString("CityToId"));

			flight.setDateArr(rs.getLong("dateArrival"));
			flight.setDateDep(rs.getLong("dateDeparture"));

			flight.setFlightId(UUID.fromString(rs.getString("FlightId")));

			flight.setFreePlaceCount(rs.getInt("FreePlaceCount"));
			flight.setPricePerTicket(rs.getDouble("Price"));

			flight.setCityFrom(getCity(cityFromId));
			flight.setCityTo(getCity(cityToId));

			return flight;
		}
	} catch (Exception e) {

		System.out.println(e.getMessage());
	}
	return null;

}

private City getCity(UUID cityId) {

	String sql = "select * from T_CITY where CityId = '" + cityId.toString() + "'";

	try {
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {

			City city = new City();
			city.setCode(rs.getString("Code"));
			city.setName(rs.getString("Name"));
			city.setCityId((UUID.fromString(rs.getString("CityId"))));

			return city;
		}
	} catch (Exception e) {
	}

	return null;
}
}
