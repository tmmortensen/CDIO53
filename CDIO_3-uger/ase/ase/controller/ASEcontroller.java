package ase.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import ase.boundary.ASEboundary;

public class ASEcontroller {
	private boolean done = false, doMore = true, name = false;
	private int tempProductBatch, tempRaavareBatch;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	private double beholderTara;

	ASEboundary bound = new ASEboundary();

	public ASEcontroller() {
		while (!done == true) {
			if (bound.name() == true) {
				name = true;
			}

			while (name == true) {
				tempProductBatch = bound.produktbatch();
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

}