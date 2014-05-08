package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import data.DALException;
import data.Data;

public class ConfirmDelete extends HttpServlet {
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

		Integer uid = Integer.parseInt(request.getParameter("UserID"));

		try {
			data.getOperatoer(uid);
			request.setAttribute("userID", uid);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/confirm_delete.jsp");
			dispatcher.forward(request, response);

		} catch (DALException e) {
			e.printStackTrace();
		}

		try {
			data.deleteOperatoer(uid);
		} catch (DALException e) {

			e.printStackTrace();

		}

	}
}
