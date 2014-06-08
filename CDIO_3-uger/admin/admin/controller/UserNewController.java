package admin.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.DALException;
import admin.data.UserDTO;
import admin.data.UserInfo;
import admin.data.UserType;

public class UserNewController extends AbstractController {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserSession userSession = (UserSession) request.getSession().getAttribute("user");
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
		UserInfo error = new UserInfo();
		String idError = "";
		String accessError = "";
		String majorError = "";
		boolean anyError = false;
		String password = UserDTO.generatePassword();

		if (request.getMethod().equals("POST")) {
			info = new UserInfo();
			
			sNewId = request.getParameter("newId");
			info.ini = request.getParameter("newIni");
			info.name = request.getParameter("newName");
			info.cpr = request.getParameter("newCPR");
			sNewAccess = request.getParameter("newAdmin");

			try{
				info.id = Integer.parseInt(sNewId);
			} catch (NumberFormatException e){
				info.id = 0;
				idError = "Det indtastede bruger id er ikke et tal";
				anyError = true;
			}
			
			try {
				UserDTO user = new UserDTO(1,"AA","AA","0000000000",password);
				try {
					user.setUserId(info.id);
				} catch (DALException e){
					idError = e.getMessage();
					anyError = true;
				}
				
				try {
					user.setIni(info.ini);
				} catch (DALException e){
					error.ini = e.getMessage();
					anyError = true;
				}
				
				try {
					user.setUsername(info.name);
				} catch (DALException e){
					error.name = e.getMessage();
					anyError = true;
				}
				
				try {
					user.setCpr(info.cpr);
				} catch (DALException e){
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
				
				if (!anyError){
					try {
						data.createUser(user);
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				} else {
					try { 
						data.getUser(info.id);
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
		request.setAttribute("accessError", accessError);
		request.setAttribute("complete", !anyError && request.getMethod().equals("POST"));
		
		request.setAttribute("newId", sNewId);
		request.setAttribute("newPw", password);
		request.setAttribute("info", info);
		

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/user_new_boundary.jsp");
		dispatcher.forward(request, response);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
