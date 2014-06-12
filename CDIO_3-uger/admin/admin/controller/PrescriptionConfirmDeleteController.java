package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.DALException;
import admin.data.PrescriptionCompDTO;
import admin.data.PrescriptionDTO;
import admin.data.UserType;


public class PrescriptionConfirmDeleteController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String sPresId = request.getParameter("id");
		int iPresId = 0;
		String sError = "";
		boolean bError = false;
		
		PrescriptionDTO prescription = null;
		List<PrescriptionCompDTO> components = null;

		// try and parse the id given in the url.
		// if the id equals "new" then we're creating a new prescription
		try {
			iPresId = Integer.parseInt(sPresId);
			prescription = prescriptions.getPrescription(iPresId);
			components = presComps.getComponentList(iPresId);
		} catch (NumberFormatException e) {
			sError = "ReceptID er ugyldigt";
			bError = true;
		} catch (DALException e) {
			sError = e.getMessage();
			bError = true;
		}

		if ( !bError && request.getMethod().equals("POST")) {
			try {
				prescriptions.deletePrescription(iPresId);
				presComps.deletePrescriptionComp(iPresId);
			} catch (DALException e) {
				sError = e.getMessage();
				bError = true;
			}
		}
		
		request.setAttribute("complete", !bError && request.getMethod().equals("POST"));
		request.setAttribute("error", sError);

		request.setAttribute("prescription", prescription);
		request.setAttribute("components", components);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/prescription_confirm_delete_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
