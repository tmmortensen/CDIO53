<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request"/>
<jsp:useBean id="idError" class="java.lang.String" scope="request"/>
<jsp:useBean id="nameError" class="java.lang.String" scope="request"/>
<jsp:useBean id="supError" class="java.lang.String" scope="request"/>
<jsp:useBean id="newId" class="java.lang.String" scope="request"/>
<jsp:useBean id="commodity" class="admin.data.CommodityDTO" scope="request"/>
<% boolean complete = (Boolean) request.getAttribute("complete"); %>
<% boolean create = (Boolean) request.getAttribute("create"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Redigering af r�vare</title>
</head>
<body>

	
<div class="dialog">
	<% if (complete){ %>
	<%   if (!create){ %>  
	<h1>�ndring af r�vare foretaget</h1>
	<%   } else { %>
	<h1>Oprettelse af r�vare foretaget</h1>
	<%   } %>
	<table border="1"><tbody>
		<tr>
			<th>R�vareID</th>
			<th>R�varenavn</th>
			<th>Leverand�r</th>
		</tr>
		<tr>
		<%
			out.println("\t\t\t\t\t<td>" + commodity.getComId() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + commodity.getComName() + "</td>\n");
			out.println("\t\t\t\t\t<td>" + commodity.getSupplier() + "</td>\n");
		%>
		</tr>
	</tbody></table>
	<%	} else if (!majorError.equals("")){
			if (create) {%>	<h1>Fejl under oprettelse af r�vare!</h1>
	<% 		} else { %><h1>Fejl under redigering af r�vare!</h1> <% } %>
	<div class="error"><%out.print(majorError);%></div> 
	<% } else { %>
	<h1>Indtast nye r�vareoplysninger</h1>
	<form method="post">
		<% if (!idError.equals("")){ %> 
		<div class="error"><%out.print(idError);%></div>
		<% } %>
		<label for="comId">R�vare ID</label>
		<input type="text" name="newId" id="comId"
			value="<%out.print(newId);%>">
			
		<% if (nameError != null && !nameError.equals("")){	%>
		<div class="error"><%out.print(nameError);%></div>
		<% } %>
		<label for="comName">R�varenavn</label>
		<input type="text" name="newName" id="comName"
			value="<%out.print(commodity.getComName());%>">
			
		<% if (supError != null && !supError.equals("")){ %>
		<div class="error"><%out.print(supError);%></div>
		<% } %>
		<label for="supplier">Leverand�r</label>
		<input type="text" name="newSupplier" id="supplier"
			value="<%out.print(commodity.getSupplier());%>">
			
		<% if (create) {%>	<input type="submit" value="Opret">
		<% } else { %><input type="submit" value="Rediger"> <% } %>
	
	</form>
	<%} %>
		<div class="buttons">
			<A href="commodity_admin">Tilbage</A>
			<A href="login?logout=true">Log ud</A>
		</div>
</div>
</body>
</html>