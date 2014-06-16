package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.DALException;
import admin.data.ProductBatchCompDTO;
import admin.data.ProductBatchDTO;
import admin.data.StatusType;
import admin.data.UserType;

public class ProductBatchEditController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.FOREMAN;
	}

	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean isNew = false;
		boolean anyError = false;

		String majorError = "";
		String idError = "";
		String presIdError = "";
		String statusError = "";
		String compError = "";
		String userError = "";

		ProductBatchDTO product = null;
		List<ProductBatchCompDTO> components = null;

		String sNewId = "";
		String sNewPresId = "";
		String sNewStatus = "";
		String sNewUserId = "";

		String sOldId = request.getParameter("id");
		int iOldId = 0;

		// try and parse the id given in the url.
		// if the id equals "new" then we're creating a new prescription
		if (sOldId != null && sOldId.equals("new")) {
			isNew = true;
		} else {
			try {
				iOldId = Integer.parseInt(sOldId);
			} catch (NumberFormatException e) {
				majorError = "ReceptID er ugyldigt";
				anyError = true;
			}
		}

		// if the request is a GET request we know that no data has been posted.
		if (request.getMethod().equals("GET")) {
			// if it's a new prescription we just create a new PrescriptionDTO
			// and be done with it
			if (isNew) {
				product = new ProductBatchDTO();
				sNewPresId = "";
				sNewStatus = "";
			} else {
				// if it's not a new prescription we have to get the info on it
				// from the database
				try {
					product = products.getProductBatch(iOldId);
					sNewId = sOldId;
					sNewPresId = "" + product.getPrescriptionId();
					sNewStatus = product.getStatus().name();
					sNewUserId = "" + product.getUserId();
				} catch (DALException e) {
					majorError = "Der findes intet produktbatch med det angivne id";
					anyError = true;
				}
				// and we have to fetch the list of components associated with
				// the prescription
				try {
					components = prodComps.getCertainProductBatchComps(iOldId);
				} catch (DALException e) {
					compError = e.getMessage();
				}
			}
		} else {
			// if the request is a POST request we need to check all the info
			// submitted and maybe update the database if there are no errors
			sNewId = request.getParameter("newId");
			sNewPresId = request.getParameter("newPresId");
			sNewStatus = request.getParameter("newStatus");
			sNewUserId = request.getParameter("newUserId");
			
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
					product = new ProductBatchDTO();
					// if we're creating a new prescription there is no old id,
					// so we use the new one in its place
					iOldId = iNewId;
				} else {
					product = products.getProductBatch(iOldId);

					// Get the list of components from the database
					components = prodComps.getCertainProductBatchComps(iOldId);
				}

				// Try to insert the new id into our DTO
				try {
					product.setPb_id(iNewId);
				} catch (DALException e) {
					// Post an error message if it fails
					idError = e.getMessage();
					anyError = true;
				}

				// Try to insert the new prescription id into our DTO
				try {
					int iNewPresId = Integer.parseInt(sNewPresId);
					product.setPrescription_id(iNewPresId);
				} catch (NumberFormatException e) {
					// Post an error message if we can't convert the input to an
					// int
					presIdError = "det indtastede recpetId er ikke et tal";
					anyError = true;
				} catch (DALException e) {
					// Post an error message if we can't set the id
					presIdError = e.getMessage();
					anyError = true;
				}

				// Try to insert the new user id into our DTO
				try {
					int iNewUserId = Integer.parseInt(sNewUserId);
					product.setUserId(iNewUserId);
				} catch (NumberFormatException e) {
					// Post an error message if we can't convert the input to an
					// int
					presIdError = "det indtastede bruger id er ikke et tal";
					anyError = true;
				} catch (DALException e) {
					// Post an error message if we can't set the id
					userError = e.getMessage();
					anyError = true;
				}

				// Try to insert the new status into our DTO
				try {
					StatusType status = StatusType.valueOf(sNewStatus);
					product.setStatus(status);
				} catch (DALException e) {
					// Post an error message if we can't set the status
					statusError = e.getMessage();
					anyError = true;
				} catch (Exception e) {
					// Post an error message if something else went wrong
					statusError = "der skete en fejl med den nye status";
					statusError += e.getMessage();
					anyError = true;
				}

				// if there were no errors so far and the submit button was
				// pressed we should try to update the database with the new
				// info
				if (!anyError) {
					// if the new and old IDs are different we have to create a
					// new entry and remove the old one. The IDs will be the
					// same if we're creating a new prescription
					if (iNewId != iOldId) {
						products.createProductBatch(product);
						try {
							products.deleteBatch(iOldId);
						} catch (DALException e) {
							products.deleteBatch(product.getPbId());
						}
						// we now need to update all the components. We have to
						// remove the old ones, since they all have the wrong
						// prescription id and then insert the new ones
						prodComps.deleteByBatchID(iOldId);
						for (ProductBatchCompDTO comp : components) {
							prodComps.createProductBatchComp(comp);
						}
					} else if (isNew) {
						// This is where we create a new prescription
						products.createProductBatch(product);

						// we don't add any components to the database since
						// they only get added by the ASE
					} else {
						// This is where we update an existing one with no ID
						// change
						products.updateProductBatch(product);

						// we don't change any of the components since they are
						// still connected to the same batch ID
					}
					//product = products.getProductBatch(product.getPbId());
				}
			} catch (DALException e) {
				idError = e.getMessage();
				anyError = true;
			}

		}

		request.setAttribute("complete", !anyError && request.getMethod().equals("POST"));
		request.setAttribute("create", isNew);

		request.setAttribute("majorError", majorError);
		request.setAttribute("idError", idError);
		request.setAttribute("presIdError", presIdError);
		request.setAttribute("statusError", statusError);
		request.setAttribute("compError", compError);
		request.setAttribute("userError", userError);
		
		request.setAttribute("newId", sNewId);
		request.setAttribute("newPresId", sNewPresId);
		request.setAttribute("newStatus", sNewStatus);
		request.setAttribute("newUserId", sNewUserId);
		
		request.setAttribute("product", product);
		request.setAttribute("components", components);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/productBatch_edit_boundary.jsp");
		dispatcher.forward(request, response);

	}

}
