<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="userID" class="java.lang.String" scope="request"/>
<jsp:useBean id="password" class="java.lang.String" scope="request"/>
<jsp:useBean id="redirect" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit user</title>
</head>
<body>

<div class="edit_user">
		<h1>Intast bruger ID for den du ønsker at ændre </h1>
		<% if (error != null){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="userid">Bruger ID</label>
			<input type="text" name="UserID" id="userid"
				value="<% out.print(userID); %>">
			<input type="submit" value="Edit">
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



