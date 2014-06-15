package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.ProductBatchDTO;
import admin.data.UserType;

public class ProductBatchAdminController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return UserType.FOREMAN;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			List<ProductBatchDTO> productList = products.getAllProductBatches();
			request.setAttribute("productList", productList);

		} catch (Exception e){
			
		}
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/productBatch_admin_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
