package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;

/**
 * Servlet implementation class TestController
 */
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IDataReadOnly data;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (Data) getServletContext().getAttribute("data");
		if (data == null){
			Data newdata = new Data();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			user = new User();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}
		
		if (!user.isLoggedIn()){
			response.sendRedirect("login");
			return;
		} 
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
