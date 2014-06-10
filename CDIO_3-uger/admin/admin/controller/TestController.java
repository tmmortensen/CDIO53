package admin.controller;

import admin.data.UserType;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Servlet implementation class TestController
 */
public class TestController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return null;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String error = "";
		
		String sBruto = request.getParameter("bruto");
		String sTara = request.getParameter("tara");
		
		double dBruto = 0;
		double dTara = 0;
		double dNetto = 0;
		
		if (sBruto != null || sTara != null){
			if (sBruto != null && !sBruto.equals("")){
				sBruto = sBruto.replace(',', '.');
				try{
					dBruto = Double.parseDouble(sBruto);
				} catch (NumberFormatException e){
					error += "Den intastede brutoværdi kan ikke læses som et tal<br>";
				}
			} else
				sBruto = "";
				
			if (sTara != null && !sTara.equals("")){
				sTara = sTara.replace(',', '.');
				try{
					dTara = Double.parseDouble(sTara);
				} catch (NumberFormatException e){
					error += "Den intastede taraværdi kan ikke læses som et tal";
				}
			} else
				sTara = "";
		}
		
		dNetto = dBruto - dTara;
		
		request.setAttribute("error", error);
		request.setAttribute("bruto", sBruto);
		request.setAttribute("tara", sTara);
		request.setAttribute("netto", dNetto);
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/test_boundary.jsp");
		dispatcher.forward(request, response);


	}

}
