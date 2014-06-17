package admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.DALException;
import admin.data.PrescriptionCompDTO;
import admin.data.PrescriptionDTO;
import admin.data.UserType;

public class PrescriptionEditController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean isNew = false;
		boolean anyError = false;
		boolean submit = true;

		String majorError = "";
		String idError = "";
		String nameError = "";

		PrescriptionDTO prescription = null;
		List<PrescriptionCompDTO> components = null;

		String sNewId = "";
		String newName = "";

		String sPresId = request.getParameter("id");
		int iOldId = 0;

		// try and parse the id given in the url.
		// if the id equals "new" then we're creating a new prescription
		if (sPresId != null && sPresId.equals("new")) {
			isNew = true;
		} else {
			try {
				iOldId = Integer.parseInt(sPresId);
			} catch (NumberFormatException e) {
				majorError = "ReceptID er ugyldigt";
				anyError = true;
			}
		}

		// if the request is a GET request we know that no data has been posted.
		if (request.getMethod().equals("GET")) {
			submit = false;
			// if it's a new prescription we just create a new PrescriptionDTO
			// and be done with it
			if (isNew) {
				prescription = new PrescriptionDTO();
			} else {
				// if it's not a new prescription we have to get the info on it
				// from the database
				try {
					prescription = prescriptions.getPrescription(iOldId);
					sNewId = sPresId;
				} catch (DALException e1) {
					majorError = "Der findes ingen recept med det angivne id";
					anyError = true;
				}
				// and we have to fetch the list of components associated with
				// the prescription
				try {
					components = presComps.getComponentList(iOldId);
				} catch (DALException e) {
					System.err.println(e.getMessage());
				}
			}
		} else {
			// if the request is a POST request we need to check all the info
			// submitted and maybe update the database if there are no errors
			sNewId = request.getParameter("newId");
			newName = request.getParameter("newName");

			// check which button was pressed
			// null means it was the regular submit button
			// any other value means we have to modify the component list
			String button = request.getParameter("button");
			if (button != null)
				submit = false;
			else
				button = "";

			// check if the entered id is a number
			int iNewId = 0;
			try {
				iNewId = Integer.parseInt(sNewId);
			} catch (NumberFormatException e) {
				iNewId = 0;
				idError = "Det indtastede id er ikke et tal";
				anyError = true;
			}

			try {
				// Check if we're creating a new recipe or if we're editing an
				// existing one and if so, get the info on it from the database.
				if (isNew) {
					prescription = new PrescriptionDTO();
					// if we're creating a new prescription there is no old id,
					// so we use the new one in its place
					iOldId = iNewId;
				} else {
					prescription = prescriptions.getPrescription(iOldId);
				}

				// Check how long the currently displayed component list is
				String sCompCount = request.getParameter("compCount");
				int iCompCount = 0;
				try {
					iCompCount = Integer.parseInt(sCompCount);
				} catch (NumberFormatException e) {
				}

				// make the list of components from the data in the form (not
				// taking the data from the database)
				int i = 0;
				components = new ArrayList<PrescriptionCompDTO>();
				while (iCompCount > components.size()) {
					// create a new component belonging to the current
					// prescription
					PrescriptionCompDTO component = new PrescriptionCompDTO();
					try {
						component.setPrescriptionId(iNewId);
					} catch (Exception e) {
						anyError = true;
					}

					// Get the component id from the form and try to insert it
					// into the DTO.
					String comIdError = "";
					String sComId = request.getParameter("comId" + i);
					try {
						int iComId = Integer.parseInt(sComId);
						component.setCommodityId(iComId);
					} catch (Exception e) {
						// Post an error message if it fails
						anyError = true;
						comIdError = e.getMessage() + "<BR>";
					}

					// Get the net weight from the form and try to insert it
					// into the DTO.
					String sNetto = request.getParameter("netto" + i);
					try {
						double dNetto = Double.parseDouble(sNetto);
						component.setNomNetto(dNetto);
					} catch (Exception e) {
						// Post an error message if it fails
						anyError = true;
						request.setAttribute("comNetError" + i, e.getMessage());
					}

					// Get the tolerance from the form and try to insert it into
					// the DTO.
					String sTolerance = request.getParameter("tolerance" + i);
					try {
						double dTolerance = Double.parseDouble(sTolerance);
						component.setTolerance(dTolerance);
					} catch (Exception e) {
						// Post an error message if it fails
						anyError = true;
						request.setAttribute("comTolError" + i, e.getMessage());
					}

					// before we add the component to the list we check if it
					// has the same commodity id as any of the others
					int j = 1;
					for (PrescriptionCompDTO oldComp : components) {
						if (oldComp.getCommodityId() == component
								.getCommodityId()) {
							comIdError += "Samme vareID som #" + j + "<BR>";
						}
						j++;
					}
					request.setAttribute("comIdError" + i, comIdError);

					// finally add the component to the list and increment our
					// counter
					components.add(component);
					i++;
				}

				// if the new button was pressed we have to add a component to
				// our list
				if (button.equals("new")) {
					components.add(new PrescriptionCompDTO(iOldId, 0, 0, 0));
				}

				// if a delete button was pressed we have to remove an entry
				// from the list corresponding to the number of the button
				if (button.matches("delete[0-9]+")) {
					String sDelete = button.substring(6);
					try {
						int iDelete = Integer.parseInt(sDelete);
						components.remove(iDelete);
					} catch (Exception e) {
					}
				}

				// Try to insert the new id into our DTO
				try {
					prescription.setId(iNewId);
				} catch (DALException e) {
					// Post an error message if it fails
					idError = e.getMessage();
					anyError = true;
				}

				// Try to insert the new name into our DTO
				try {
					prescription.setName(newName);
				} catch (DALException e) {
					// Post an error message if it fails
					nameError = e.getMessage();
					anyError = true;
				}

				// if there were no errors so far and the submit button was
				// pressed we should try to update the database with the new
				// info
				if (!anyError && submit) {
					// if the new and old IDs are different we have to create a
					// new entry and remove the old one. The IDs will be the
					// same if we're creating a new prescription
					if (iNewId != iOldId) {
						prescriptions.createPrescription(prescription);
						prescriptions.deletePrescription(iOldId);

						// we now need to update all the components. We have to
						// remove the old ones, since they all have the wrong
						// prescription id and then insert the new ones
						presComps.deletePrescriptionComp(iOldId);
						for (PrescriptionCompDTO comp : components) {
							presComps.createPrescriptionComp(comp);
						}
					} else if (isNew) {
						// This is where we create a new prescription
						prescriptions.createPrescription(prescription);

						// now we need to add the components to the database
						for (PrescriptionCompDTO comp : components) {
							presComps.createPrescriptionComp(comp);
						}
					} else {
						// This is where we update an existing one with no ID
						// change
						prescriptions.updatePrescription(prescription);

						// we now need to update all the components. the easiest
						// way to do that is to remove the old ones and insert
						// the new ones
						presComps.deletePrescriptionComp(iOldId);
						for (PrescriptionCompDTO comp : components) {
							presComps.createPrescriptionComp(comp);
						}
					}

				}
			} catch (DALException e) {
				idError = e.getMessage();
				anyError = true;
			}

		}

		request.setAttribute("complete", !anyError && submit);
		request.setAttribute("create", isNew);

		request.setAttribute("majorError", majorError);
		request.setAttribute("idError", idError);
		request.setAttribute("nameError", nameError);
		request.setAttribute("newId", sNewId);
		request.setAttribute("prescription", prescription);
		request.setAttribute("components", components);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/prescription_edit_boundary.jsp");
		dispatcher.forward(request, response);

	}
}