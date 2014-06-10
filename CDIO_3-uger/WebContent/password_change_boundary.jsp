<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="newPword1" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword2" class="java.lang.String" scope="request"/>
<jsp:useBean id="password" class="java.lang.String" scope="request"/>
<jsp:useBean id="pwError" class="java.lang.String" scope="request"/>
<jsp:useBean id="npwError" class="java.lang.String" scope="request"/>
<%
	Boolean success = (Boolean) request.getAttribute("success");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Program</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

	<div class="password_dialog">
	<% if(success){%>
		<h1>Dit password er ændret</h1>
		<%}else{  %>
		<h1>Her kan du ændre password</h1>
		<form method="post">
		<% if (pwError != null && !pwError.equals("")){ %> 
		<div class="error"> <% out.print(pwError); %> </div> <%} %>
			<label for="pword">Nuværende password</label>
			<input type="password" name="password" id="pword"
				value="<% out.print(password); %>">
		<% if (npwError != null && !npwError.equals("")){ %> 
		<div class="error"> <% out.print(npwError); %> </div> <%} %>
			<label for="newPword1">Nyt password</label>
			<input type="password" name="newPword1" id="newPword1"
				value="<% out.print(newPword1); %>">
			<label for="newPword2">Gentag nyt password</label>
			<input type="password" name="newPword2" id="newPword2"
				value="<% out.print(newPword2); %>">
			<input type="submit" value="Skift Password">
		</form>
		<%}%>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>

