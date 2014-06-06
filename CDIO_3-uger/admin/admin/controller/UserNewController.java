package admin.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.UserData;
import admin.data.UserDTO;
import admin.data.UserInfo;

public class UserNewController extends HttpServlet {
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

		String sNewId = "";
		UserInfo info = new UserInfo();
		UserInfo error = new UserInfo();
		String idError = "";
		String majorError = "";
		boolean anyError = false;
		String password = UserDTO.generatePassword();

		if (request.getMethod().equals("POST")) {
			info = new UserInfo();
			
			sNewId = request.getParameter("newId");
			info.ini = request.getParameter("newIni");
			info.name = request.getParameter("newName");
			info.cpr = request.getParameter("newCPR");
			info.admin = (request.getParameter("newAdmin") != null &&
					request.getParameter("newAdmin").equals("true"));

			try{
				info.id = Integer.parseInt(sNewId);
			} catch (NumberFormatException e){
				info.id = 0;
				idError = "Det indtastede bruger id er ikke et tal";
				anyError = true;
			}
			
			try {
				UserDTO operator = new UserDTO(1,"AA","AA","0000000000",password);
				try {
					operator.setOprId(info.id);
				} catch (DALException e){
					idError = e.getMessage();
					anyError = true;
				}
				
				try {
					operator.setIni(info.ini);
				} catch (DALException e){
					error.ini = e.getMessage();
					anyError = true;
				}
				
				try {
					operator.setOprNavn(info.name);
				} catch (DALException e){
					error.name = e.getMessage();
					anyError = true;
				}
				
				try {
					operator.setCpr(info.cpr);
				} catch (DALException e){
					error.cpr = e.getMessage();
					anyError = true;
				}
				
				operator.setAdmin(info.admin);
				
				if (!anyError){
						try {
							data.createOperatoer(operator);
						} catch (DALException e) {
							idError = e.getMessage();
							anyError = true;
						}
				} else {
					try { 
						data.getOperatoer(info.id);
						idError = "Dette id er optaget";
					}
					catch (DALException e) {}
				}
			} catch (Exception e) {}
		} else {
			info.ini = "";
			info.name = "";
			info.cpr = "";

		}
		
		request.setAttribute("majorError", majorError);
		request.setAttribute("error", error);
		request.setAttribute("idError", idError);
		request.setAttribute("complete", !anyError && request.getMethod().equals("POST"));
		
		request.setAttribute("newId", sNewId);
		request.setAttribute("newPw", password);
		request.setAttribute("info", info);
		

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/new_user_boundary.jsp");
		dispatcher.forward(request, response);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
