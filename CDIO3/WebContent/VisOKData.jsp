<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if (request.getMethod().equals("POST")) { // brugeren har tastet på submit
response.sendRedirect("InputForm.jsp");
}
%>
Den indtastede værdi er godkendt: Du indtastede
<%= request.getParameter("alder_resultat") %> år
<form method="GET" action="InputForm.jsp">
<input type="submit" value="OK">
</form>

</body>
</html>