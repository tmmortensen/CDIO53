<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="comBatch" class="admin.data.CommodityBatchDTO" scope="request"/>
<%
	boolean done = (Boolean) request.getAttribute("done");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sletning af R�varebatch</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="delete_dialog">
		<% if (!error.equals("")){ %>
		<h1>Sletning af R�varebatch</h1>
		<%  out.print(error); %>
		<a href="commodityBatch_admin">OK</a>
		<% } else {
			if (!done) {%>
		<h1>Bekr�ft at du vil slette f�lgende r�varebatch:</h1>
		<%} else { %>
		<h1>Dette r�varebatch er blevet slettet</h1>
		<%}  %>
		<table border="1"><tbody>
			<tr>
				<th>Batch ID</th>
				<th>R�vare ID</th>
				<th>M�ngde</th>
			</tr>
			<tr>
		<%
			out.println("\t\t\t\t\t<td>" + comBatch.getCommodityBatchId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + comBatch.getCommodityId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + comBatch.getAmount() + "</td>\n");
		%>
			</tr>
		</tbody></table>
		<% if (!done) {%>
		<div class="buttons">
			<form method="post">
				<input type="submit" value="Slet">
			</form>
			<a href="commodityBatch_admin">Annull�r</a>
		</div>
		<%} else { %>
		<a href="commodityBatch_admin">Tilbage</a>
		<%}
		} %>
	</div>
</body>
</html>