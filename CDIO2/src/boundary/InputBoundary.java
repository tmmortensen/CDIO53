package boundary;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import data.IProgramState;

public class InputBoundary implements IBoundary {

	BufferedReader consoleReader;
	IProgramState programState;

	public InputBoundary(IProgramState programState) {
		this.programState = programState;
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void closeResources(){
		try{
			consoleReader.close();
			System.in.close();
		} catch(Exception e) {}
	}

	public void run() {
		while (programState.isRunning()) {
			try {
				if (!consoleReader.ready())
					continue;
				String userInput = consoleReader.readLine();
				if (userInput.equalsIgnoreCase("T")) {
					programState.tare();
				} else if (userInput.equalsIgnoreCase("B")) {
					System.out.println("Indtast brutto vægt.");
					while (!consoleReader.ready()) {
						if (!programState.isRunning())
							return;
					}
					userInput = consoleReader.readLine();
					try {
						programState.setGross(Double.parseDouble(userInput));
					} catch (Exception e) {
						System.out
								.println("Indtastet kan ikke genkendes som tal.");
					}
				} else if (userInput.equalsIgnoreCase("Q")) {
					programState.quit();
					return;
				} else
					System.out.println("user input :\"" + userInput + "\"");
					programState.setUserInput(userInput);
			} catch (Exception e) {
			}
		}
	}
}
