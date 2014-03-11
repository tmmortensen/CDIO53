package data;

import java.net.InetAddress;

public class ProgramState implements IProgramState {
	double tare = 0;
	double gross = 0;
	String display = "";
	String netString = "";
	String userInput = "";
	
	int port = 0;
	InetAddress address;

	boolean exit = false;
	long lastUpdate = 0;
	long lastInput = 0;
	
	public ProgramState() {
		lastUpdate = System.currentTimeMillis();
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
	
	public int getPort(){
		return port;
	}

	public void setAddress(InetAddress address){
		this.address = address;
		lastUpdate = System.currentTimeMillis();
	}
	
	public InetAddress getAddress(){
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
	
	public double getGross(){
		return gross;
	}
	
	
	public double getNet() {
		return gross - tare;
	}
	
	public String getDisplayText(){
		return display;
	}
	
	public void setDisplayText(String text){
		display = text;
		lastUpdate = System.currentTimeMillis();
	}
	
	public void reset(){
		gross = 0;
		tare = 0;
		display = "";
		lastUpdate = System.currentTimeMillis();
	}
	
	public void quit(){
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

}
