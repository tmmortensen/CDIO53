package simulator.boundary;

import simulator.data.IProgramState;

public class OutputBoundary implements IBoundary {

	private long lastRefresh = 0;
	private IProgramState programState;

	public OutputBoundary(IProgramState programState) {
		this.programState = programState;
	}

	@Override
	public void closeResources() {
		try {
			System.out.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void run() {
		while (programState.isRunning()) {
			if (programState.hasDisplayUpdated(lastRefresh)) {
				lastRefresh = System.currentTimeMillis();
				printmenu();
			}
			try {
				this.wait(100);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Method to print console menu
	 */
	public void printmenu() {
		// for (int i = 0; i < 25; i++)
		// System.out.println(" ");

		String adress = "null";
		try {
			adress = programState.getAddress().getHostAddress();
		} catch (Exception e) {
		}

		System.out.println("*************************************************");
		System.out.println("Netto: " + programState.getNet() + " kg");
		System.out.println("Instruktionsdisplay: "
				+ programState.getDisplayText());
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + adress);
		System.out.println("Brutto: " + programState.getGross() + " kg");
		System.out.println("Streng modtaget: " + programState.getNetString());
		System.out.println(" ");
		System.out.println("Denne vægt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("På kommunikationsporten ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk på vægt)");
		System.out
				.println("Tast B for ny brutto (svarende til at belastningen "
						+ "på vægt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / "
				+ "quit)");
		System.out.print("Tast her: ");
	}
}
