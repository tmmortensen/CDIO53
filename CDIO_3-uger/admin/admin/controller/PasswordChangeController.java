package admin.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.UserDTO;

/**
 * Servlet implementation class TestController
 */
public class PasswordChangeController extends AbstractController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserSession user = (UserSession) request.getSession().getAttribute("user");
		if (user == null) {
			user = new UserSession();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}

		if (!user.isLoggedIn()) {
			response.sendRedirect("login");
			return;
		}

		String pword = request.getParameter("password");
		String newPword1 = request.getParameter("newPword1");
		String newPword2 = request.getParameter("newPword2");
		String pwError = null;
		String npwError = null;
		UserDTO opr = null;
		boolean input = false;

		if (pword != null) {
			input = true;
			if (!pword.equals("")) {
				int id = user.getId();
				try {
					opr = data.getUser(id);
					if (!opr.getPassword().equals(pword)) {
						pwError = "Det indtastede password er ikke korrekt";
					}

				} catch (DALException e) {
					response.sendRedirect("login");
					return;
				}
			} else {
				pwError = "Du skal indtaste dit nuværende kodeord";
			}
		}

		if (newPword1 != null || newPword2 != null) {
			input = true;
			if (newPword1.equals(newPword2)) {
				npwError = UserDTO.chkPassWithMsg(newPword1);
			} else {
				npwError = "De to nye passwords er ikke ens";
			}

		}

		boolean success = false;
		if (input && pwError == null && npwError == null) {
			try {
				opr.setPassword(newPword1);
				data.updateUser(opr);
				success = true;
				user.login(user.getId(), newPword1);
			} catch (DALException e) {
			}
		}

		if (npwError != null)
			npwError = npwError.replace("\n", "<BR>");
		request.setAttribute("success", success);
		request.setAttribute("newPword1", newPword1);
		request.setAttribute("newPword2", newPword2);
		request.setAttribute("password", pword);
		request.setAttribute("pwError", pwError);
		request.setAttribute("npwError", npwError);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/password_change_boundary.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
