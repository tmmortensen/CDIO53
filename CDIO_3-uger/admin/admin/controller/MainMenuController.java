package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.MenuOption;
import admin.data.UserType;

public class MainMenuController extends AbstractController{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return null;
	}
	
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		
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
}
