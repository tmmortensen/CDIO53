package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import boundary.LoginBoundary;
import data.Data;
import data.IOperatoerDAO;
import data.OperatoerDTO;

public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	IOperatoerDAO data;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		data = (IOperatoerDAO) getServletContext().getAttribute("data");
		if (data == null){
			data = new Data();
			getServletContext().setAttribute("data", data);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null){
			user = new User();
			request.getSession().setAttribute("user", user);
		}
		
		String sLoginError = "";
		
		if (!user.isinitialized()){
			user.init(data);
		}

		String redirect;
		if (request.getParameter("Redirect") != null)
			redirect = request.getParameter("Redirect");
		else
			redirect = "mainmenu.jsp";

		String uid = request.getParameter("UserID");
		String pword = request.getParameter("Password");

		String logout = request.getParameter("Logout");
		if (logout != null && logout.toLowerCase().equals("true")){
			user.logout();
		}

		if (user.isLoggedIn())
			response.sendRedirect(redirect);

		if (uid != null && pword != null)
			try {
				int iUid = Integer.parseInt(uid);
				if (user.login(iUid, pword)){
					response.sendRedirect(redirect);
				} else{
					sLoginError = "Det indtastede bruger id og kodeord er ikke korrekt";	
				}
			} catch (Exception e){
				sLoginError = "Bruger ID er ikke et tal";	
			}
		else {
			uid = "";
			pword = "";
		}
		
		LoginBoundary boundary = new LoginBoundary();
		boundary.setLoginError(sLoginError);
		boundary.setUserId(uid);
		boundary.setPassword(pword);
		boundary.setRedirect(redirect);
		boundary.createOutput(response);
		
		// TODO remove this when done testing
		try {
			PrintWriter output = response.getWriter();
			List<OperatoerDTO> operators = data.getOperatoerList();
			for (OperatoerDTO op : operators){
				output.print("ID: " + op.getOprId() + " PW: " + op.getPassword() + " Admin: ");
				if (op.isAdmin()){
					output.print("Ja");
				}
				output.print("<BR>\n");
			}
		} catch (Exception e){}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		doPost(request, response);
	}
}
