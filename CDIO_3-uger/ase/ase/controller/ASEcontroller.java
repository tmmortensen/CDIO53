package ase.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import admin.data.UserDTO;
import ase.boundary.ASEboundary;
import ase.data.ASEdata;

public class ASEcontroller {
	
	private boolean done = false, doMore = true, name = false;
	private int tempProductBatch, tempRaavareBatch;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private double beholderTara;

	ASEboundary bound = new ASEboundary();
	ASEdata data = new ASEdata();
	
	
	public ASEcontroller() {
		int oprID = bound.sendOprIDToDB();
		while (!done == true) {
			try{
				UserDTO test = data.UserDTOgetUser(oprID);
				System.out.println(data.UserInfo(test));
			}
			catch(Exception e){
				System.out.println("Hov hov du.");
			}
			
			if (bound.name() == true) {
				name = true;
			}

			while (name == true) {
//				tempProductBatch = bound.produktbatch();
				bound.doMore();
				while (doMore == true) {
					bound.aflastet();
					beholderTara = bound.beholder();
					bound.tara();
					tempRaavareBatch = bound.raavareBatch();
					bound.weight();
					if (bound.stop() == true) {
						break;
					}
					if (bound.doMore() == true) {
						doMore = true;
					} else
						break;

				}
				name = false;
			}
			if (!bound.newUser() == true) {
				done = true;
			}
		}

	}
//	public String getOprName(int oprID){
//		oprID = this.oprID;
//		String OprName = data.getDBName(oprID);
//		return OprName;
//	}
}
