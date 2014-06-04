package simulator.controller;

import java.io.IOException;

import simulator.boundary.GUI;
import simulator.boundary.IBoundary;
import simulator.boundary.InputBoundary;
import simulator.boundary.NetworkIOBoundary;
import simulator.boundary.OutputBoundary;
import simulator.data.IProgramState;
import simulator.data.ProgramState;

/**
 * The Main class that runs the program
 * 
 * @author Gruppe 53
 * 
 */
public class Main {

	/**
	 * Runs the program
	 * 
	 * @param args
	 * @throws IOException
	 *             thrown if port number is invalid
	 */
	public static void main(String[] args) throws IOException {
		int portdst;
		IProgramState programState = new ProgramState();

		if (args.length > 0)
			try {
				portdst = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("Port argument ugyldigt. "
						+ "Bruger default 8000.");
				portdst = 8000;
			}
		else {
			System.out.println("Bruger default port 8000.");
			portdst = 8000;
		}

		// input setup and run
		IBoundary input = new InputBoundary(programState);
		Thread inputThread = new Thread(input);
		inputThread.start();

		// output setup and run
		IBoundary output = new OutputBoundary(programState);
		Thread outputThread = new Thread(output);
		outputThread.start();

		// gui setup and run
		GUI gui = new GUI(programState);
		Thread guiThread = new Thread(gui);
		guiThread.start();

		// Network setup and run
		programState.setPort(portdst);
		NetworkIOBoundary network = new NetworkIOBoundary(programState);
		Thread networkThread = new Thread(network);
		networkThread.start();

		// wait until program is closed
		while (programState.isRunning()) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}

		network.closeResources();
		input.closeResources();
		output.closeResources();
		gui.closeResources();
		// System.exit(0);

	}
}
