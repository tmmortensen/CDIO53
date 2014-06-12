<%@ page language="java" import="java.util.*,admin.data.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="majorError" class="java.lang.String" scope="request" />
<jsp:useBean id="idError" class="java.lang.String" scope="request" />
<jsp:useBean id="nameError" class="java.lang.String" scope="request" />
<jsp:useBean id="prescription" class="admin.data.PrescriptionDTO"
	scope="request" />
<jsp:useBean id="newId" class="java.lang.String" scope="request" />
<%
	boolean complete = (Boolean) request.getAttribute("complete");
	boolean create = (Boolean) request.getAttribute("create");
	List<PrescriptionCompDTO> components = (List<PrescriptionCompDTO>) request
			.getAttribute("components");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Edit Prescription</title>
</head>
<body>
	<div class="dialog">
		<%	if (complete) {
				if (!create) { %>
		<h1>Dine ændringer er gemt</h1>
		<%		} else { %>
		<h1>Ny recept oprettet</h1>
		<%		} %>
		<table border="1">
			<tbody>
				<tr>
					<th>ID</th>
					<th>Navn</th>
				</tr>
				<%
					out.println("\t\t\t\t<tr>\n");
						out.println("\t\t\t\t\t<td>" + prescription.getId() + "</td>\n");
						out.println("\t\t\t\t\t<td>" + prescription.getName()
								+ "</td>\n");
						out.println("\t\t\t\t</tr>\n");
				%>
			</tbody>
		</table>
				<div class="compList">
			Receptkomponenter:
			<table>
				<tr>
					<th>Råvare ID</th>
					<th>Nettovægt</th>
					<th>Tollerance</th>
				</tr>
		<%			int i = 0;
					if (components != null && !components.isEmpty()){
						for (PrescriptionCompDTO comp : components){%>
				<tr>
					<td>
						<%out.print(comp.getCommodityId());%>
					</td>
					<td>
						<%out.print(comp.getNomNetto());%>
					</td>
					<td>
						<%out.print(comp.getTolerance());%>
					</td>
				</tr>
			<%			i++;
						} 
					} %>	
			</table>
		</div>
		<% 	} else if (!majorError.equals("")) {
		   		if (create) { %>
		<h1>Fejl under oprettelse af recept!</h1>
		<%		} else { %>
		<h1>Fejl under redigering af recept!</h1>
		<%		} %>
		<div class="error">
		<%	out.print(majorError); %>
		</div>
		<%	} else { %>
		<h1>Indtast nye receptoplysninger</h1>
		<form id="main" method="post">
			
			<% if (!idError.equals("")) { %>
			<div class="error">	<% out.print(idError); %> </div>
			<% } %>
			<label for="userid">Recept ID</label> 
			<input type="text" name="newId"
				id="userid" value="<%out.print(newId);%>">

			<% if (nameError != null && !nameError.equals("")) { %>
			<div class="error"> <% out.print(nameError); %>	</div>
			<% } %>
			<label for="username">Receptnavn</label> 
			<input type="text"
				name="newName" id="username"
				value="<%out.print(prescription.getName());%>">
		</form>
		<div class="compList">
			Receptkomponenter:
			<table>
				<tr>
					<th></th>
					<th>Råvare ID</th>
					<th>Nettovægt</th>
					<th>Tollerance</th>
				</tr>
		<%			int i = 0;
					if (components != null && !components.isEmpty()){
						for (PrescriptionCompDTO comp : components){%>
				<tr>
					<td></td>
					<td>
					<%  String comIdError = (String) request.getAttribute("comIdError" + i); 
							if (comIdError != null && !comIdError.equals("")){ %>
						<div class ="error"> <% out.print(comIdError); %> </div> 
						<% 	} %>
					</td>
					<td>
					<%  String comNetError = (String) request.getAttribute("comNetError" + i); 
							if (comNetError != null && !comNetError.equals("")){ %>
						<div class ="error"> <% out.print(comNetError); %> </div> 
						<% 	} %>
					</td>
					<td>
					<%  String comTolError = (String) request.getAttribute("comTolError" + i); 
							if (comTolError != null && !comTolError.equals("")){ %>
						<div class ="error"> <% out.print(comTolError); %> </div> 
						<% 	} %>
					</td>
				</tr>
				<tr>
					<td>
						<button name="button" value="delete<%out.print(i);%>" 
						type="submit" form="main">Slet</button>
					</td>
					<td>
						<input type="text" name="comId<%out.print(i);%>"
							value="<%out.print(comp.getCommodityId());%>"
							form="main">
					</td>
					<td>
						<input type="text" name="netto<%out.print(i);%>"
							value="<%out.print(comp.getNomNetto());%>"
							form="main">
					</td>
					<td>
						<input type="text" name="tolerance<%out.print(i);%>"
							value="<%out.print(comp.getTolerance());%>"
							form="main">
					</td>
				</tr>
			<%			i++;
						} 
					} %>	
				<tr>
					<td>
						<input type="hidden" name="compCount"
							value="<%out.print(i);%>" form="main">
						<button name="button" value="new" 
							type="submit" form="main">Ny</button>
					</td>
				</tr>
			</table>
		</div>
		<%
				if (create) {
		%>
		<input type="submit" value="Opret" form="main">
		<%
			} else {
		%><input type="submit" value="Gem" form="main">
		<%
			}
		%>
			<input type="submit" name="button" value="Kontroller" form="main">
		<%
			}
		%>
		<div class="buttons">
			<A href="prescription_admin">Tilbage</A> <A href="login?logout=true">Log
				ud</A>
		</div>
	</div>
</body>
</html>