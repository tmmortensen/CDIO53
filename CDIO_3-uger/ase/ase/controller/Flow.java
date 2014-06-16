package ase.controller;

import ase.boundary.ASEBoundary;
import ase.data.DBConnection;

public class Flow {

	private boolean done, reset, confirm = false;
	private boolean step_done, step2_done, step3_done, step4_done = false;
	private boolean step5_done, step6_done, step7_done = false;

	public void start() {
		DBConnection DB = new DBConnection();
		ASEBoundary bound = new ASEBoundary();
		while (!done) {
			reset = false; // Bliver sat til true, hvis vi ønsker at
									// genstarte.
			
			while (!reset && !step_done) { //Step 3+4
				int id = bound.getID();
				String username = DB.checkName(id);
				confirm = bound.sendUsername(username);
				if (confirm)
					step_done = true;
			}


			while (!reset && !step2_done && step_done) { // step 5+6 Produktbatch input
															// state
				int produktBatchID = bound.getProductBatchID();
				int produktBatchID2 = DB.checkProduktBatchID(produktBatchID);
				if (produktBatchID == produktBatchID2) {// Punktet her
																// skal tjekke
																// på
																// produktbatch
					if (bound.retry())
						continue;
					else{
						reset = true;
						step_done = false; //Mangler et punkt 6 der smider navn på produktbatch ud.
						break;
						}
				} else
					step_done = false;
					step2_done = true;
			}

			while (!reset && !step3_done && step2_done) { // step 7+8. Raavarebatch input
															// state
				bound.drainWeight();						 // I boundary skal operatøren
				bound.getTara();							// bedes om at tømme vægten og
												// trykke "ok", og derefter
												// tareres vægten
//				if (!result) {
//					reset = true;
//					step2_done = false;
				step3_done = true;
				step2_done = false;
				}
				double t = bound.getTara(); // step 9+10+11 Operatøren bedes at placeres tara og
											// trykke ok.
				step2_done = false;
				step3_done = true;
			}
			while (!reset && !step4_done && step3_done) {// step 12,5. Finde receptkomponent og huske den.				
				step3_done = false;
				step4_done = true;
			}
			while(!reset && !step5_done && step4_done){// step 13. Indtast på varebatch ID state
				DB.getRaavareID();
				int id = bound.getRaavareBatchID();
				int id2 = DB.checkRaavareBatchID(id);
				if(id == id2){
						if(bound.retry())
							continue;
						else 
							break;
							reset = true;
					}
					else{
						step4_done = false;
						step5_done = true;
						bound.outRaavareID(id);
					}
			}
			
			
			while(!reset && !step5_done && step5_done){ //step 14
				
				double n = bound.getNettoWeight(); //På de værdier vi får fra step4.
				double dataNetto = data.getNetto();
				double dataTolerance = data.getTolerance();
				//En sammenligning fra værdien på vægten og det vi har fra databasen.
					if(n =< dataNetto*(1+dataTolerance) && n >= dataNetto*(1-dataTolerance))
						if(bound.retry())
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
			while(!reset && !step7_done && step6_done){ //Step 14.25. Step hvor data gemmes.
				DB.saveData();
				step6_done = false;
				step7_done = true;
			}
			
			while(!reset && step7_done){ //step 14,5

				boolean quit = bound.getQuit();
					if(quit){
						DB.produktStatus("afslutte");
						step7_done = false;
					}
			}
		}
	}

