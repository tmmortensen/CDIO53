<%@ page language="java" import="java.util.*, data.*, controller.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<MenuOption> menuChoices = (ArrayList<MenuOption>)request.getAttribute ("Menulist");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mainmenu</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="main_menu">
	<h1>Hovedmenu</h1>
<%
	for(MenuOption option: menuChoices){
		out.print("<A href=\"" + option.url + "\">" + option.name + "</A><BR>" ); 
	}
%>
</div>
</body>
</html>
