<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="userId" class="java.lang.String" scope="request"/>
<jsp:useBean id="password" class="java.lang.String" scope="request"/>
<jsp:useBean id="redirect" class="java.lang.String" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Intast bruger ID og kodeord </h1>
		<% if (error != ""){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="userid">Bruger ID</label>
			<input type="text" name="userId" id="userid"
				value="<% out.print(userId); %>">
			<label for="password">Kodeord</label>
			<input type="password" name="password" id="password"
				value="<% out.print(password); %>">
			<input type="hidden" name="redirect" id="redirect"
				value="<% out.print(redirect); %>">
			<input type="submit" value="Log ind">
		</form>
	</div>
	
	<%--Kun til test--%>
	<%
		out.print("Current Users <BR>");
		Data data = (Data) request.getAttribute("data");
		List<OperatoerDTO> operators = data.getOperatoerList();
		
		for(OperatoerDTO op : operators){
			out.print("ID: " + op.getOprId() + " PW: " 
						+ op.getPassword() + " Admin: " + op.isAdmin() + "<BR>");
		}
	%>
	
</body>
</html>