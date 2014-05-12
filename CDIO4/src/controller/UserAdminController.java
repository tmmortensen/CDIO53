package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;
import data.OperatoerDTO;
import data.UserInfo;

/**
 * Servlet implementation class UserAdminController
 */
public class UserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IDataReadOnly data;
       
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (Data) getServletContext().getAttribute("data");
		if (data == null){
			Data newdata = new Data();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			user = new User();
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
			List<OperatoerDTO> operators = data.getOperatoerList();
			List<UserInfo> users = new ArrayList<UserInfo>();
			for (OperatoerDTO operator : operators){
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
