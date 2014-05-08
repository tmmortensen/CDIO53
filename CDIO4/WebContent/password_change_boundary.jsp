<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword1" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword2" class="java.lang.String" scope="request"/>
<jsp:useBean id="genPword" class="java.lang.String" scope="request"/>
<jsp:useBean id="pword" class="java.lang.String" scope="request"/>
<jsp:useBean id="uid" class="java.lang.String" scope="request"/>
<jsp:useBean id="redirect" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Program</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Her kan du ændre password</h1>
		<% if (error != ""){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="uid">Indtast bruger id</label>
			<input type="text" name="uid" id="uid"
				value="<% out.print(uid); %>">
			<label for="pword">Nuværende password</label>
			<input type="password" name="pword" id="pword"
				value="<% out.print(newPword1); %>">
			<label for="newPword1">Nyt password</label>
			<input type="password" name="newPword1" id="newPword1"
				value="<% out.print(newPword1); %>">
			<label for="newPword2">Gentag nyt password</label>
			<input type="password" name="newPword2" id="newPword2"
				value="<% out.print(newPword2); %>">
			<label for="genPword">Nyt password</label>
			<input type="text" name="genPword" id="genPword" readonly
				value="<% out.print(genPword); %>">
			<input type="hidden" name="redirect" id="redirect"
				value="<% out.print(redirect); %>">
			<input type="submit" value="Skift Password">
		</form>
	</div>
</body>
</html>

