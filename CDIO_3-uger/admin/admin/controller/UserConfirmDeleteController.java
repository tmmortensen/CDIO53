package admin.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.UserDTO;
import admin.data.UserInfo;
import admin.data.UserType;

public class UserConfirmDeleteController extends AbstractController {
	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.ADMIN;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		String error = "";
		UserDTO opr;
		UserInfo userInfo;
		boolean success = false;
		
		int uid = -1;
		try {
			uid = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			error = "Bruger id er ugyldigt";
		}

		try {
			opr = users.getUser(uid);
			userInfo = new UserInfo(opr);
			request.setAttribute("userInfo", userInfo);

			String confirmed = request.getParameter("confirmed");
			if (confirmed != null && confirmed.equals("true")) {
				try {
					users.deleteUser(uid);
					success = true;
				} catch (DALException e) {
					error = "Der skete en fejl under sletning<BR>";
					error += e.getMessage();
				}
			} else {
			}
		} catch (DALException e) {
			error = "Der findes ingen bruger med det givne id";
		}

		request.setAttribute("done", success);
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/user_confirm_delete_boundary.jsp");
		dispatcher.forward(request, response);
		return;
	}
}
