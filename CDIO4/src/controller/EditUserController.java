package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import data.DALException;
import data.Data;
import data.OperatoerDTO;
import data.UserInfo;

public class EditUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Data data;
	UserInfo userinfo = (UserInfo) getServletContext().getAttribute("user");
	boolean first_visit = true;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		data = (Data) getServletContext().getAttribute("data");
		if (data == null) {
			data = new Data();
			getServletContext().setAttribute("data", data);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String err = "";
		String uid = request.getParameter("userID");
		String ini = request.getParameter("userINI");
		String name = request.getParameter("userNAME");
		String cpr = request.getParameter("userCPR");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			user = new User();
			user.init(data);
			request.getSession().setAttribute("user", user);
		}

		if (first_visit) {
			String InUid = request.getParameter("id");
			int IntUid = Integer.parseInt(InUid);
			try {
				String Inini = data.getOperatoer(IntUid).getIni();
				String Inname = data.getOperatoer(IntUid).getOprNavn();
				String Incpr = data.getOperatoer(IntUid).getCpr();
				request.setAttribute("userID", InUid);
				request.setAttribute("userINI", Inini);
				request.setAttribute("userNAME", Inname);
				request.setAttribute("userCPR", Incpr);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/edit_user.jsp");
				dispatcher.forward(request, response);
				first_visit = false; 
				return;
			} catch (DALException e1) {
				e1.printStackTrace();
			}

		}

		if (uid.equals(userinfo.id) && ini.equals(userinfo.ini)
				&& name.equals(userinfo.name) && cpr.equals(userinfo.cpr)) {
			err = "du har ikke ændret noget!";
			request.setAttribute("userID", uid);
			request.setAttribute("userINI", ini);
			request.setAttribute("userNAME", name);
			request.setAttribute("userCPR", cpr);
			request.setAttribute("error", err);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/edit_user.jsp");
			dispatcher.forward(request, response);
			return;

		} else {
			int Uid = Integer.parseInt(uid);

			if (Uid == userinfo.id) {
				try {
					String password = data.getOperatoer(userinfo.id)
							.getPassword();
					OperatoerDTO opr = new OperatoerDTO(Uid, name, ini, cpr,
							password);
					data.updateOperatoer(opr);

				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					String password = data.getOperatoer(userinfo.id)
							.getPassword();
					data.createOperatoer(new OperatoerDTO(Uid, ini, name, cpr,
							password));
					data.deleteOperatoer(userinfo.id);

				} catch (DALException e) {
					e.printStackTrace();
				}

			}
		}

		request.setAttribute("userID", uid);
		request.setAttribute("userINI", ini);
		request.setAttribute("userNAME", name);
		request.setAttribute("userCPR", cpr);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/edit_user.jsp");
		dispatcher.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
