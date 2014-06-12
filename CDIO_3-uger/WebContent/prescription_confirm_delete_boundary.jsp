<%@ page language="java" import="java.util.*,admin.data.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request" />
<jsp:useBean id="prescription" class="admin.data.PrescriptionDTO"
	scope="request" />
<%
	boolean complete = (Boolean) request.getAttribute("complete");
	List<PrescriptionCompDTO> components = (List<PrescriptionCompDTO>) request
			.getAttribute("components");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Delete Prescription</title>
</head>
<body>
	<div class="dialog">
		<%	if (complete) { %>
		<h1>Denne recept er blevet slettet</h1>
		<%	} else if (!error.equals("")) { %>
		<h1>Fejl under sletning af recept!</h1>
		<div class="error">	<% out.print(error); %> </div>
		<%	} else { %>
		<h1>Er du sikker på at du vil slette denne recept ?</h1>
		<%	}
			if (prescription != null){ %>
		<table border="1">
			<tbody>
				<tr>
					<th>ID</th>
					<th>Navn</th>
				</tr>
				<tr>
				<%	out.println("\t\t\t\t\t<td>" + prescription.getId() + "</td>\n");
					out.println("\t\t\t\t\t<td>" + prescription.getName()
								+ "</td>\n"); %>
				</tr>
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
		<%		int i = 0;
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
			<%		i++;
					} 
				} %>	
			</table>
		</div>
		<%		if (!complete) { %>
		<form method="post">
			<input type="submit" value="Slet">
		</form>
		<%		}
			} %>
		<div class="buttons">
			<A href="prescription_admin">Tilbage</A> <A href="login?logout=true">Log
				ud</A>
		</div>
	</div>
</body>
</html>
