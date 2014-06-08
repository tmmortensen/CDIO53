package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.UserData;
import admin.data.IUserReadOnly;
import admin.data.MenuOption;

public class MainMenuController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IUserReadOnly data;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (IUserReadOnly) getServletContext().getAttribute("data");
		if (data == null){
			UserData newdata = new UserData();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
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
		ArrayList<MenuOption> menuChoices = new ArrayList<MenuOption>();		
		menuChoices.add( new MenuOption("Skift kodeord", "password_change") );
		//menuChoices.add( new MenuOption("Test", "test") );
		menuChoices.add( new MenuOption("Log ud", "login?logout=true") );
		if (user.isForeman()){
			menuChoices.add(1, new MenuOption( "Produktbatchadministration", "productBatch_admin"));
			menuChoices.add(1, new MenuOption( "Råvarebatchadministration", "commodityBatch_admin"));
		}
		if (user.isPharmacist()){
			menuChoices.add(1, new MenuOption( "Receptadministration", "prescription_admin"));
			menuChoices.add(1, new MenuOption( "Råvareadministration", "commodity_admin"));
		}
		if (user.isAdmin()){
			menuChoices.add(1, new MenuOption( "Brugeradministration ", "user_admin"));
		}
		
		request.setAttribute("Menulist", menuChoices);
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/mainmenu_boundary.jsp");
		dispatcher.forward(request, response);

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
