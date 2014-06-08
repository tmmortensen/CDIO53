package admin.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.UserData;
import admin.data.UserDTO;
import admin.data.UserInfo;
import admin.data.UserType;

public class UserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserData data;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		data = (UserData) getServletContext().getAttribute("data");
		if (data == null) {
			data = new UserData();
			getServletContext().setAttribute("data", data);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserSession userSession = (UserSession) request.getSession()
				.getAttribute("user");
		if (userSession == null) {
			userSession = new UserSession();
			userSession.init(data);
			request.getSession().setAttribute("user", userSession);
		}

		if (!userSession.isLoggedIn()) {
			response.sendRedirect("login");
			return;
		}

		if (!userSession.isAdmin()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		String sNewId = "";
		String sNewAccess = "";
		UserInfo info = new UserInfo();
		UserInfo old = new UserInfo();
		UserInfo error = new UserInfo();
		String idError = "";
		String accessError = "";
		String majorError = "";
		boolean anyError = false;

		String sUserId = request.getParameter("id");
		int iUserId = 0;

		try {
			iUserId = Integer.parseInt(sUserId);
		} catch (NumberFormatException e) {
			majorError = "Bruger id er ugyldigt";
			anyError = true;
		}

		if (request.getMethod().equals("GET")) {
			try {
				UserDTO user = data.getUser(iUserId);
				sNewId = sUserId;
				info = new UserInfo(user);
				request.setAttribute("info", info);
			} catch (DALException e1) {
				majorError = "Der findes ingen bruger med det angivne id";
				anyError = true;
			}
		} else {
			info = new UserInfo();

			sNewId = request.getParameter("newId");
			info.ini = request.getParameter("newIni");
			info.name = request.getParameter("newName");
			info.cpr = request.getParameter("newCPR");
			sNewAccess = request.getParameter("newAdmin");

			try {
				info.id = Integer.parseInt(sNewId);
			} catch (NumberFormatException e) {
				info.id = 0;
				idError = "Det indtastede bruger id er ikke et tal";
				anyError = true;
			}

			UserDTO user;
			try {
				user = data.getUser(iUserId);

				try {
					user.setUserId(info.id);
				} catch (DALException e) {
					idError = e.getMessage();
					anyError = true;
				}

				try {
					user.setIni(info.ini);
				} catch (DALException e) {
					error.ini = e.getMessage();
					anyError = true;
				}

				try {
					user.setUsername(info.name);
				} catch (DALException e) {
					error.name = e.getMessage();
					anyError = true;
				}

				try {
					user.setCpr(info.cpr);
				} catch (DALException e) {
					error.cpr = e.getMessage();
					anyError = true;
				}

				try {
					info.access = UserType.valueOf(sNewAccess);
					user.setAccessLevel(info.access);
				} catch (IllegalArgumentException e) {
					info.access = UserType.OPERATOR;
					accessError = "Der skete en fejl med brugertypen";
					anyError = true;
				}

				if (!anyError) {
					old = new UserInfo(data.getUser(iUserId));
					if (iUserId == info.id) {
						try {
							data.updateUser(user);
						} catch (DALException e) {
							idError = e.getMessage();
							anyError = true;
						}
					} else {
						try {
							data.createUser(user);
							data.deleteUser(iUserId);
						} catch (DALException e) {
							idError = e.getMessage();
							anyError = true;
						}
					}
				} else if (iUserId != info.id) {
					try {
						data.getUser(info.id);
						idError = "Dette id er optaget";
					} catch (DALException e) {
					}
				}
			} catch (Exception e) {
				majorError = "Brugeren med det givne id findes ikke længere";
			}
		}

		request.setAttribute("majorError", majorError);
		request.setAttribute("error", error);
		request.setAttribute("idError", idError);
		request.setAttribute("accessError", accessError);
		request.setAttribute("complete", !anyError
				&& request.getMethod().equals("POST"));

		request.setAttribute("newId", sNewId);
		request.setAttribute("oldId", sUserId);
		request.setAttribute("info", info);
		request.setAttribute("old", old);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/edit_user_boundary.jsp");
		dispatcher.forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
