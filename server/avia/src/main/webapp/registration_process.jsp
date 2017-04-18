<%@page import="com.avia.entities.USER"%>;
<%@page import="com.aviasales.db.UserDb"%>;
<%@page import="java.io.PrintWriter"%>

<%@include file="header.html" %>
<%
USER user = new USER();

String name = request.getParameter("name");
String email = request.getParameter("email");
String password = request.getParameter("password");

user.setName(name);
user.setEmail(email);
user.setPassword(password);

UserDb userDb = new UserDb();

//String connResult = userDb.connectToDB();

String jsonOutput = userDb.CreateUser(user);

PrintWriter printWriter = response.getWriter();
printWriter.println(jsonOutput);
printWriter.close();


%>

<%@include file="foother.html" %>