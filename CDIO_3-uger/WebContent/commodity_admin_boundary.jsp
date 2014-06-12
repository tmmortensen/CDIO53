<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<CommodityDTO> commodityList = (List<CommodityDTO>) request.getAttribute("commodityList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Råvarer Administration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="adminDialog">
		<h1>Råvarer Administration </h1>
		<table border="1"><tbody>
			<tr>
				<th>ID</th>
				<th>Navn</th>
				<th>Leverandør</th>
				<th>Rediger</th>
				<th>Slet</th>
			</tr>
			<%
				for (CommodityDTO com : commodityList){
								out.println("\t\t\t\t<tr>\n");
								out.println("\t\t\t\t\t<td>" + com.getComId() + "</td>\n");
								out.println("\t\t\t\t\t<td>" + com.getComName() + "</td>\n");
								out.println("\t\t\t\t\t<td>" + com.getSupplier() + "</td>\n");
								out.println("\t\t\t\t\t<td><A href=\"commodity_edit?id=" + com.getComId() + "\">Rediger</A></td>\n");
								out.println("\t\t\t\t\t<td><A href=\"commodity_confirm_delete?id=" + com.getComId() + "\">Slet</A></td>\n");
								out.println("\t\t\t\t</tr>\n");
							}
			%>
		</tbody></table>
		<A href="commodity_edit?id=new">Opret ny råvare</A>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>