package Boundary;

import java.util.Scanner;

public class Boundary implements IBoundary {

	Scanner input = new Scanner(System.in);

	public Boundary() {
		login();
	}

	@Override
	public void login() {
		String[] typeLogin = { " ", " " };
		System.out.println("Indtast oprID");
		String oprID = input.nextLine();
		typeLogin[0] = oprID;
		System.out.println("Indtast password");
		String password = input.nextLine();
		typeLogin[1] = password;

		if (oprID == "1234" && password == "1234") {
			showmenu();
		}

	}

	@Override
	public void operatorAdmin() {

	}

	@Override
	public void changePassword() {

	}

	@Override
	public void test() {

	}

	@Override
	public void exit() {

	}

	@Override
	public int showmenu() {

		System.out.print("Vælg en funktion:\n" + "1. Operatør Admin.\n"
				+ "2. Ændre password\n" + "3. Test\n" + "4. Afslut\n");

		int menuChoice = input.nextInt();

		switch (menuChoice) {
		case 1:
			operatorAdmin();
			break;
		case 2:
			changePassword();
			break;
		case 3:
			// test();
			break;
		case 4:
			System.out.println("Systemet lukkes");
			System.exit(0);
			break;
		default:
			// The user input an unexpected choice.
		}
		return menuChoice;
	}

}
