<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request"/>
<jsp:useBean id="bruto" class="java.lang.String" scope="request"/>
<jsp:useBean id="tara" class="java.lang.String" scope="request"/>
<%
	double netto = (Double) request.getAttribute("netto");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Program</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Intast bruto og tara vægt </h1>
		<% if (error != ""){ %> <div class="error"> <% out.print(error); %> </div> <%} %>
		<form method="post">
			<label for="bruto">Bruto vægt</label>
			<input type="text" name="bruto" id="bruto"
				value="<% out.print(bruto); %>">
			<label for="tara">Tara vægt</label>
			<input type="text" name="tara" id="tara"
				value="<% out.print(tara); %>">
			<label for="netto">Netto vægt (resultat)</label>
			<input type="text" name="netto" id="netto" readonly
				value="<% out.print(netto); %>">
			<input type="submit" value="Beregn">
		</form>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>