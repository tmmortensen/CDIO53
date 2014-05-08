<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword1" class="java.lang.String" scope="request"/>
<jsp:useBean id="newPword2" class="java.lang.String" scope="request"/>
<jsp:useBean id="redirect" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Skift password</title>
</head>
<body>

<div class="password_change">
		<h1>Skift dit password her:</h1>
		<% if (error != null){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="newPword1">Nyt password</label>
			<input type="text" name="NewPassword1" id="newPword1"
				value="<% out.print(newPword1); %>">
			<label for="currentPassword">Gentag nyt password</label>
			<input type="text" name="NewPassword2" id="newPword2"
				value="<% out.print(newPword2); %>">
			<input type="submit" value="Change">
		</form>
	</div>

<%
Data data =(Data) request.getAttribute("data");
List<OperatoerDTO> operators = data.getOperatoerList();

for(OperatoerDTO op: operators){
	out.print("Bruger ID: " + op.getOprId());	
}
%>
</body>
</html>



