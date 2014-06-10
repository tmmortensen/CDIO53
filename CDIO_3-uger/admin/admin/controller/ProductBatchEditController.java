package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.data.UserType;

public class ProductBatchEditController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Override
	protected UserType requiredAccessLevel() {
		return UserType.FOREMAN;
	}

	@Override
	public void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
	}

}
