package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Vaegtsimulator_med_consol_opg {
	static ServerSocket listener;
	private static double brutto = 0;
	private static double tara = 0;
	private static String inline;
	private static String InstruktionsDisplay = "";
	private static int portdst = 8000;
	private static Socket sock;
	private static BufferedReader instream;
	private static DataOutputStream outstream;

	public static void printmenu() {
		for (int i = 0; i < 25; i++)
			System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (brutto - tara) + " kg");
		System.out.println("Instruktionsdisplay: " + InstruktionsDisplay);
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + sock.getInetAddress());
		System.out.println("Brutto: " + (brutto) + " kg");
		System.out.println("Streng modtaget: " + inline);
		System.out.println(" ");
		System.out.println("Denne vegt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("paa kommunikationsporten. ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vegt)");
		System.out
				.println("Tast B for ny brutto (svarende til at belastningen paa vegt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out
				.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		System.out.print("Tast her: ");
	}

	public static void main(String[] args) throws IOException {
		listener = new ServerSocket(portdst);
		System.out.println("Venter paa connection paa port " + portdst);
		System.out.println("Indtast eventuel portnummer som 1. argument");
		System.out.println("paa kommando linien for andet portnr");
		sock = listener.accept();
		instream = new BufferedReader(new InputStreamReader(
				sock.getInputStream()));
		outstream = new DataOutputStream(sock.getOutputStream());
		printmenu();
		try {
			while (!(inline = instream.readLine().toUpperCase()).isEmpty()) {
				if (inline.startsWith("DN")) {
					// ikke implimenteret
				} else if (inline.startsWith("D")) {
					if (inline.equals("D"))
						InstruktionsDisplay = "";
					else
						InstruktionsDisplay = (inline.substring(2,
								inline.length()));
					printmenu();
					outstream.writeBytes("DB" + "\r\n");
				} else if (inline.startsWith("T")) {
					outstream.writeBytes("T " + (tara) + " kg " + "\r\n");
					tara = brutto;
					printmenu();
				} else if (inline.startsWith("S")) {
					printmenu();
					outstream.writeBytes("S " + (brutto - tara) + " kg "
							+ "\r\n");
				} else if (inline.startsWith("B")) { // denne ordre findes
					// ikke på en fysisk vægt
					String temp = inline.substring(2, inline.length());
					brutto = Double.parseDouble(temp);
					printmenu();
					outstream.writeBytes("DB" + "\r\n");
				} else if ((inline.startsWith("Q"))) {
					System.out.println("");
					System.out
							.println("Program stoppet Q modtaget paa com port");
					System.in.close();
					System.out.close();
					instream.close();
					outstream.close();
					System.exit(0);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
