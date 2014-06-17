<%@ page language="java" import="java.util.*,admin.data.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request" />
<jsp:useBean id="date" class="java.lang.String" scope="request" />
<jsp:useBean id="sumTara" class="java.lang.String" scope="request" />
<jsp:useBean id="sumNetto" class="java.lang.String" scope="request" />
<jsp:useBean id="product" class="admin.data.ProductBatchDTO"
	scope="request" />
<%
	List<ProductListItem> components = (List<ProductListItem>) request
			.getAttribute("components");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Rediger Produktbatch</title>
</head>
<body>
<div class="print">
	<% if (!error.equals("")){ out.print(error);} %>
	<table border="0"><tbody>
		<tr>
			<td>Udskrevet</td>
			<td><% out.print(date);%></td>	
		</tr>
		<tr>
			<td>Produkt Batch ID</td>
			<td><% out.print(product.getPbId());%></td>
		</tr>		
		<tr>
			<td>Recept ID</td>
			<td><% out.print(product.getPrescriptionId());%></td>
		</tr>		
	</tbody></table>
	<table border="0"><tbody>
	<%	for (ProductListItem item : components) {%>	
		<tr><td><br><br></td></tr>
		<tr><td>
			<table border="0"><tbody>
				<tr>
					<td>Råvare ID</td>
					<td><% out.print(item.commodityId);%></td>	
				</tr>
				<tr>
					<td>Råvare Navn</td>
					<td><% out.print(item.commodityName);%></td>
				</tr>
			</tbody></table>
			<hr>
			<table border="0"><tbody>
				<tr>
					<td>Mængde</td>
					<td>Tolerance</td>
					<td>Tara</td>
					<td>Netto</td>
					<td>Råvare Batch</td>
					<td>Operatør</td>
				</tr>
				<tr>
					<td><% out.print(item.ammount);%></td>
					<td><% out.print(item.tolerance);%></td>
					<td><% if (item.tara != 0 ) out.print(item.tara);%></td>
					<td><% if (item.netto != 0 ) out.print(item.netto);%></td>
					<td><% if (item.commodityBatch != 0 ) out.print(item.commodityBatch);%></td>
					<td><% out.print(item.operator);%></td>
				</tr>
			</tbody></table>
		</td></tr>
	<%	} %>		
	<tr><td>
		<BR><BR><BR>		
		<table border="0"><tbody>
			<tr>
				<td>Sum Tara</td>
				<td><% out.print(sumTara);%></td>	
			</tr>
			<tr><td><BR></td></tr>		
			<tr>
				<td>Sum Netto</td>
				<td><% out.print(sumNetto);%></td>
			</tr>		
			<tr><td><BR><BR></td></tr>		
			<tr>
				<td>Status</td>
				<td><% out.print(product.getStatus().uiName());%></td>
			</tr>		
			<tr>
				<td>Oprettelsesdato</td>
				<td><% out.print(product.getCreationDate());%></td>
			</tr>		
		</tbody></table>
	</td></tr>
	</tbody></table>
	<A href="productBatch_admin">Tilbage</A>
</div> 
</body>	
</html>