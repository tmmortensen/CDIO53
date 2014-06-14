package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityBatchDTO;
import admin.data.DALException;
import admin.data.UserType;

public class BatchCommodityEditController extends AbstractController {

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
		String comIdError = "";
		String amountError = "";
		String sNewId = "";
		String sNewComId = "";
		String sNewAmount = "";
		CommodityBatchDTO comBatch = null;
		
		String sComBatchId = request.getParameter("id");
		int iOldId = 0;

		if (sComBatchId != null && sComBatchId.equals("new")) {
			isNew = true;
		} else {
			try {
				iOldId = Integer.parseInt(sComBatchId);
			} catch (NumberFormatException e) {
				majorError = "RåvarebatchId er ugyldigt";
				anyError = true;
			}
		}
		
		if (isNew) {
			comBatch = new CommodityBatchDTO();
		} else {
			try {
				comBatch = comBatches.getCommodityBatch(iOldId);
				sNewId = sComBatchId;
				sNewComId = "" + comBatch.getCommodityBatchId();
				sNewAmount = "" + comBatch.getAmount();
			} catch (DALException e1) {
				majorError = "Der findes intet råvarebatch med det angivne id";
				anyError = true;
			}
		}
		
		if (! anyError && request.getMethod().equals("POST")) {
			sNewId = request.getParameter("newId");
			sNewComId = request.getParameter("newComId");
			sNewAmount = request.getParameter("newAmount");
			
			try {
				iNewId = Integer.parseInt(sNewId);
				comBatch.setCommodityBatchId(iNewId);
			} catch (NumberFormatException e) {
				idError = "Det indtastede råvarebatch id er ikke et helt tal";
				anyError = true;
			} catch (DALException e) {
				idError = e.getMessage();
				anyError = true;
			}
			
			try {
				int iNewComId = Integer.parseInt(sNewComId);
				comBatch.setCommodityId(iNewComId);
			} catch (NumberFormatException e) {
				comIdError = "Det indtastede råvare id er ikke et helt tal";
				anyError = true;
			} catch (DALException e) {
				comIdError = e.getMessage();
				anyError = true;
			}

			try {
				double dNewAmount = Double.parseDouble(sNewAmount);
				comBatch.setAmount(dNewAmount);
			} catch (NumberFormatException e) {
				amountError = "Den indtastede mændge er ikke et tal";
				anyError = true;
			} catch (DALException e) {
				amountError = e.getMessage();
				anyError = true;
			}

			if (!anyError){
				if(isNew){
					try{
						comBatches.createCommodityBatch(comBatch);
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				} else if (iOldId == iNewId) {
					try{
						comBatches.updateCommodityBatch(comBatch);
					} catch (DALException e) {
						idError = e.getMessage();
						anyError = true;
					}
				} else {
					try{
						comBatches.createCommodityBatch(comBatch);
						try{
							comBatches.deleteCommodityBatch(iOldId);
						} catch (DALException e) {
							idError = "Råvarebatch id kunne ikke ændres <BR>";
							idError += e.getMessage();
							anyError = true;
							comBatches.deleteCommodityBatch(iNewId);
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
		request.setAttribute("comIdError", comIdError);
		request.setAttribute("amountError", amountError);
		
		request.setAttribute("create", isNew);
		request.setAttribute("complete", !anyError
				&& request.getMethod().equals("POST"));

		request.setAttribute("newId", sNewId);
		request.setAttribute("newComId", sNewComId);
		request.setAttribute("newAmount", sNewAmount);
		request.setAttribute("comBatch", comBatch);
		
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/commodityBatch_edit_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
