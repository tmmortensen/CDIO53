package controller;

import java.util.ArrayList;

import data.MenuOption;

public class MainMenu {
	
	public ArrayList<MenuOption> menuOptions(User user){
		ArrayList<MenuOption> options = new ArrayList<MenuOption>();
		options.add( new MenuOption("Skift kodeord", "password_change.jsp") );
		options.add( new MenuOption("Test", "password_change.jsp") );
		options.add( new MenuOption("Log ud", "login.jsp?Logout=true") );
		if (user.isAdmin()){
			options.add(1, new MenuOption( "Administrer brugere", "user_admin.jsp"));
		}
		return options;
	}

}
