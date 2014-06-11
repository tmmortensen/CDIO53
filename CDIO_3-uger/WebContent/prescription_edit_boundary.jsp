<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request"/>
<jsp:useBean id="idError" class="java.lang.String" scope="request"/>
<jsp:useBean id="nameError" class="java.lang.String" scope="request"/>
<jsp:useBean id="prescription" class="admin.data.PrescriptionDTO" scope="request"/>
<jsp:useBean id="newId" class="java.lang.String" scope="request"/>
<% boolean complete = (Boolean) request.getAttribute("complete"); %>
<% boolean create = (Boolean) request.getAttribute("create"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit Prescription</title>
</head>
<body>

	
<div class="dialog">
	<% if (complete){ %>
	<%   if (!create){ %>  
	<h1>Dine ændringer er gemt</h1>
	<%   } else { %>
		<h1>Ny recept oprettet</h1>
	<%   } %>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Navn</th>
			</tr>
		<%
			out.println("\t\t\t\t<tr>\n");
			out.println("\t\t\t\t\t<td>" + prescription.getId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + prescription.getName() + "</td>\n");
		%>
		</tbody></table>
	<%   
	   } else if (!majorError.equals("")){
	%> 
	<% if (create) {%>	<h1>Fejl under oprettelse af bruger!</h1>
	<% } else { %><h1>Fejl under redigering af bruger!</h1> <% } %>
	<div class="error"><%out.print(majorError);%></div> 
	<% } else { %>
	<h1>Indtast nye receptoplysninger</h1>
	<form method="post">
		<% if (!idError.equals("")){ %> 
		<div class="error"><%out.print(idError);%></div>
		<% } %>
		<label for="userid">Recept ID</label>
		<input type="text" name="newId" id="userid"
			value="<%out.print(newId);%>">
			
		<% if (nameError != null && !nameError.equals("")){	%>
		<div class="error"><%out.print(nameError);%></div>
		<% } %>
		<label for="username">Receptnavn</label>
		<input type="text" name="newName" id="username"
			value="<%out.print(prescription.getName());%>">
			
		<% if (create) {%>	<input type="submit" value="Opret">
		<% } else { %><input type="submit" value="Rediger"> <% } %>
	
	</form>
	<%} %>
		<div class="buttons">
			<A href="user_admin">Tilbage</A>
			<A href="login?logout=true">Log ud</A>
		</div>
</div>
</body>
</html>