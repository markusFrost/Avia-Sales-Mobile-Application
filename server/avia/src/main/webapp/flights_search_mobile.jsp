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

   
//String jsonInput = request.getParameter("json_user");

String jsonInput = request.getHeader("json_flights");

if (jsonInput!=null && !jsonInput.isEmpty()) {

			Gson gson = new Gson();
			FlightRequest flightRequest = gson.fromJson(jsonInput, FlightRequest.class);			
			
			FlightDb db = new FlightDb();
			
			String jsonOutput = db.getFlights(flightRequest);
			
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonOutput);
            printWriter.close();
}
else {
    
    FlightResponse resp = new FlightResponse();
    resp.setCode(404);
    resp.setMessage("JsonInput is empty!");
    
    String json = new Gson().toJson(resp);
    
    PrintWriter printWriter = response.getWriter();
    printWriter.println(json);
    printWriter.close();
    
}
%>