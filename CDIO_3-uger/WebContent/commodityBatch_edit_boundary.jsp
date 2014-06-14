<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request"/>
<jsp:useBean id="idError" class="java.lang.String" scope="request"/>
<jsp:useBean id="comIdError" class="java.lang.String" scope="request"/>
<jsp:useBean id="amountError" class="java.lang.String" scope="request"/>
<jsp:useBean id="newId" class="java.lang.String" scope="request"/>
<jsp:useBean id="newComId" class="java.lang.String" scope="request"/>
<jsp:useBean id="newAmount" class="java.lang.String" scope="request"/>
<jsp:useBean id="comBatch" class="admin.data.CommodityBatchDTO" scope="request"/>
<% boolean complete = (Boolean) request.getAttribute("complete"); %>
<% boolean create = (Boolean) request.getAttribute("create"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Redigering af råvarebatch</title>
</head>
<body>

	
<div class="dialog">
	<% if (complete){ %>
	<%   if (!create){ %>  
	<h1>Ændring af råvarebatch foretaget</h1>
	<%   } else { %>
	<h1>Oprettelse af råvarebatch foretaget</h1>
	<%   } %>
	<table border="1"><tbody>
		<tr>
			<th>Batch ID</th>
			<th>Råvare ID</th>
			<th>Mængde</th>
		</tr>
		<tr>
		<%
			out.println("\t\t\t\t\t<td>" + comBatch.getCommodityBatchId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + comBatch.getCommodityId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + comBatch.getAmount() + "</td>\n");
		%>
		</tr>
	</tbody></table>
	<%	} else if (!majorError.equals("")){
			if (create) {%>	<h1>Fejl under oprettelse af råvarebatch!</h1>
	<% 		} else { %><h1>Fejl under redigering af råvarebatch!</h1> <% } %>
	<div class="error"><%out.print(majorError);%></div> 
	<% } else { %>
	<h1>Indtast nye oplysninger om råvarebatch</h1>
	<form method="post">
		<% if (!idError.equals("")){ %> 
		<div class="error"><%out.print(idError);%></div>
		<% } %>
		<label for="batchId">Batch ID</label>
		<input type="text" name="newId" id="batchId"
			value="<%out.print(newId);%>">
			
		<% if (comIdError != null && !comIdError.equals("")){	%>
		<div class="error"><%out.print(comIdError);%></div>
		<% } %>
		<label for="comid">Råvare ID</label>
		<input type="text" name="newComId" id="comid"
			value="<%out.print(newComId);%>">
			
		<% if (amountError != null && !amountError.equals("")){ %>
		<div class="error"><%out.print(newComId);%></div>
		<% } %>
		<label for="amount">Mængde</label>
		<input type="text" name="newAmount" id="amount"
			value="<%out.print(newAmount);%>">
			
		<% if (create) {%>	<input type="submit" value="Opret">
		<% } else { %><input type="submit" value="Rediger"> <% } %>
	
	</form>
	<%} %>
		<div class="buttons">
			<A href="commodityBatch_admin">Tilbage</A>
			<A href="login?logout=true">Log ud</A>
		</div>
</div>
</body>
</html>