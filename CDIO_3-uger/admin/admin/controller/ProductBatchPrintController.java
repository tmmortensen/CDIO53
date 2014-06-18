package admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.DALException;
import admin.data.PrescriptionCompDTO;
import admin.data.ProductBatchCompDTO;
import admin.data.ProductBatchDTO;
import admin.data.ProductListItem;
import admin.data.UserType;

public class ProductBatchPrintController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return null;
	}

	@Override
	public void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int iId = 0;
		double iSumTara = 0;
		double iSumNetto = 0;
		String sId = request.getParameter("id");

		String sError = "";

		ProductBatchDTO product = null;
		List<PrescriptionCompDTO> preCompList = null;
		List<ProductListItem> itemList = new ArrayList<ProductListItem>();
		String printDate = new Date(Calendar.getInstance().getTime().getTime())
				.toString();

		try {
			iId = Integer.parseInt(sId);
		} catch (NumberFormatException e) {
			sError = "ProduktbatchID er ugyldigt";
		}

		try {
			product = products.getProductBatch(iId);
			preCompList = presComps.getComponentList(product
					.getPrescriptionId());
		} catch (DALException e) {
			sError = "ProduktbatchID findes ikke i databasen";
		}

		if (preCompList != null) {
			for (PrescriptionCompDTO preComp : preCompList) {
				ProductListItem item = new ProductListItem();
				item.commodityId = preComp.getCommodityId();
				try {
					item.commodityName = commodities.getCommodity(
							preComp.getCommodityId()).getComName();
				} catch (DALException e) {
				}

				item.ammount = preComp.getNomNetto();
				item.tolerance = preComp.getTolerance();
				try {
					ProductBatchCompDTO proComp = prodComps.getCompByComId(iId,
							item.commodityId);
					item.tara = proComp.getTara();
					iSumTara += item.tara;
					item.netto = proComp.getNetto();
					iSumNetto += item.netto;
					item.commodityBatch = proComp.getCommoditybatch_id();
					item.operator = users.getUser(proComp.getUser_id())
							.getIni();
				} catch (DALException e) {
				}
				itemList.add(item);
			}
		}
		
		String sSumTara = "" + iSumTara;
		String sSumNetto = "" + iSumNetto;
		try {
			sSumTara = sSumTara.substring(0, sSumTara.indexOf(".")+4);
		}catch(Exception e){}
		try {
			sSumNetto = sSumNetto.substring(0, sSumNetto.indexOf(".")+4);
		}catch(Exception e){}

		request.setAttribute("error", sError);

		request.setAttribute("date", printDate);
		request.setAttribute("sumTara", sSumTara);
		request.setAttribute("sumNetto", sSumNetto);

		request.setAttribute("product", product);
		request.setAttribute("components", itemList);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/productBatch_print_boundary.jsp");
		dispatcher.forward(request, response);

	}

}
