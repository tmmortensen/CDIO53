<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request"/>
<jsp:useBean id="idError" class="java.lang.String" scope="request"/>
<jsp:useBean id="error" class="admin.data.UserInfo" scope="request"/>
<jsp:useBean id="info" class="admin.data.UserInfo" scope="request"/>
<jsp:useBean id="old" class="admin.data.UserInfo" scope="request"/>
<jsp:useBean id="newId" class="java.lang.String" scope="request"/>
<jsp:useBean id="oldId" class="java.lang.String" scope="request"/>
<% boolean complete = (Boolean) request.getAttribute("complete"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit user</title>
</head>
<body>

	
<div class="dialog">
	<% if (complete){ %> 
	<h1>Redigering foretaget</h1>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Initialer</th>
				<th>Navn</th>
				<th>CPR-nummer</th>
				<th>Brugertype</th>
			</tr>
		<%
			out.println("\t\t\t\t<tr>\n");
			out.println("\t\t\t\t\t<td>" + old.id + "</td>\n");
			out.println("\t\t\t\t\t<td>" + old.ini + "</td>\n");
			out.println("\t\t\t\t\t<td>" + old.name + "</td>\n");
			out.println("\t\t\t\t\t<td>" + old.cpr + "</td>\n");
			out.println("\t\t\t\t\t<td>" + old.access.name() +"</td>\n");
		%>
		</tbody></table><br>
		Er blevet rettet til:<br><br>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Initialer</th>
				<th>Navn</th>
				<th>CPR-nummer</th>
				<th>Brugertype</th>
			</tr>
		<%
			out.println("\t\t\t\t<tr>\n");
			out.println("\t\t\t\t\t<td>" + info.id + "</td>\n");
			out.println("\t\t\t\t\t<td>" + info.ini + "</td>\n");
			out.println("\t\t\t\t\t<td>" + info.name + "</td>\n");
			out.println("\t\t\t\t\t<td>" + info.cpr + "</td>\n");
			out.println("\t\t\t\t\t<td>" + info.access.name() +"</td>\n");
		%>
		</tbody></table>

	<%
		} else if (!majorError.equals("")){
	%> 
	<h1>Fejl under redigering af bruger!</h1>
	<div class="error"> 
		<%
 			out.print(majorError);
 		%> 
	</div> 
	<%
 		} else {
 	%>
	<h1>Indtast nye brugeroplysninger</h1>
	<form method="post">
		<%
			if (!idError.equals("")){
		%> <div class="error"> <%
 	out.print(idError);
 %> </div> <%
 	}
 %>
		<label for="userid">Bruger ID</label>
		<input type="text" name="newId" id="userid"
			value="<%out.print(newId);%>">
		<%
			if (error.ini != null){
		%> <div class="error"> <%
 	out.print(error.ini);
 %> </div> <%
 	}
 %>
		<label for="userini">Bruger initialer</label>
		<input type="text" name="newIni" id="userini"
			value="<%out.print(info.ini);%>">
		<%
			if (error.name != null){
		%> <div class="error"> <%
 	out.print(error.name);
 %> </div> <%
 	}
 %>
		<label for="username">Brugernavn</label>
		<input type="text" name="newName" id="username"
			value="<%out.print(info.name);%>">
		<%
			if (error.cpr != null){
		%> <div class="error"> <%
 	out.print(error.cpr);
 %> </div> <%
 	}
 %>
		<label for="usercpr">CPR</label>
		<input type="text" name="newCPR" id="usercpr"
			value="<%out.print(info.cpr);%>">
		<label for="isAdmin">Administrator</label>
		<input type="checkbox" name="newAdmin" id="isAdmin"
		
			value="true" >
		<input type="submit" value="Rediger">
	</form>
	<%} %>
		<div class="buttons">
			<A href="user_admin">Tilbage</A>
			<A href="login?logout=true">Log ud</A>
		</div>
</div>
</body>
</html>