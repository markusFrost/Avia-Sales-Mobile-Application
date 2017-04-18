<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.avia.entities.UserResponse"%>
<%@page import="com.avia.entities.USER"%>;
<%@page import="com.aviasales.db.UserDb"%>;
<%@page import="java.io.PrintWriter"%>
<%@include file="header.html" %>

<%
USER user = new USER();

String email = request.getParameter("email");
String password = request.getParameter("password");

user.setEmail(email);
user.setPassword(password);

UserDb userDb = new UserDb();

String jsonOutput = userDb.LoginUser(user);

UserResponse resp = new Gson().fromJson(jsonOutput, UserResponse.class);

PrintWriter printWriter = response.getWriter();

if (resp.getCode() == 200) {
	user = resp.getUser();
	printWriter.println("Hello, " + user.getName());
	//TODO: add pause and redirect to searching page
} else {
	printWriter.println(resp.getMessage());
}
//printWriter.println(jsonOutput);
printWriter.close();


%>

<%@include file="foother.html" %>