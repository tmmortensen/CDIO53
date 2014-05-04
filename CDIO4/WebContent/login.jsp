<%@ page language="java" import="java.util.*, data.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="data" class="data.Data" scope="application"/>
<jsp:useBean id="user" class="controller.User" scope="session"/>
<%
	boolean bLoginError = false;
	String sLoginError = "";

	String uid = "";
	String pword = "";
	
	String redirect = "mainmenu.jsp";
	if (!user.isinitialized()){
		user.init(data);
	}
	
	if (request.getMethod().equals("GET")){
		if (request.getParameter("Redirect") != null)
			redirect = request.getParameter("Redirect"); 
		String logout = request.getParameter("Logout");
		if (logout != null && logout.toLowerCase().equals("true")){
			user.logout();
		}
	}

	if (user.isLoggedIn())
		response.sendRedirect(redirect);
	
	if (request.getMethod().equals("POST")) { // brugeren har klikket på submit
		uid = request.getParameter("UserID");
		pword = request.getParameter("Password");
		redirect = request.getParameter("Redirect");
	
		try {
			int iUid = Integer.parseInt(uid);
			if (user.login(iUid, pword)){
				response.sendRedirect(redirect);
			} else{
				bLoginError = true;
				sLoginError = "Det indtastede bruger id og kodeord er ikke korrekt";	
			}
		} catch (Exception e){
			bLoginError = true;
			sLoginError = "Bruger ID er ikke et tal";	
		}
	}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div class="dialog">
		<h1>Intast bruger ID og kodeord </h1>
		<% if (bLoginError){ %> <div class="error"> <% out.print(sLoginError); %> </div> <%} %>
		<form method="post">
			<label for="userid">Bruger ID</label>
			<input type="text" name="UserID" id="userid"
				value="<% out.print(uid); %>">
			<label for="password">Kodeord</label>
			<input type="password" name="Password" id="password"
				value="<% out.print(pword); %>">
			<input type="hidden" name="Redirect" id="redirect"
				value="<% out.print(redirect); %>">
			<input type="submit" value="Login">
		</form>
	</div>
	<%--Kun til test--%>
	<%
		out.print("This page is only a JSP page<BR>");
		out.print("Current Users <BR>");
		List<OperatoerDTO> operators = data.getOperatoerList();
		if(operators.isEmpty()){
			data.createDefaultOperators();
			operators = data.getOperatoerList();
		}
		
		for(OperatoerDTO op : operators){
			out.print("ID: " + op.getOprId() + " PW: " + op.getPassword() + "<BR>");
		}
	%>


</body>
</html>