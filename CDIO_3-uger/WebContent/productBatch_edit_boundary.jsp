<%@ page language="java" import="java.util.*,admin.data.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request" />
<jsp:useBean id="idError" class="java.lang.String" scope="request" />
<jsp:useBean id="presIdError" class="java.lang.String" scope="request" />
<jsp:useBean id="statusError" class="java.lang.String" scope="request" />
<jsp:useBean id="userError" class="java.lang.String" scope="request" />
<jsp:useBean id="compError" class="java.lang.String" scope="request" />
<jsp:useBean id="product" class="admin.data.ProductBatchDTO"
	scope="request" />
<jsp:useBean id="newId" class="java.lang.String" scope="request" />
<jsp:useBean id="newPresId" class="java.lang.String" scope="request" />
<jsp:useBean id="newStatus" class="java.lang.String" scope="request" />
<jsp:useBean id="newUserId" class="java.lang.String" scope="request" />
<%
	boolean complete = (Boolean) request.getAttribute("complete");
	boolean create = (Boolean) request.getAttribute("create");
	List<ProductBatchCompDTO> components = (List<ProductBatchCompDTO>) request
			.getAttribute("components");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Rediger Produktbatch</title>
</head>
<body>
	<div class="dialog">
		<%	if (complete) {
				if (!create) { %>
		<h1>Dine ændringer er gemt</h1>
		<%		} else { %>
		<h1>Nyt produktbatch oprettet</h1>
		<%		} %>
		<table border="1">
			<tbody>
				<tr>
					<th>Batch ID</th>
					<th>Recept ID</th>
					<th>Bruger ID</th>
					<th>Status</th>
				</tr>
				<tr>
				<%
				out.println("\t\t\t\t\t<td>" + product.getPbId() + "</td>\n");
				out.println("\t\t\t\t\t<td>" + product.getPrescriptionId() + "</td>\n");
				out.println("\t\t\t\t\t<td>" + product.getUserId() + "</td>\n");
				out.println("\t\t\t\t\t<td>" + product.getStatus().uiName() + "</td>\n");
				%>
				</tr>
			</tbody>
		</table>
		<%		if (!create) { %>
		<div class="compList">
			Foretagede vejninger:
			<table>
				<tr>
					<th>Råvarebatch ID</th>
					<th>Operatør ID</th>
					<th>Nettovægt</th>
					<th>Tara vægt</th>
				</tr>
				<%
						if (components != null && !components.isEmpty()) {
							for (ProductBatchCompDTO comp : components) {
				%>
				<tr>
					<td><% out.print(comp.getCommoditybatch_id()); %></td>
					<td><% out.print(comp.getUser_id()); %></td>
					<td><% out.print(comp.getNetto()); %></td>
					<td><% out.print(comp.getTara()); %></td>
				</tr>
				<%
							}
						}
				%>
			</table>
		</div>
		<%		} %>
		<%
			} else if (!majorError.equals("")) {
				if (create) {
		%>
		<h1>Fejl under oprettelse af produktbatch!</h1>
		<%
				} else {
		%>
		<h1>Fejl under redigering af produktbatch!</h1>
		<%
				}
		%>
		<div class="error">
			<%
				out.print(majorError);
			%>
		</div>
		<%
			} else {
		%>
		<h1>Indtast nye oplysninger om produktbatch</h1>
		<form method="post">
			<%	if (!idError.equals("")) { %>
			<div class="error">	<% out.print(idError); %> </div>
			<% } %>
			<label for="prodId">Produktbatch ID</label>
			<input type="text" name="newId"
				id="prodId" value="<%out.print(newId);%>">

			<% if (!presIdError.equals("")) { %>
			<div class="error">	<% out.print(presIdError); %> </div>
			<% } %>
			<label for="presId">Recept ID</label> <input type="text"
				name="newPresId" id="presId"
				value="<%out.print(newPresId);%>">

			<% if (!userError.equals("")) { %>
			<div class="error">	<% out.print(userError); %> </div>
			<% } %>
			<label for="userId">Bruger ID</label> <input type="text"
				name="newUserId" id="userId"
				value="<%out.print(newUserId);%>">

			<% if (!statusError.equals("")) { %>
			<div class="error">	<% out.print(statusError); %> </div>
			<% } %> 
			<label for="usertype">Status</label>
			<select name="newStatus">
				<option value="NEW" <% if (newStatus.equals("NEW")) out.print("selected");%>>
				<%out.print(StatusType.NEW.uiName());%></option>
				<option value="IN_PRODUCTION" <% if (newStatus.equals("IN_PRODUCTION")) out.print("selected");%>>
				<%out.print(StatusType.IN_PRODUCTION.uiName());%></option>
				<option value="PAUSED" <% if (newStatus.equals("PAUSED")) out.print("selected");%>>
				<%out.print(StatusType.PAUSED.uiName());%></option>
				<option value="FINISHED" <% if (newStatus.equals("FINISHED")) out.print("selected");%>>
				<%out.print(StatusType.FINISHED.uiName());%></option>
			</select>
		<div class="compList">
			Udførte vejninger:
			<% if (compError != null && !compError.equals("")) { %>
			<div class="error">	<% out.print(compError); %> </div>
			<% } %>
			<table>
				<tr>
					<th>Råvarebatch ID</th>
					<th>Operatør ID</th>
					<th>Nettovægt</th>
					<th>Tara vægt</th>
				</tr>
				<%
						if (components != null && !components.isEmpty()) {
							for (ProductBatchCompDTO comp : components) {
				%>
				<tr>
					<td> <% comp.getCommoditybatch_id(); %></td>
					<td> <% comp.getUser_id(); %></td>
					<td> <% comp.getNetto(); %></td>
					<td> <% comp.getTara(); %></td>
				</tr>
				<%			}
						} %>
			</table>
		</div>
		<%		if (create) { %>
		<input type="submit" value="Opret" >
		<%		} else {	%>
		<input type="submit" value="Gem" >
		<%		}
			} %>
		</form>
		<div class="buttons">
			<A href="productBatch_admin">Tilbage</A> 
			<A href="login?logout=true">Log	ud</A>
		</div>
	</div>
</body>
</html>