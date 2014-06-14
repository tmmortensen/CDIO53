package ase.controller;

public class Flow implements Runnable {

	private boolean state_done, done = false, sub_step_done;
	private boolean step_done = false;
	private boolean step2_done = false;
	public void run() {

		while (!done) {
			boolean reset = false;
			state_done = false;

			while (!reset && !state_done) {
				// bound.getID();
				// username = checkID();
				// confirm = bound.send(username);
				// if(continues)
				// step_done = true;

			}
			
			state_done = false;
			
			while(!reset && !state_done && step_done){ //Produktbatch input state
//				bound.getPBID();
//				Indsæt punkt her der tjekker på produktbatch
//				if(fail)
//					if(prøv et andet?)
//						continue;
//					else
//						reset = true;
//						break;
//				else
//					step2_done = true;
			}
			
			while(!reset && !state_done && step2_done){ //Raavarebatch input state
//				result = bound.zero(); //I boundary skal operatøren bedes om at tømme vægten og trykke "ok", og derefter tareres vægten
//					if(result != succes){
//						reset = true;
//						step_done = true;
//					}
//				double t = bound.tara() //Operatøren bedes at placeres tara og trykke ok.
				//Skal checkes som før.
				
//				Her skal finde receptkomponent og huske den.
				sub_step_done = false;
			}
			
			while(!reset && !state_done && !sub_step_done){//Indtast på varebatch ID state
//				DB.getRaavareID();
//				bound.outRaavareID();
//				int id = bound.getRaavareBatchID();
//				Check på ID her
//					if(fail)
//						retry?
//							continue;
//						else 
//							break;
//							reset = true;
//					else
//						sub_step_done = true;
			}
			
			sub_step_done = false;
			while(!reset && !state_done && !sub_step_done){
//				double n = b.getNetto(double netto, double tolerance);
				
//				Kontrollere om netto overholder tolerance.
//					if(fail)
//						retry?
//							continue;
//					else
//					sub_step_done = true;
			}
//			while(){ // Step hvor data gemmes.
//			}
			
//			boolean quite = bound.getQuit();
//				if(quit)
//					DB.produktStates("afslutte");
			
//			Og så smid tilbage til starten.
		}
	}
}
