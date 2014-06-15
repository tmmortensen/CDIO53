package simulator.data;

import java.net.InetAddress;

/**
 * Interface with methods for ProgramState classes
 * 
 * @author Gruppe 53
 * 
 */
public interface IProgramState {
	/**
	 * 
	 * Sets the bottom display to show in the GUI recieves a string from the
	 * p111 command in the console
	 * 
	 * @param botDisplay
	 */
	public void setBotDisplay(String botDisplay);

	/**
	 * 
	 * return the bottom display that is currently stored in the GUI as a String
	 * 
	 * @return
	 */
	public String getBotDisplay();

	/**
	 * Stores the latest string received from the network
	 * 
	 * @param netString
	 *            the string to be stored
	 */
	public void setNetString(String netString);

	/**
	 * Fetch the latest string received from the network
	 * 
	 * @return the stored string
	 */
	public String getNetString();

	/**
	 * Are there any updates to the information used for the display
	 * 
	 * @param since
	 *            the system time in milliseconds from when we're interested in
	 *            updates
	 * @return true if there are updates more recent than the specified time
	 */
	public boolean hasDisplayUpdated(Long since);

	/**
	 * Are there any new user input
	 * 
	 * @param since
	 *            the system time in milliseconds from when we're interested in
	 *            input
	 * @return true if there are input more recent than the specified time
	 */
	public boolean haveNewUserInput(Long since);

	/**
	 * Stores the port the program is listening on
	 * 
	 * @param port
	 *            the port number to be stored
	 */
	public void setPort(int port);

	/**
	 * Get the port number the program is listening on
	 * 
	 * @return the port number the program is listening on
	 */
	public int getPort();

	/**
	 * Store the address of the connected client
	 * 
	 * @param address
	 *            the address of the connected client
	 */
	public void setAddress(InetAddress address);

	/**
	 * retrieve the address of the connected client
	 * 
	 * @return address the address of the connected client
	 */
	public InetAddress getAddress();

	/**
	 * Sets the tare weight to current load on the scale
	 * @param tweight 
	 */
	public void tare();

	/**
	 * set the current load on the weight
	 * 
	 * @param weight
	 */
	public void setGross(double weight);

	/**
	 * @return the current load on the scale
	 */
	public double getGross();

	/**
	 * @return the net weight on the scale (gross - tare)
	 */
	public double getNet();

	/**
	 * @return the current text on the display
	 */
	public String getDisplayText();

	/**
	 * Set the text on the scale display
	 * 
	 * @param text
	 *            the text to be set on the display
	 */
	public void setDisplayText(String text);

	/**
	 * Retrieve the latest stored user input
	 * 
	 * @return the last string that was stored as user input
	 */
	public String getUserInput();

	/**
	 * Store user input for later use
	 * 
	 * @param text
	 *            the string to be stored
	 */
	public void setUserInput(String text);

	/**
	 * reset the display text, load and tare weight
	 */
	public void reset();

	/**
	 * set the program state to close the program
	 */
	public void quit();

	/**
	 * Tests if the program is still (supposed to be) running
	 * 
	 * @return <code>true</code> until <code>quit()</code> has been executed and
	 *         <code>false</code> after <code>quit()</code> has been executed.
	 */
	public boolean isRunning();

}
