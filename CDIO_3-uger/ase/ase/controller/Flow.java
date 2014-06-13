package ase.controller;

public class Flow implements Runnable {

	
	
	ConnectionSetup connect = new ConnectionSetup();
	
	public void run(){
	
		connect.initiate();
		
	}
}

