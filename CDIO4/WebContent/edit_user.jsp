<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error"    class="java.lang.String" scope="request"/>
<jsp:useBean id="userID"   class="java.lang.String" scope="request"/>
<jsp:useBean id="userINI"  class="java.lang.String" scope="request"/>
<jsp:useBean id="userNAME" class="java.lang.String" scope="request"/>
<jsp:useBean id="userCPR"  class="java.lang.String" scope="request"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit user</title>
</head>
<body>

	
<div class="edit_user">
		<h1>Indtast det nye brugerID </h1>
		<% if (error != null){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="userid">Bruger ID</label>
			<input type="text" name="userID" id="userid"
				value="<% out.print(userID); %>">
				
				<label for="userini">Bruger ini</label>
			<input type="text" name="userINI" id="userini"
				value="<% out.print(userINI); %>">
				
				<label for="username">Bruger Navn</label>
			<input type="text" name="userNAME" id="username"
				value="<% out.print(userNAME); %>">
				
				<label for="usercpr">CPR</label>
			<input type="text" name="userCPR" id="usercpr"
				value="<% out.print(userCPR); %>">
				
			<input type="submit" value="Edit">
		</form>
	</div>










</body>
</html>



