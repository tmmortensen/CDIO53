package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.IUsersReadOnly;
import admin.data.UserDTO;
import admin.data.UserInfo;
import admin.data.UserType;

/**
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends AbstractController {
	private static final long serialVersionUID = 1L;
	IUsersReadOnly userData;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.userData = super.users;
	}

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.ADMIN;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		
		try {
			List<UserDTO> users = userData.getUserList();
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
