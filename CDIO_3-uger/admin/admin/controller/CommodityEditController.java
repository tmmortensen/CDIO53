package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityDTO;
import admin.data.DALException;
import admin.data.UserType;

public class CommodityEditController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		int iNewId = 0;
		boolean isNew = false;
		boolean anyError = false;
		String majorError = "";
		String idError = "";
		String nameError = "";
		String supError = "";
		String sNewId = "";
		String newName = "";
		String newSupplier = "";
		CommodityDTO commodity = null;
		
		String sComId = request.getParameter("id");
		int iOldId = 0;

		if (sComId != null && sComId.equals("new")) {
			isNew = true;
		} else {
			try {
				iOldId = Integer.parseInt(sComId);
			} catch (NumberFormatException e) {
				majorError = "RåvareId er ugyldigt";
				anyError = true;
			}
		}
		
		if (isNew) {
			commodity = new CommodityDTO();
		} else {
			try {
				commodity = commodities.getCommodity(iOldId);
				sNewId = sComId;
			} catch (DALException e1) {
				majorError = "Der findes ingen råvare med det angivne id";
				anyError = true;
			}
		}
		
		if (! anyError && request.getMethod().equals("POST")) {
			sNewId = request.getParameter("newId");
			newName = request.getParameter("newName");
			newSupplier = request.getParameter("newSupplier");
			
			try {
				iNewId = Integer.parseInt(sNewId);
				commodity.setCommodity_id(iNewId);
			} catch (NumberFormatException e) {
				idError = "Det indtastede råvare id er ikke et tal";
				anyError = true;
			} catch (DALException e) {
				idError = e.getMessage();
				anyError = true;
			}
			
			try {
				commodity.setCommodity_name(newName);
			} catch (DALException e) {
				nameError = e.getMessage();
				anyError = true;
			}

			try {
				commodity.setSupplier(newSupplier);
			} catch (DALException e) {
				supError = e.getMessage();
				anyError = true;
			}
			
			if (!anyError){
				if(isNew){
					try{
						commodities.createCommodity(commodity);
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				} else if (iOldId == iNewId) {
					try{
						commodities.updateCommodity(commodity);
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				} else {
					try{
						commodities.createCommodity(commodity);
						try{
							commodities.deleteCommodity(iOldId);
						} catch (DALException e) {
							idError = "Råvare id kunne ikke ændres <BR>";
							idError += e.getMessage();
							anyError = true;
							commodities.deleteCommodity(iNewId);
						}
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				}
			}

		}

		request.setAttribute("majorError", majorError);
		request.setAttribute("idError", idError);
		request.setAttribute("nameError", nameError);
		request.setAttribute("supError", supError);
		
		request.setAttribute("create", isNew);
		request.setAttribute("complete", !anyError
				&& request.getMethod().equals("POST"));

		request.setAttribute("newId", sNewId);
		request.setAttribute("commodity", commodity);
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/commodity_edit_boundary.jsp");
		dispatcher.forward(request, response);


	}
}
