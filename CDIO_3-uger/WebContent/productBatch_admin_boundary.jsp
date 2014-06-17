<%@ page language="java" import="java.util.*,admin.data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<ProductBatchDTO> products = (List<ProductBatchDTO>) request.getAttribute("productList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produktionsbatch Administration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="product_admin">
		<h1>Produktionsbatch Administration </h1>
		<table border="1"><tbody>
			<tr>
				<th>Batch ID</th>
				<th>Recept ID</th>
				<th>Bruger ID</th>
				<th>Oprettet</th>
				<th>Status</th>
				<th></th>
				<th></th>
			</tr>
			<%
				for (ProductBatchDTO product : products){
					out.println("\t\t\t\t<tr>\n");
					out.println("\t\t\t\t\t<td>" + product.getPbId() + "</td>\n");
					out.println("\t\t\t\t\t<td>" + product.getPrescriptionId() + "</td>\n");
					out.println("\t\t\t\t\t<td>" + product.getUserId() + "</td>\n");
					out.println("\t\t\t\t\t<td>" + product.getCreationDate() + "</td>\n");
					out.println("\t\t\t\t\t<td class=\"status\">" + product.getStatus().uiName() + "</td>\n");
					if (product.getStatus() == StatusType.NEW){			
						out.println("\t\t\t\t\t<td><A href=\"productBatch_edit?id=" + product.getPbId() + "\">Rediger</A></td>\n");
						out.println("\t\t\t\t\t<td><A href=\"productBatch_confirm_delete?id=" + product.getPbId() + "\">Slet</A></td>\n");
					} else if (product.getStatus() == StatusType.PAUSED) {
						out.println("\t\t\t\t\t<td><A href=\"productBatch_edit?id=" + product.getPbId() + "\">Rediger</A></td>\n");
						out.println("\t\t\t\t\t<td></td>\n");
					} else {
						out.println("\t\t\t\t\t<td><A href=\"productBatch_edit?id=" + product.getPbId() + "\">Vis Detajler</A></td>\n");
						out.println("\t\t\t\t\t<td></td>\n");
					}
					out.println("\t\t\t\t\t<td><A href=\"productBatch_print?id=" + product.getPbId() + "\">Print</A></td>\n");
					out.println("\t\t\t\t</tr>\n");
				}
			%>
		</tbody></table>
		<A href="productBatch_edit?id=new">Opret nyt produktbatch</A>
		<div class="buttons">
			<A href="mainmenu">Hovedmenu</A>
			<A href="login?logout=true">Log ud</A>
		</div>
	</div>
</body>
</html>