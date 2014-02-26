package Boundary;

public interface IBoundary {

	/**
	 * Allows an operator to login
	 * 
	 * @return login info from operator
	 */
	public String[] login();

	/**
	 * Shows a message and returns a string from operator
	 * 
	 * @param msg
	 * @return string from operator
	 */
	public String getString(String msg);

	/**
	 * Shows a message and returns an integer from operator
	 * 
	 * @param msg
	 * @return int from operator
	 */
	public int getInt(String msg);

	/**
	 * Shows a header and shows operators options
	 * 
	 * @param options
	 * @param header
	 * @return int from operator
	 */
	public int menu(String[] options, String header);

	/**
	 * Shows a message from the system to the operator
	 * 
	 * @param msg
	 */
	public void showStringMessage(String msg);

}
