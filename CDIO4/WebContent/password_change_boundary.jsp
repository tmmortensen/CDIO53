<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword1" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword2" class="java.lang.String" scope="request"/>
<jsp:useBean id="genPword" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Program</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Intast og gentag nyt password</h1>
		<% if (error != ""){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="newPword1">Nyt password</label>
			<input type="password" name="newPword1" id="newPword1"
				value="<% out.print(newPword1); %>">
			<label for="newPword2">Gentag nyt password</label>
			<input type="password" name="newPword2" id="newPword2"
				value="<% out.print(newPword2); %>">
			<label for="genPword">Nyt password</label>
			<input type="text" name="genPword" id="genPword" readonly
				value="<% out.print(genPword); %>">
			<input type="submit" value="Skift Password">
		</form>
	</div>
</body>
</html>

