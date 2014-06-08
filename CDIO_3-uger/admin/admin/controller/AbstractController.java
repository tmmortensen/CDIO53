package admin.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import admin.data.DataOld;
//import admin.data.UserData;

public abstract class AbstractController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// TODO skifte til det nye databasebaserede datalag
	//UserData data;
	DataOld data;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// data = (UserData) getServletContext().getAttribute("data");
		data = (DataOld) getServletContext().getAttribute("data");
		
		if (data == null) {
			//data = new UserData();
			data = new DataOld();
			getServletContext().setAttribute("data", data);
		}
	}
}
