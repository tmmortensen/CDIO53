<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<UserInfo> users = (List<UserInfo>) request.getAttribute("userlist");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bruger Administration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="adminDialog">
		<h1>Bruger Administration </h1>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Initialer</th>
				<th>Navn</th>
				<th>CPR-nummer</th>
				<th>Brugertype</th>
				<th>Rediger</th>
				<th>Slet</th>
			</tr>
			<%
				for (UserInfo user : users){
						out.println("\t\t\t\t<tr>\n");
						out.println("\t\t\t\t\t<td>" + user.id + "</td>\n");
						out.println("\t\t\t\t\t<td>" + user.ini + "</td>\n");
						out.println("\t\t\t\t\t<td>" + user.name + "</td>\n");
						out.println("\t\t\t\t\t<td>" + user.cpr + "</td>\n");
						out.println("\t\t\t\t\t<td>" + user.access.uiName() +"</td>\n");
						out.println("\t\t\t\t\t<td><A href=\"" + user.editURL + "\">Rediger</A></td>\n");
						out.println("\t\t\t\t\t<td><A href=\"" + user.deleURL + "\">Slet</A></td>\n");
						out.println("\t\t\t\t</tr>\n");
					}
			%>
		</tbody></table>
		<A href="user_edit?id=new">Opret ny bruger</A>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>