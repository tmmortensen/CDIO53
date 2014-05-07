package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import data.DALException;
import data.Data;

public class PasswordChangeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Data data;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		data = (Data) getServletContext().getAttribute("data");
		if (data == null) {
			data = new Data();
			getServletContext().setAttribute("data", data);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String err = "";
		//User user = (User) request.getSession().getAttribute("user");

		Integer uid = Integer.parseInt(request.getParameter("UserID"));
		String password = request.getParameter("password");

		if (uid != null) {
			try {
				data.getOperatoer(uid);
				request.setAttribute("userID", uid);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/edit_user.jsp");
				dispatcher.forward(request, response);

			} catch (DALException e) {
				err = e.getMessage();

			}
		}
		
		if(uid!=null && password!= null){
			
			
			
			
		}

		request.setAttribute("data", data);
		request.setAttribute("error", err);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/edit_user.jsp");
		dispatcher.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
