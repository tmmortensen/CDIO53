<%@ page language="java" import="java.util.*, java.text.*, java.lang.*" 
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String rNum = "";
	String vNum = "";
	String vName = "";
	String netto = "";
	String tol = "";

	String rNumErrorString = "Receptnummer skal være et helt tal mellem 1 og 99999999";
	String vNumErrorString = "Varenummer skal være et helt tal mellem 1 og 99999999";
	String vNameErrorString = "Varenavn skal være mellem 2 og 20 karaterer langt";
	String nettoErrorString = "Nettovægt skal være mellem 50g og 6000g";
	String tolErrorString = "Tolerancen skal være mellem 0.1% og 10% og mindst 1g";

	boolean rNumError = false;
	boolean vNumError = false;
	boolean vNameError = false;
	boolean nettoError = false;
	boolean tolError = false;
	boolean showInput = true;
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Oprettelse af recept</title>
<link rel="stylesheet" type="text/css" href="Recept.css">
</head>
<body>
	<%
		if (request.getMethod().equals("POST")) { // brugeren har klikket på submit
			showInput = false;
			rNum = request.getParameter("Receptnummer");
			vNum = request.getParameter("Varenummer");
			vName = request.getParameter("Varenavn");
			netto = request.getParameter("Nettovægt");
			tol = request.getParameter("Tolerance");
			
			
			rNum = rNum.replaceAll("\\s","");
			vNum = vNum.replaceAll("\\s","");
			vName = vName.replaceAll("\\s","");
			netto = netto.replaceAll("\\s","").replaceAll(",",".");
			tol = (tol.replaceAll("\\s","")).replaceAll(",",".");
			
			
			
			
			//kontrol af receptnummer
			try {
				int rNumber = Integer.parseInt(rNum);
				if (rNumber < 1 || rNumber > 99999999){
					if(rNumber < 1)
						rNumErrorString += "<br>Det Indtastede tal er mindre end 1";
					else
						rNumErrorString += "<br>Det Indtastede tal er større end 99999999";
					rNumError = true;
					showInput = true;
				}
			} catch(Exception e){
				rNumErrorString += "<br>Det Indtastede kan ikke læses som et tal";
				rNumError = true;
				showInput = true;
			}
			
			//kontrol af varenummer
			try {
				int vNumber = Integer.parseInt(vNum);
				if (vNumber < 1 || vNumber > 99999999){
					if(vNumber < 1)
						vNumErrorString += "<br>Det Indtastede tal er mindre end 1";
					else
						vNumErrorString += "<br>Det Indtastede tal er større end 99999999";
					vNumError = true;
					showInput = true;
				}
			} catch(Exception e){
				vNumErrorString += "<br>Det Indtastede kan ikke læses som et tal";
				vNumError = true;
				showInput = true;
			}
			
			//kontrol af varenavn
			if (vName.length() < 2 || vName.length() > 20){
				if (vName.length() < 2)
					vNameErrorString += "<br>Navnet er for kort";
				else
					vNameErrorString += "<br>Navnet er for langt";
				vNameError = true;
				showInput = true;
			}
			
			//kontrol af nettovægt
			try {
				double dNetto = Double.parseDouble(netto);
				if (dNetto < 50 || dNetto > 6000){
					if(dNetto < 50)
						nettoErrorString += "<br>Det Indtastede tal er mindre end 50";
					else
						nettoErrorString += "<br>Det Indtastede tal er større end 6000";
					nettoError = true;
					showInput = true;
				}
			} catch(Exception e){
				nettoErrorString += "<br>Det Indtastede kan ikke læses som et tal";
				nettoError = true;
				showInput = true;
			}
			
			//kontrol af tolerance
			try {
				double dTol = Double.parseDouble(tol);
				double dNetto = Double.parseDouble(netto);
				double tolGram = dNetto * dTol * 0.01;
				
				if (dTol < 0.1 || dTol > 10 || tolGram < 1){
					if ( tolGram < 1 ){
						tolErrorString += "<br>Tollerancen er mindre end 1g";
						tolErrorString += "<br>(" + (1/dNetto*100) + "% når nettovægt er "+ dNetto +")";
					}
					tolError = true;
					showInput = true;
				}
			} catch(Exception e){
				try{double dTol = Double.parseDouble(tol);}
				catch(Exception e2){
					tolErrorString += "<br>Det Indtastede kan ikke læses som et tal";
				}
				tolError = true;
				showInput = true;
			}
			
			
		}

		String dateString = DateFormat.getDateInstance().format(new Date());

		if (showInput){
	%>
	<div class="dialog">
		<h1>Oprettelse af Recept</h1>
		<form method="post">
		<% if (rNumError){ %> <div class="error"> <% out.print(rNumErrorString); %> </div> <%} %>
			<label for="rNumber">Receptnummer</label>
			<input type="text" name="Receptnummer" id="rNumber"
				value="<% out.print(rNum); %>">
		<% if (vNumError){ %> <div class="error"> <% out.print(vNumErrorString); %> </div> <%} %>
			<label for="vNumber">Varenummer</label>
			<input type="text" name="Varenummer" id="vNumber"
				value="<% out.print(vNum); %>">
		<% if (vNameError){ %> <div class="error"> <% out.print(vNameErrorString); %> </div> <%} %>
			<label for="vName">Varenavn</label>
			<input type="text" name="Varenavn" id="vName"
				value="<% out.print(vName); %>">
		<% if (nettoError){ %> <div class="error"> <% out.print(nettoErrorString); %> </div> <%} %>
			<label for="netWeight">Nominel nettovægt i g</label>
			<input type="text" name="Nettovægt" id="netWeight"
				value="<% out.print(netto); %>">
		<% if (tolError){ %> <div class="error"> <% out.print(tolErrorString); %> </div> <%} %>
			<label for="tolerance">Tolerance (på netto) i %</label>
			<input type="text" name="Tolerance" id="tolerance"
				value="<% out.print(tol); %>">
			<label for="date">Dato</label>
			<input type="text" name="Dato" id="date" 
				readonly value="<%out.print(dateString);%>">
			<input type="submit" value="Opret">
		</form>
	</div>
	<%	} else {%>
	<div class="dialog">
		<h1>Recept iorden</h1>
		<label for="rNumber">Receptnummer</label>
		<span><% out.print(rNum); %></span>
		<label for="vNumber">Varenummer</label>
		<span><% out.print(vNum); %></span>
		<label for="vName">Varenavn</label>
		<span><% out.print(vName); %></span>
		<label for="netWeight">Nominel nettovægt i g</label>
		<span><% out.print(netto); %></span>
		<label for="tolerance">Tolerance (på netto) i %</label>
		<span><% out.print(tol); %></span>
		<label for="date">Dato</label>
		<span><% out.print(dateString);%></span>
		<a href="Recept.jsp"> Ny recept   </a>
	</div>
	<%	} %>
</body>
</html>