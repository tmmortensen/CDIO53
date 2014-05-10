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
		String InUid = request.getParameter("id");
		int IntUid = Integer.parseInt(InUid);

		if (first_visit) {

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
		OperatoerDTO operator;

		try {
			operator = data.getOperatoer(IntUid);
			if (uid.equals(InUid) && ini.equals(operator.getIni())
					&& name.equals(operator.getOprNavn())
					&& cpr.equals(operator.getCpr())) {
				err = "du har ikke �ndret noget!";
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

				if (uid.equals(InUid)) {
					try {
						String password = data.getOperatoer(Uid).getPassword();
						OperatoerDTO opr = new OperatoerDTO(Uid, name, ini,
								cpr, password);
						data.updateOperatoer(opr);

					} catch (DALException e) {
						err = e.getMessage();
					}
				} else {
					try {
						String password = data.getOperatoer(IntUid)
								.getPassword();
						data.createOperatoer(new OperatoerDTO(Uid, ini, name,
								cpr, password));
						data.deleteOperatoer(IntUid);

					} catch (DALException e) {
						err = e.getMessage();
						request.setAttribute("error", err);
						request.setAttribute("userID", uid);
						request.setAttribute("userINI", ini);
						request.setAttribute("userNAME", name);
						request.setAttribute("userCPR", cpr);

						RequestDispatcher dispatcher = getServletContext()
								.getRequestDispatcher("/edit_user.jsp");
						dispatcher.forward(request, response);
						return;
					}

				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

		request.setAttribute("userID", uid);
		request.setAttribute("userINI", ini);
		request.setAttribute("userNAME", name);
		request.setAttribute("userCPR", cpr);
		request.setAttribute("error", err);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/edit_user.jsp");
		dispatcher.forward(request, response);
		return;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
