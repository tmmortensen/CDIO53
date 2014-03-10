package boundary;

import java.util.Scanner;

import data.Global;


public class InputBoundary implements IBoundary {

	Scanner input;
	
	public InputBoundary(Scanner in) {
		input = in;
	}

	public void run() {
		while(!Global.exit){
			String userInput = input.nextLine();
			if(userInput.equalsIgnoreCase("T")){
				Global.tara = Global.brutto;
				Global.lastUpdate = System.currentTimeMillis();
			}
			else if(userInput.equalsIgnoreCase("B")){
				System.out.println("Indtast brutto vægt.");
				userInput = input.nextLine();
				try{
					Global.brutto = Double.parseDouble(userInput);
					Global.lastUpdate = System.currentTimeMillis();
				}
				catch(Exception e){
					System.out.println("Indtastet kan ikke genkendes som tal.");
				}	
			}
			else if(userInput.equalsIgnoreCase("Q")){
				Global.exit = true;
				return;
			}
		}
	}
}
