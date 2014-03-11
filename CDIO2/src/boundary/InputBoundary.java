package boundary;

import java.util.Scanner;

import data.IProgramState;


public class InputBoundary implements IBoundary {

	Scanner input;
	IProgramState programState;
	
	public InputBoundary(Scanner in, IProgramState programState) {
		input = in;
		this.programState = programState;
	}

	public void run() {
		while(programState.isRunning()){
			String userInput = input.nextLine();
			if(userInput.equalsIgnoreCase("T")){
				programState.tare();
			}
			else if(userInput.equalsIgnoreCase("B")){
				System.out.println("Indtast brutto vægt.");
				userInput = input.nextLine();
				try{
					programState.setGross(Double.parseDouble(userInput));
				}
				catch(Exception e){
					System.out.println("Indtastet kan ikke genkendes som tal.");
				}	
			}
			else if(userInput.equalsIgnoreCase("Q")){
				programState.quit();
				return;
			} else
				programState.setUserInput(userInput);
		}
	}
}
