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

		UserSession user = (UserSession) request.getSession().getAttribute("user");
		if (user == null){
			user = new UserSession();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}
		
		if (!user.isLoggedIn()){
			response.sendRedirect("login");
			return;
		} 
		
		if (!user.isAdmin()){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		try {
			List<UserDTO> operators = data.getOperatoerList();
			List<UserInfo> users = new ArrayList<UserInfo>();
			for (UserDTO operator : operators){
				UserInfo userInfo = new UserInfo(operator);
				users.add(userInfo);
			}
			
			request.setAttribute("userlist", users);

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
