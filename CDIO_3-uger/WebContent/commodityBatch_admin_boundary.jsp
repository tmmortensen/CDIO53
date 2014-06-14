<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<CommodityBatchDTO> cBList = (List<CommodityBatchDTO>) request.getAttribute("commodityBatchList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Råvarer Batch Administration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="adminDialog">
		<h1>Råvarer Batch Administration </h1>
		<table border="1"><tbody>
			<tr>
				<th>Batch ID</th>
				<th>Råvare ID</th>
				<th>Mængde</th>
				<th>Rediger</th>
				<th>Slet</th>
			</tr>
			<tr>
			<%
				for (CommodityBatchDTO comBatch : cBList){
								out.println("\t\t\t\t\t<td>" + comBatch.getCommodityBatchId() + "</td>\n");
								out.println("\t\t\t\t\t<td>" + comBatch.getCommodityId() + "</td>\n");
								out.println("\t\t\t\t\t<td>" + comBatch.getAmount() + "</td>\n");
								out.println("\t\t\t\t\t<td><A href=\"commodityBatch_edit?id=" + comBatch.getCommodityBatchId() + "\">Rediger</A></td>\n");
								out.println("\t\t\t\t\t<td><A href=\"commodityBatch_confirm_delete?id=" + comBatch.getCommodityBatchId() + "\">Slet</A></td>\n");
							}
			%>
			</tr>
		</tbody></table>
		<A href="commodityBatch_edit?id=new">Opret ny råvare</A>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>