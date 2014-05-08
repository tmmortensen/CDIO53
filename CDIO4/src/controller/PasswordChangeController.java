package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;

/**
 * Servlet implementation class TestController
 */
public class PasswordChangeController extends HttpServlet {
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
		
		String sNewPword1 = request.getParameter("newPword1");
		String sNewPword2 = request.getParameter("newPword2");
		
		String dNewPword1 = null;
		String dNewPword2 = null;
		String dGenPword = null;
		
		if (sNewPword1 != null || sNewPword2 != null){
			dNewPword1 = sNewPword1;
			dNewPword2 = sNewPword2;
			if (dNewPword1.equals(dNewPword2)){
				try{
					dGenPword = dNewPword1;
				} catch (NumberFormatException e){
					error += "Password matcher ikke<br>";
				}
			} else
				dGenPword = null;
		}
		
		request.setAttribute("error", error);
		request.setAttribute("newPword1", sNewPword1);
		request.setAttribute("newPword2", sNewPword2);
		request.setAttribute("genPword", dGenPword);
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/password_change_boundary.jsp");
		dispatcher.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
