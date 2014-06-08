package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.UserData;
import admin.data.IUserReadOnly;
import admin.data.UserDTO;
import admin.data.UserInfo;

/**
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserReadOnly data;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (UserData) getServletContext().getAttribute("data");
		if (data == null){
			UserData newdata = new UserData();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		UserSession userSession = (UserSession) request.getSession().getAttribute("user");
		if (userSession == null){
			userSession = new UserSession();
			userSession.init(data);
			request.getSession().setAttribute("user", userSession);
		}
		
		if (!userSession.isLoggedIn()){
			response.sendRedirect("login");
			return;
		} 
		
		if (!userSession.isAdmin()){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		try {
			List<UserDTO> users = data.getUser();
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			for (UserDTO user : users){
				UserInfo userInfo = new UserInfo(user);
				userInfos.add(userInfo);
			}
			
			request.setAttribute("userlist", userInfos);

		} catch (Exception e){
			
		}
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/user_admin_boundary.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
