package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;

public class PasswordChangeController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IDataReadOnly data;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (IDataReadOnly) getServletContext().getAttribute("data");
		if (data == null){
			Data newdata = new Data();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			user = new User();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}
		
		String passwordError = "";
		
		if (!user.isinitialized()){
			user.init(data);
		}

		String redirect;
		if (request.getParameter("redirect") != null)
			redirect = request.getParameter("redirect");
		else
			redirect = "mainmenu";

		String curPword = request.getParameter("curPword");
		String newPword1 = request.getParameter("newPword1");
		String newPword2 = request.getParameter("newPword2");

		String logout = request.getParameter("logout");
		if (logout != null && logout.toLowerCase().equals("true")){
			user.logout();
		}

		if (user.isLoggedIn()){
			response.sendRedirect(redirect);
			return;
		}

		if (newPword1 != null && newPword2 != null)
			try {
				if (newPword1.equals(newPword2)){
					response.sendRedirect(redirect);
					return;
				} else{
					passwordError = "De indtastede password matcher ikke";	
				}
			} catch (Exception e){
				passwordError = "Password overholder ikke gældende regler";	
			}
		else {
			newPword1 = "";
			newPword2 = "";
		}
		
		// TODO fjern dette
		request.setAttribute("data", data);
		// ^^ er kun til test
		
		request.setAttribute("error", passwordError);
		request.setAttribute("userId", curPword);
		request.setAttribute("newPword1", newPword1);
		request.setAttribute("newPword2", newPword2);
		request.setAttribute("redirect", redirect);
		
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/password_change.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
}
