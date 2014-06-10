package admin.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import admin.data.IUsersReadOnly;
import admin.data.UserType;

public class LoginController extends AbstractController{

	private static final long serialVersionUID = 1L;
	IUsersReadOnly data;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		this.data = super.data;
	}
	
	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		String loginError = "";

		String redirect = request.getParameter("redirect");
		if (redirect == null)
			redirect = "mainmenu";

		String logout = request.getParameter("logout");
		if (logout != null && logout.toLowerCase().equals("true")){
			userSession.logout();
		}

		if (userSession.isLoggedIn()){
			response.sendRedirect(redirect);
			return;
		}

		String uid = request.getParameter("userId");
		String pword = request.getParameter("password");
		if (uid != null && pword != null)
			try {
				int iUid = Integer.parseInt(uid);
				if (!userSession.login(iUid, pword)){
					loginError = "Det indtastede bruger id og kodeord er ikke korrekt";
				} else if (!(userSession.accessLevel() < UserType.INACTIVE.ordinal())){
					loginError = "Denne bruger er deaktiveret";
					userSession.logout();
				}else{
					response.sendRedirect(redirect);
					return;
				}
			} catch (Exception e){
				loginError = "Bruger ID er ikke et tal";	
			}
		else {
			uid = "";
			pword = "";
		}
		
		// TODO fjern dette
		request.setAttribute("data", data);
		// ^^ er kun til test
		
		request.setAttribute("error", loginError);
		request.setAttribute("userId", uid);
		request.setAttribute("password", pword);
		request.setAttribute("redirect", redirect);
		
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/login_boundary.jsp");
		dispatcher.forward(request, response);
	}
	
}
