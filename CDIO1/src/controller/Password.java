package controller;

import data.IOperatoerDAO;
import boundary.IBoundary;

public class Password implements ISubController {
	IBoundary boundary;
	IOperatoerDAO data;

	public Password(IBoundary bound, IOperatoerDAO data) {
		this.boundary = bound;
		this.data = data;
	}

	public void run(int userId) {

	}

}
