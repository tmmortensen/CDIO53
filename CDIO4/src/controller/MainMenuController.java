package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import data.Data;
import data.IDataReadOnly;
import data.MenuOption;

public class MainMenuController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IDataReadOnly data;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (IDataReadOnly) getServletContext().getAttribute("data");
		if (data == null){
			Data newdata = new Data();
			getServletContext().setAttribute("data", newdata);
			data = newdata;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
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
		ArrayList<MenuOption> menuChoices = new ArrayList<MenuOption>();		
		menuChoices.add( new MenuOption("Skift kodeord", "password_change") );
		menuChoices.add( new MenuOption("Test", "test") );
		menuChoices.add( new MenuOption("Log ud", "login?logout=true") );
		if (user.isAdmin()){
			menuChoices.add(1, new MenuOption( "Administrer brugere", "user_admin"));
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
