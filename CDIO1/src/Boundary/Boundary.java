package Boundary;

import java.util.Scanner;

public class Boundary implements IBoundary {
	String oprId;
	String password;

	Scanner input = new Scanner(System.in);

	public Boundary() {
		login();
	}

	@Override
	public String[] login() {
		String[] login = new String[2];
		login[0] = oprId;
		login[1] = password;

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getInt(String msg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int menu(String[] options, String header) {
		System.out.println(header + "\n");
		for (int i = 0; i <= options.length; i++) {
			System.out.println(options[i]);
		}
		int menuInput = input.nextInt();
		if (menuInput > options.length || menuInput < options.length)
			System.out.println("Ikke korrekt talværdi - tast igen");
		return menuInput;
	}

	@Override
	public void showStringMessage(String msg) {
		System.out.println(msg);

	}

}
