package boundary;

public interface IBoundary {

	/**
	 * Allows a operator to login
	 * 
	 * @return <code>login[]</code> where <code>login[0]</code> is the entered
	 *         user ID and <code>login[1]</code> is the entered password
	 */
	public String[] login();

	/**
	 * Shows a message and returns a string from operator
	 * 
	 * @param msg
	 *            a message to the operator
	 * @return string from operator
	 */
	public String getString(String msg);

	/**
	 * Shows a message and returns an integer from operator
	 * 
	 * @param msg
	 *            a message to the operator
	 * @return int from operator
	 */
	public int getInt(String msg);

	/**
	 * Shows a menu with header and options
	 * 
	 * @param options
	 *            an array of strings containing the text for each option
	 * @param header
	 *            the header for the menu
	 * @return the # of the option chosen by the operator (a value between 1 and
	 *         <code>options.length()</code>)
	 */
	public int menu(String[] options, String header);

	/**
	 * Shows a message from the system to the operator
	 * 
	 * @param msg
	 *            the message to be displayed
	 */
	public void showStringMessage(String msg);

}
