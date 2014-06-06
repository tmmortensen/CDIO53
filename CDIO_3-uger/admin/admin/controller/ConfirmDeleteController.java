package admin.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.Data;
import admin.data.OperatoerDTO;
import admin.data.UserInfo;

public class ConfirmDeleteController extends HttpServlet {
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

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			user = new User();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}

		if (!user.isLoggedIn()) {
			response.sendRedirect("login");
			return;
		}

		if (!user.isAdmin()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String error = "";
		OperatoerDTO opr;
		UserInfo userInfo;
		boolean success = false;
		
		int uid = -1;
		try {
			uid = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			error = "Bruger id er ugyldigt";
		}

		try {
			opr = data.getOperatoer(uid);
			userInfo = new UserInfo(opr);
			request.setAttribute("userInfo", userInfo);

			String confirmed = request.getParameter("confirmed");
			if (confirmed != null && confirmed.equals("true")) {
				try {
					data.deleteOperatoer(uid);
					success = true;
				} catch (DALException e) {
					error = "Der skete en fejl under sletning";
				}
			} else {
			}
		} catch (DALException e) {
			error = "Der findes ingen bruger med det givne id";
		}

		request.setAttribute("done", success);
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/confirm_delete_boundary.jsp");
		dispatcher.forward(request, response);
		return;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
