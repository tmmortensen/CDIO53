<%@ page language="java" import="java.util.*,admin.data.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="error" class="java.lang.String" scope="request" />
<jsp:useBean id="product" class="admin.data.ProductBatchDTO"
	scope="request" />
<%
	boolean complete = (Boolean) request.getAttribute("complete");
	List<ProductBatchCompDTO> components = (List<ProductBatchCompDTO>) request
			.getAttribute("components");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Slet Produktionsbatch</title>
</head>
<body>
	<div class="dialog">
		<%	if (complete) { %>
		<h1>Dette produktbatch er blevet slettet</h1>
		<%	} else if (!error.equals("")) { %>
		<h1>Fejl under sletning af produktbatch!</h1>
		<div class="error">	<% out.print(error); %> </div>
		<%	} else { %>
		<h1>Er du sikker på at du vil slette dette produktbatch ?</h1>
		<%	}
			if (product != null){ %>
		<table border="1">
			<tbody>
				<tr>
					<th>Batch ID</th>
					<th>Recept ID</th>
					<th>Status</th>
				</tr>
				<tr>
				<%	out.println("\t\t\t\t\t<td>" + product.getPbId() + "</td>\n");
					out.println("\t\t\t\t\t<td>" + product.getPrescriptionId()	+ "</td>\n"); 
					out.println("\t\t\t\t\t<td>" + product.getStatus().uiName() + "</td>\n"); %>
				</tr>
			</tbody>
		</table>
		<div class="compList">
			Foretagede afvejninger:
			<table>
				<tr>
					<th>Råvarebatch ID</th>
					<th>Operatør ID</th>
					<th>Nettovægt</th>
					<th>Tara vægt</th>
				</tr>
		<%		if (components != null && !components.isEmpty()){
					for (ProductBatchCompDTO comp : components){%>
				<tr>
					<td>
						<%out.print(comp.getCommoditybatch_id());%>
					</td>
					<td>
						<%out.print(comp.getUser_id());%>
					</td>
					<td>
						<%out.print(comp.getNetto());%>
					</td>
					<td>
						<%out.print(comp.getTara());%>
					</td>
				</tr>
			<%		} 
				} %>	
			</table>
		</div>
		<%		if (!complete && error.equals("")) { %>
		<form method="post">
			<input type="submit" value="Slet">
		</form>
		<%		}
			} %>
		<div class="buttons">
			<A href="productBatch_admin">Tilbage</A> 
			<A href="login?logout=true">Log	ud</A>
		</div>
	</div>
</body>
</html>
