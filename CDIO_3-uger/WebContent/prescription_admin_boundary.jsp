<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<PrescriptionDTO> prescriptions = (List<PrescriptionDTO>) request.getAttribute("prescriptionList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bruger Administration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="adminDialog">
		<h1>Recept Administration </h1>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Navn</th>
				<th>Rediger</th>
				<th>Slet</th>
			</tr>
			<%
				for (PrescriptionDTO pre : prescriptions){
								out.println("\t\t\t\t<tr>\n");
								out.println("\t\t\t\t\t<td>" + pre.getId() + "</td>\n");
								out.println("\t\t\t\t\t<td>" + pre.getName() + "</td>\n");
								out.println("\t\t\t\t\t<td><A href=\"prescription_edit?id=" + pre.getId() + "\">Rediger</A></td>\n");
								out.println("\t\t\t\t\t<td><A href=\"prescription_confirm_delete?id=" + pre.getId() + "\">Slet</A></td>\n");
								out.println("\t\t\t\t</tr>\n");
							}
			%>
		</tbody></table>
		<A href="prescription_edit?id=new">Opret ny recept</A>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>