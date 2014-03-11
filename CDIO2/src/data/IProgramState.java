package data;

import java.net.InetAddress;

public interface IProgramState {
	/**
	 * Stores the latest string received from the network
	 * @param netString the string to be stored
	 */
	public void setNetString(String netString);
	
	/**
	 * Fetch the latest string received from the network
	 * @return the stored string
	 */
	public String getNetString();
	
	/**
	 * Test whether there are any updates to the information used for the display
	 * @param since the system time in milliseconds from which we're interested in updates since 
	 * @return true if there are updates which are more recent than the specified time
	 */
	public boolean hasDisplayUpdated(Long since);
	
	public boolean haveNewUserInput(Long since);
	
	public void setPort(int port);
	
	public int getPort();
	
	public void setAdress(InetAddress adress);
	
	public InetAddress getAdress();
	
	/**
	 * Sets the tare weight to current load on the scale 
	 */
	public void tare();
	
	/**
	 * set the current load on the weight
	 * @param weight
	 */
	public void setGross(double weight);
	
	public double getGross();
	
	/**
	 * @return the net weight on the scale
	 */
	public double getNet();
	
	public String getDisplayText();
	
	public void setDisplayText(String text);
	
	public String getUserInput();
	
	public void setUserInput(String text);
	
	public void reset();
	
	/**
	 * set the program state to close the program
	 */
	public void quit();
	
	public boolean isRunning();
	
}
