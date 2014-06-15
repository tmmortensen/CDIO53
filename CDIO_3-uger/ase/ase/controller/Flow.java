package ase.controller;

public class Flow implements Runnable {

	private boolean state_done, done = false, sub_step_done, confirm = false;
	private boolean step_done = false;
	private boolean step2_done = false, step3_done = false, step4_done = false, step5_done = false, step6_done = false;
	private boolean step7_done;
	public void run() {

		while (!done) {
			boolean reset = false; // Bliver sat til true, hvis vi ønsker at
									// genstarte.
			state_done = false;

			while (!reset && !state_done) { //Step 1
				bound.getID();
				String username = DB.checkName();
				bound.sendName(username);
				confirm = bound.send(username);
				if (confirm)
					step_done = true;
				else
					continue;

			}

			state_done = false;

			while (!reset && !state_done && step_done) { // step 2 Produktbatch input
															// state
				int produktBatchID = bound.getProduktBatchID();
				if (!DB.checkProduktBatchID(produktBatchID)) {// Punktet her
																// skal tjekke
																// på
																// produktbatch
					if (bound.retry())
						continue;
					else
						reset = true;
					break;
				} else
					step_done = false;
				step2_done = true;
			}

			while (!reset && !state_done && step2_done) { // step 3. Raavarebatch input
															// state
				boolean result = bound.zero(); // I boundary skal operatøren
												// bedes om at tømme vægten og
												// trykke "ok", og derefter
												// tareres vægten
				if (!result) {
					reset = true;
					step2_done = false;
				}
				double t = bound.tara(); // Operatøren bedes at placeres tara og
											// trykke ok.
				step2_done = false;
				step3_done = true;
			}
			while (!reset && !state_done && step3_done) {// step 4. Finde receptkomponent og huske den.				
				step3_done = false;
				step4_done = true;
			}
			while(!reset && !state_done && step4_done){// step 5. Indtast på varebatch ID state
				DB.getRaavareID();
				bound.outRaavareID();
				int id = bound.getRaavareBatchID();
					if(DB.checkRaavareBatchID(id)){
						if(bound.retry)
							continue;
						else 
							break;
							reset = true;
					}
					else{
						step4_done = false;
						step5_done = true;
					}
			}
			
			
			while(!reset && !state_done && step5_done){ //step 6
				double n = b.getNetto(double netto, double tolerance); //På de værdier vi får fra step4.
//				En sammenligning fra værdien på vægten og det vi har fra databasen.
					if(fail)
						if(bound.retry)
							continue;
						else{
							reset = true;
							step5_done = false;
						}
					else{
					step5_done = false;
					step6_done = true;
					}
			}
			while(!reset && !state_done && step6_done){ //Step 7. Step hvor data gemmes.
				DB.saveData();
				step6_done = false;
				step7_done = true;
			}
			
			while(!reset && !state_done && step7_done){

				boolean quit = bound.getQuit();
					if(quit){
						DB.produktStatus("afslutte");
						step7_done = false;
					}
			}		}
	}
}
