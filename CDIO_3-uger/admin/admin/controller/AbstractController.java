package admin.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.CommodityBatchData;
import admin.data.CommodityData;
import admin.data.ICommodityBatchDAO;
import admin.data.ICommodityDAO;
import admin.data.IPrescriptionCompDAO;
import admin.data.IPrescritpionDAO;
import admin.data.IProductBatchCompDAO;
import admin.data.IProductBatchDAO;
import admin.data.IUserDAO;
import admin.data.PrescriptionCompData;
import admin.data.PrescriptionData;
import admin.data.ProductBatchCompData;
import admin.data.ProductBatchData;
import admin.data.UserData;
import admin.data.UserType;

public abstract class AbstractController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	UserSession userSession;
	
	ICommodityDAO commodities;
	ICommodityBatchDAO comBatches;
	IPrescritpionDAO prescriptions;
	IPrescriptionCompDAO presComps;
	IProductBatchDAO products;
	IProductBatchCompDAO prodComps;
	IUserDAO users;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		commodities = (ICommodityDAO) getServletContext().getAttribute("commodities");
		if (commodities == null) {
			commodities = new CommodityData();
			getServletContext().setAttribute("commodities", commodities);
		}

		comBatches = (ICommodityBatchDAO) getServletContext().getAttribute("comBatches");
		if (comBatches == null) {
			comBatches = new CommodityBatchData();
			getServletContext().setAttribute("comBatches", comBatches);
		}

		prescriptions = (IPrescritpionDAO) getServletContext().getAttribute("prescriptions");
		if (prescriptions == null) {
			prescriptions = new PrescriptionData();
			getServletContext().setAttribute("prescriptions", prescriptions);
		}

		presComps = (IPrescriptionCompDAO) getServletContext().getAttribute("presComps");
		if (presComps == null) {
			presComps = new PrescriptionCompData();
			getServletContext().setAttribute("presComps", presComps);
		}

		products = (IProductBatchDAO) getServletContext().getAttribute("products");
		if (products == null) {
			products = new ProductBatchData();
			getServletContext().setAttribute("products", products);
		}

		prodComps = (IProductBatchCompDAO) getServletContext().getAttribute("prodComps");
		if (prodComps == null) {
			prodComps = new ProductBatchCompData();
			getServletContext().setAttribute("prodComps", prodComps);
		}

		users = (IUserDAO) getServletContext().getAttribute("users");
		if (users == null) {
			users = new UserData();
			getServletContext().setAttribute("users", users);
		}
	}

	/**
	 * Handle the Get requests
	 */
	public final void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// we almost handle POST and GET the same so we let the POST method
		// handle our GET requests too
		doPost(request, response);
	}

	public final void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check if we have a user session and create one if we don't
		userSession = (UserSession) request.getSession().getAttribute(
				"userSession");
		if (userSession == null) {
			userSession = new UserSession();
			userSession.init(users);
			request.getSession().setAttribute("userSession", userSession);
		}

		// check if the user is logged in and send them to the login page if not
		String path = request.getServletPath();
		if (!userSession.isLoggedIn() && !path.equals("/login")) {
			String querry = request.getQueryString();
			if (querry != null)
				path += "?" + querry;
			response.sendRedirect("login?redirect=" + path);
			return;
		}

		// check if the user has the required access level and give an error if
		// not
		UserType req = requiredAccessLevel();
		if (req != null && userSession.accessLevel() > req.ordinal()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		// do the stuff needed for the specific controller
		doRequest(request, response);
	}

	/**
	 * Each controller have to specify a required access level
	 * 
	 * @return the UserType required or null if none is required
	 * (inactive users will never have access)
	 */
	protected abstract UserType requiredAccessLevel();

	/**
	 * Implement this to do the controller stuff
	 */
	public abstract void doRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
}
