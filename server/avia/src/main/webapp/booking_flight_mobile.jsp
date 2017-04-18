<%@page import="com.avia.exchange.BookingResponse"%>
<%@page import="com.aviasales.db.BookingDb"%>
<%@page import="com.avia.exchange.BookingRequest"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.avia.exchange.FlightResponse"%>
<%@page import="com.aviasales.db.FlightDb"%>
<%@page import="com.avia.exchange.FlightRequest"%>
<%@page import="com.avia.entities.UserResponse"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.avia.entities.USER"%>;
<%@page import="com.aviasales.db.UserDb"%>;
<%


String jsonInput = request.getHeader("json_booking");

Gson gson = new GsonBuilder().disableHtmlEscaping().create();

if (jsonInput!=null && !jsonInput.isEmpty()) {
			
			
			BookingRequest bookingRequest = gson.fromJson(jsonInput, BookingRequest.class);	
			
			BookingDb db = new BookingDb();
			
			String jsonOutput = db.BookFlights(bookingRequest);
			
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonOutput);
            printWriter.close();
}
else {
    
    BookingResponse resp = new BookingResponse();
    resp.setCode(404);
    resp.setMessage("JsonInput is empty!");
    
    String json = gson.toJson(resp);
    
    PrintWriter printWriter = response.getWriter();
    printWriter.println(json);
    printWriter.close();
    
}
%>