<%@page import="com.avia.entities.UserResponse"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.avia.entities.USER"%>;
<%@page import="com.aviasales.db.UserDb"%>;
<%


String jsonInput = request.getHeader("json_user");

if (jsonInput!=null && !jsonInput.isEmpty()) {

			Gson gson = new Gson();
			USER user = gson.fromJson(jsonInput, USER.class);
			
			UserDb userDb = new UserDb();
			
			String jsonOutput = userDb.CreateUser(user);
			
            PrintWriter printWriter = response.getWriter();
            printWriter.println(jsonOutput);
            printWriter.close();
}
else {
	
	UserResponse resp = new UserResponse();
	resp.setCode(404);
	resp.setMessage("JsonInput is empty!");
	
	String json = new Gson().toJson(resp);
	
	PrintWriter printWriter = response.getWriter();
    printWriter.println(json);
    printWriter.close();
	
}
%>