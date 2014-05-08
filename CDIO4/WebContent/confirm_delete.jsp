<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="userId" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Intast bruger-ID for den bruger der skal slettes </h1>
		<form method="post">
			<label for="userid">Bruger ID</label>
			<input type="text" name="ID" id="userid"
				value="<% out.print(userId); %>">
			<input type="submit" value="Delete user">
		</form>
	</div>
</body>
</html>