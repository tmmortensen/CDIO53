package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;

public class LoginController extends HttpServlet{

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
		
		String loginError = "";
		
		if (!user.isinitialized()){
			user.init(data);
		}

		String redirect;
		if (request.getParameter("Redirect") != null)
			redirect = request.getParameter("Redirect");
		else
			redirect = "mainmenu";

		String uid = request.getParameter("UserID");
		String pword = request.getParameter("Password");

		String logout = request.getParameter("Logout");
		if (logout != null && logout.toLowerCase().equals("true")){
			user.logout();
		}

		if (user.isLoggedIn()){
			response.sendRedirect(redirect);
			return;
		}

		if (uid != null && pword != null)
			try {
				int iUid = Integer.parseInt(uid);
				if (user.login(iUid, pword)){
					response.sendRedirect(redirect);
					return;
				} else{
					loginError = "Det indtastede bruger id og kodeord er ikke korrekt";	
				}
			} catch (Exception e){
				loginError = "Bruger ID er ikke et tal";	
			}
		else {
			uid = "";
			pword = "";
		}
		
		// TODO fjern dette
		request.setAttribute("data", data);
		// ^^ er kun til test
		
		request.setAttribute("error", loginError);
		request.setAttribute("userID", uid);
		request.setAttribute("password", pword);
		request.setAttribute("redirect", redirect);
		
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/login_boundary.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
}
