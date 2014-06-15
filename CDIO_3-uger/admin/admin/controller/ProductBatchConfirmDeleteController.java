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
import admin.data.UserType;

public class ProductBatchConfirmDeleteController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return UserType.FOREMAN;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String sProdId = request.getParameter("id");
		int iProdId = 0;
		String sError = "";
		boolean bError = false;
		
		ProductBatchDTO product = null;
		List<ProductBatchCompDTO> components = null;

		// try and parse the id given in the url.
		// if the id equals "new" then we're creating a new prescription
		try {
			iProdId = Integer.parseInt(sProdId);
			product = products.getProductBatch(iProdId);
			components = prodComps.getCertainProductBatchComps(iProdId);
		} catch (NumberFormatException e) {
			sError = "Produktbatch ID er ugyldigt";
			bError = true;
		} catch (DALException e) {
			sError = e.getMessage();
			bError = true;
		}
		
		if ( !bError && request.getMethod().equals("POST")) {
			try {
				products.deleteBatch(iProdId);
				prodComps.deleteByBatchID(iProdId);
			} catch (DALException e) {
				sError = e.getMessage();
				bError = true;
			}
		}
		
		request.setAttribute("complete", !bError && request.getMethod().equals("POST"));
		request.setAttribute("error", sError);

		request.setAttribute("product", product);
		request.setAttribute("components", components);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/prescription_confirm_delete_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
