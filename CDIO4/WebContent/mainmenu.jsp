<%@ page language="java" import="java.util.*, data.*, controller.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="data" class="data.Data" scope="application"/>
<jsp:useBean id="user" class="controller.User" scope="session"/>
<%
	if (user.isLoggedIn()){
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mainmenu</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%
	MainMenu menu = new MainMenu();
	ArrayList<MenuOption> options = menu.menuOptions(user);
	for(MenuOption option: options){
		out.print("<A href=\"" + option.url + "\">" + option.name + "</A><BR>" ); 
	}
%>
</body>
</html>
<%
	} else 
		response.sendRedirect("login.jsp?Redirect=mainmenu.jsp");
%>