package data;

import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Global {
	public static boolean exit;
	public static double tara;
	public static double brutto;
	public static String display;
	public static String networkString;
	public static int port;
	public static InetAddress address;
	public static long lastUpdate;
	public static String lastInputString;
	public static long lastInputTime;
	
	public static ServerSocket listener;
	public static BufferedReader instream;
}
