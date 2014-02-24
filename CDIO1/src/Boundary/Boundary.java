package Boundary;

import java.util.Scanner;

public class Boundary implements IBoundary {

	Scanner input = new Scanner(System.in);

	public Boundary() {

	}

	@Override
	public String[] login() {
		System.out.println("Log på systemet");
		String[] login = new String[2];
		System.out.println("Indtast operatør ID: ");
		String oprId = input.nextLine();
		login[0] = oprId;
		System.out.println("Indtast password: ");
		String password = input.nextLine();
		login[1] = password;
		return login;
	}

	@Override
	public String getString(String msg) {
		System.out.println(msg);
		String stringinput = input.nextLine();
		return stringinput;
	}

	@Override
	public int getInt(String msg) {
		System.out.println(msg);
		int getint = input.nextInt();
		return getint;
	}

	@Override
	public int menu(String[] options, String header) {
		System.out.println(header);
		for (int i = 0; i <= options.length; i++) {
			System.out.println(options[i]);
		}
		int menuInput = input.nextInt();
		while (menuInput > options.length + 1 || menuInput < 1) {
			System.out.println("Ikke korrekt talværdi - tast igen");
			menuInput = input.nextInt();
		}
		return menuInput;
	}

	@Override
	public void showStringMessage(String msg) {
		System.out.println(msg);

	}

}
