package simulator.data;

import java.net.InetAddress;

/**
 * Class used to change the program state
 * 
 * @author Gruppe 53
 * 
 */
public class ProgramState implements IProgramState {
	double tare = 0;
	double gross = 0;
	String display = "";
	String netString = "";
	String userInput = "";
	String botDisplay = "";
	String prefix = "";

	int port = 0;
	InetAddress address;

	boolean confirmed;
	boolean exit = false;
	long lastUpdate = 0;
	long lastInput = 0;

	public ProgramState() {
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void setBotDisplay(String botDisplay) {
		this.botDisplay = botDisplay;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public String getBotDisplay() {
		return botDisplay;
	}

	@Override
	public void setNetString(String netString) {
		this.netString = netString;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public String getNetString() {
		return netString;
	}

	@Override
	public boolean hasDisplayUpdated(Long since) {
		return lastUpdate > since;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setAddress(InetAddress address) {
		this.address = address;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public InetAddress getAddress() {
		return address;
	}

	@Override
	public boolean isRunning() {
		return !exit;
	}

	@Override
	public void tare() {
		tare = gross;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void setGross(double weight) {
		gross = weight;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public double getGross() {
		return gross;
	}

	@Override
	public double getNet() {
		return gross - tare;
	}

	@Override
	public String getDisplayText() {
		return display;
	}

	@Override
	public void setDisplayText(String text) {
		display = text;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void reset() {
		gross = 0;
		tare = 0;
		display = "";
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void quit() {
		exit = true;
	}

	@Override
	public boolean haveNewUserInput(Long since) {
		return lastInput > since;
	}

	@Override
	public String getUserInput() {
		return userInput;
	}

	@Override
	public void setUserInput(String text) {
		userInput = text;
		lastInput = System.currentTimeMillis();
	}

	@Override
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public boolean getConfirmed() {

		return confirmed;
	}

	@Override
	public void setPrefix(String prefix) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

}
