<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="userInfo" class="data.UserInfo" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="delete_dialog">
		<% if (error != ""){ %>
		<h1>Bruger sletning</h1>
		<%  out.print(error); %>
		<a href="user_admin">OK</a>
		<% } else {%>
		<h1>Bekræft at du vil slette følgende bruger:</h1>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Initialer</th>
				<th>Navn</th>
				<th>CPR-nummer</th>
				<th>Administrator</th>
			</tr>
		<%
			out.println("\t\t\t\t<tr>\n");
			out.println("\t\t\t\t\t<td>" + userInfo.id + "</td>\n");
			out.println("\t\t\t\t\t<td>" + userInfo.ini + "</td>\n");
			out.println("\t\t\t\t\t<td>" + userInfo.name + "</td>\n");
			out.println("\t\t\t\t\t<td>" + userInfo.cpr + "</td>\n");
			if (userInfo.admin)
				out.println("\t\t\t\t\t<td>X</td>\n");
			else
				out.println("\t\t\t\t\t<td> </td>\n");
		%>
		</tbody></table>
		
		<form method="post">
			<input type="hidden" name="confirmed" id="confirmed"
				value="true">
		</form>
		<div class="buttons">
			<input type="submit" value="Slet">
			<a href="user_admin">Annullér</a>
		</div>
		<%} %>
	</div>
</body>
</html>