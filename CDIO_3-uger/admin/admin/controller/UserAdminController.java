package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.IUsersReadOnly;
import admin.data.UserDTO;
import admin.data.UserInfo;

/**
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends AbstractController {
	private static final long serialVersionUID = 1L;
	IUsersReadOnly data;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.data = super.data;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		if (!userSession.isLoggedIn()){
			response.sendRedirect("login?redirect=user_admin");
			return;
		} 
		
		if (!userSession.isAdmin()){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		
		try {
			List<UserDTO> users = data.getUserList();
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
}
