package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;

/**
 * Servlet implementation class TestController
 */
public class PasswordChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Data data;
       
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

		String sUid = request.getParameter("userId");
		String pword = request.getParameter("password");
		String newPword1 = request.getParameter("newPword1");
		String newPword2 = request.getParameter("newPword2");
		String idError = null;
		String pwError = null;
		String npwError = null;
		
		if (sUid != null) {
				int iUid = Integer.parseInt(sUid);
				if (!user.checkId(iUid)){
//					idError = "Det indtastede bruger id er ikke korrekt";
					idError = sUid;
				}
			}
		
		if (pword != null){
				if (!user.checkPassword(pword)){
//					pwError = "Det indtastede password er ikke korrekt";
					pwError = "password er: " + pword;
				}
		}
		
		if (newPword1 != null || newPword2 != null){
		
			if (newPword1.equals(newPword2)){
			
					pword = newPword1;				
			}
			else {
				npwError = "De intastede passwords matcher ikke";
			}
		}
		
		request.setAttribute("newPword1", newPword1);
		request.setAttribute("newPword2", newPword2);
		request.setAttribute("password", pword);
		request.setAttribute("userId", sUid);
		request.setAttribute("idError", idError);
		request.setAttribute("pwError", pwError);
		request.setAttribute("npwError", npwError);
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/password_change_boundary.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
