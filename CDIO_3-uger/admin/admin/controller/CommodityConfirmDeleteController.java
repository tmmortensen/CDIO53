package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityDTO;
import admin.data.DALException;
import admin.data.UserType;

public class CommodityConfirmDeleteController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String error = "";
		CommodityDTO com = null;
		boolean success = false;
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			error = "Råvare id er ugyldigt";
		}

		try {
			com = commodities.getCommodity(id);
		} catch (DALException e) {
			error = "Der findes ingen råvare med det givne id";
		}

		if (request.getMethod().equals("POST")){
			try {
				commodities.deleteCommodity(id);
				success = true;
			} catch (DALException e) {
				error = "Der skete en fejl under sletning<BR>";
				error += e.getMessage();
			}
		}

		request.setAttribute("commodity", com);

		request.setAttribute("done", success);
		request.setAttribute("error", error);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/commodity_confirm_delete_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
