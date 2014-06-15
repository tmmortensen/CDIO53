package ase.controller;

import ase.data.DBConnection;

public class Flow {

	private boolean done = false, confirm = false;
	private boolean step_done, step2_done, step3_done, step4_done = false;
	private boolean step5_done, step6_done, step7_done = false;

	public void start() {
		DBConnection DB = new DBConnection();
		while (!done) {
			boolean reset = false; // Bliver sat til true, hvis vi ønsker at
									// genstarte.
			
			while (!reset && !step_done) { //Step 1
				int id = bound.getID();
				String username = DB.checkName(id);
				confirm = bound.send(username);
				if (confirm)
					step_done = true;
			}


			while (!reset && !step2_done && step_done) { // step 2 Produktbatch input
															// state
				int produktBatchID = bound.getProduktBatchID();
				if (!DB.checkProduktBatchID(produktBatchID)) {// Punktet her
																// skal tjekke
																// på
																// produktbatch
					if (bound.retry())
						continue;
					else{
						reset = true;
						step_done = false;
						break;
						}
				} else
					step_done = false;
					step2_done = true;
			}

			while (!reset && !step3_done && step2_done) { // step 3. Raavarebatch input
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
			while (!reset && !step4_done && step3_done) {// step 4. Finde receptkomponent og huske den.				
				step3_done = false;
				step4_done = true;
			}
			while(!reset && !step5_done && step4_done){// step 5. Indtast på varebatch ID state
				DB.getRaavareID();
				bound.outRaavareID();
				int id = bound.getRaavareBatchID();
				int id2 = DB.getRaavareBatchID();
					if(id == id2){
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
			
			
			while(!reset && !step5_done && step5_done){ //step 6
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
			while(!reset && !step7_done && step6_done){ //Step 7. Step hvor data gemmes.
				DB.saveData();
				step6_done = false;
				step7_done = true;
			}
			
			while(!reset && step7_done){

				boolean quit = bound.getQuit();
					if(quit){
						DB.produktStatus("afslutte");
						step7_done = false;
					}
			}
		}
	}
}
