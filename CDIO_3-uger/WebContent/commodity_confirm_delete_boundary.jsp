<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="commodity" class="admin.data.CommodityDTO" scope="request"/>
<%
	boolean done = (Boolean) request.getAttribute("done");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Slet R�vare</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="delete_dialog">
		<% if (!error.equals("")){ %>
		<h1>R�vare sletning</h1>
		<%  out.print(error); %>
		<a href="commodity_admin">OK</a>
		<% } else {
			if (!done) {%>
		<h1>Bekr�ft at du vil slette f�lgende r�vare:</h1>
		<%} else { %>
		<h1>Denne r�vare er blevet slettet</h1>
		<%}  %>
		<table border="1"><tbody>
			<tr>
				<th>R�vare ID</th>
				<th>R�varenavn</th>
				<th>Leverand�r</th>
			</tr>
			<tr>
		<%
			out.println("\t\t\t\t\t<td>" + commodity.getComId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + commodity.getComName() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + commodity.getSupplier() + "</td>\n");
		%>
			</tr>
		</tbody></table>
		<% if (!done) {%>
		<div class="buttons">
			<form method="post">
				<input type="submit" value="Slet">
			</form>
			<a href="user_admin">Annull�r</a>
		</div>
		<%} else { %>
		<a href="user_admin">Tilbage</a>
		<%}
		} %>
	</div>
</body>
</html>