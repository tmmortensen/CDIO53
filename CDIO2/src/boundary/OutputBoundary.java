package boundary;

import data.Global;

public class OutputBoundary implements IBoundary {
	
	private long lastRefresh = 0; 

	public void run() {
		while(!Global.exit){
			if(Global.lastUpdate > lastRefresh){
				lastRefresh = System.currentTimeMillis();
				printmenu();
				
			}
			try{
				this.wait(100);	
			}
			catch(Exception e){
				
			}
		}
		
	}
	public void printmenu() {
		for (int i = 0; i < 25; i++)
			System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (Global.brutto - Global.tara) + " kg");
		System.out.println("Instruktionsdisplay: " + Global.display);
		System.out.println("*************************************************");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Debug info: ");
		System.out.println("Hooked up to " + Global.address);
		System.out.println("Brutto: " + (Global.brutto) + " kg");
		System.out.println("Streng modtaget: " + Global.networkString);
		System.out.println(" ");
		System.out.println("Denne vægt simulator lytter på ordrene ");
		System.out.println("D, DN, S, T, B, Q ");
		System.out.println("På kommunikationsporten ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk på vægt)");
		System.out
				.println("Tast B for ny brutto (svarende til at belastningen på vægt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out
				.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		System.out.print("Tast her: ");
}
}
