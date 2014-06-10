package admin.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import admin.data.DataOld;
import admin.data.UserData;

public abstract class AbstractController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	UserData data;
	UserSession userSession;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		data = (UserData) getServletContext().getAttribute("data");
		
		if (data == null) {
			data = new UserData();
			getServletContext().setAttribute("data", data);
		}
	}
	
	public final void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
	
	public final void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		userSession = (UserSession) request.getSession().getAttribute("userSession");
		if (userSession == null){
			userSession = new UserSession();
			userSession.init(data);
			request.getSession().setAttribute("userSession", userSession);
		}
		
		if (!userSession.isinitialized()){
			userSession.init(data);
		}
		
		doRequest(request, response);
	}

	public abstract void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
}
