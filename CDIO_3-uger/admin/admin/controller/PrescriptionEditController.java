package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.DALException;
import admin.data.PrescriptionDTO;
import admin.data.UserDTO;
import admin.data.UserInfo;
import admin.data.UserType;

public class PrescriptionEditController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		boolean isNew = false;
		boolean anyError = false;
		
		String majorError = "";
		String idError = "";
		String nameError = "";
		
		PrescriptionDTO prescription = null;
		
		String sNewId = "";
		String newName = "";
		
		String sPresId = request.getParameter("id");
		int iPresId = 0;

		if (sPresId != null && sPresId.equals("new")) {
			isNew = true;
		} else {
			try {
				iPresId = Integer.parseInt(sPresId);
			} catch (NumberFormatException e) {
				majorError = "Bruger id er ugyldigt";
				anyError = true;
			}
		}
		
		if (request.getMethod().equals("GET")) {
			if (isNew) {
				prescription = new PrescriptionDTO();
			} else {
				try {
					prescription = prescriptions.getPrescription(iPresId);
					sNewId = sPresId;
				} catch (DALException e1) {
					majorError = "Der findes ingen bruger med det angivne id";
					anyError = true;
				}
			}
		} else {
			sNewId = request.getParameter("newId");
			newName = request.getParameter("newName");
			
			int iNewId = 0;
			try {
				iNewId = Integer.parseInt(sNewId);
			} catch (NumberFormatException e) {
				iNewId = 0;
				idError = "Det indtastede bruger id er ikke et tal";
				anyError = true;
			}
			
			try {
				prescription = prescriptions.getPrescription(iPresId);
				
				prescription.setName(newName);
				if (iNewId != iPresId){
					
				} else {
					try {
						prescriptions.
					}
				}
			} catch (DALException e){
				idError = e.getMessage();
			}

		}

		request.setAttribute("complete", !anyError
				&& request.getMethod().equals("POST"));
		request.setAttribute("create", isNew);

		request.setAttribute("majorError", majorError);
		request.setAttribute("idError", idError);
		request.setAttribute("nameError", nameError);
		request.setAttribute("newId", sNewId);
		request.setAttribute("prescription", prescription);
	
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/prescription_edit_boundary.jsp");
		dispatcher.forward(request, response);

	}
}