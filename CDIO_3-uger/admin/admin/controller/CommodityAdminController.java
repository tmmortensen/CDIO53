package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityDTO;
import admin.data.UserType;

public class CommodityAdminController extends AbstractController {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected UserType requiredAccessLevel() {
		return UserType.PHARMACIST;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		try {
			List<CommodityDTO> commodityList = commodities.getComList();
			request.setAttribute("commodityList", commodityList);
		} catch (Exception e){
		}
		
		RequestDispatcher dispatcher =
				getServletContext().getRequestDispatcher("/commodity_admin_boundary.jsp");
		dispatcher.forward(request, response);

	}

}
