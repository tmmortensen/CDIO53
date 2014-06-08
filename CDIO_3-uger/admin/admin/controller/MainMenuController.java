package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.IUserReadOnly;
import admin.data.MenuOption;

public class MainMenuController extends AbstractController{

	private static final long serialVersionUID = 1L;
	IUserReadOnly data;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.data = super.data;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
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
		ArrayList<MenuOption> menuChoices = new ArrayList<MenuOption>();		
		menuChoices.add( new MenuOption("Skift kodeord", "password_change") );
		//menuChoices.add( new MenuOption("Test", "test") );
		menuChoices.add( new MenuOption("Log ud", "login?logout=true") );
		if (userSession.isForeman()){
			menuChoices.add(1, new MenuOption( "Produktbatchadministration", "productBatch_admin"));
			menuChoices.add(1, new MenuOption( "Råvarebatchadministration", "commodityBatch_admin"));
		}
		if (userSession.isPharmacist()){
			menuChoices.add(1, new MenuOption( "Receptadministration", "prescription_admin"));
			menuChoices.add(1, new MenuOption( "Råvareadministration", "commodity_admin"));
		}
		if (userSession.isAdmin()){
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
