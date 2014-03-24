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
String alder = request.getParameter("alder");
String error = validate(alder);
if (error.equals(""))
{
System.out.print(alder);
response.sendRedirect("VisOKData.jsp?alder_resultat="+alder);
}
else
response.sendRedirect("VisError.jsp?error_resultat="+error);
}
%>
<form method="POST" action="InputForm.jsp">
Alder:
<input type="text" name="alder" value="Indtast din alder her">
<input type="submit" value="OK">
</form>

</body>
</html>