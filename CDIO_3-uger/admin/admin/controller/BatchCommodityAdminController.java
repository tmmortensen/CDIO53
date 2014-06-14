package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityBatchDTO;
import admin.data.UserType;

public class BatchCommodityAdminController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			List<CommodityBatchDTO> commodityBatchList = comBatches.getComBatchList();
			request.setAttribute("commodityBatchList", commodityBatchList);
		} catch (Exception e){
		}
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/commodityBatch_admin_boundary.jsp");
		dispatcher.forward(request, response);

	}
}
