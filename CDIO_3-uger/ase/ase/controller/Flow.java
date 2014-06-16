package ase.controller;

import java.util.List;
import java.io.IOException;

import admin.data.CommodityBatchData;
import admin.data.DALException;
import admin.data.ICommodityBatchDAO;
import admin.data.IPrescriptionCompDAO;
import admin.data.IPrescritpionDAO;
import admin.data.IProductBatchCompDAO;
import admin.data.IProductBatchDAO;
import admin.data.IUserDAO;
import admin.data.PrescriptionCompDTO;
import admin.data.PrescriptionCompData;
import admin.data.PrescriptionDTO;
import admin.data.PrescriptionData;
import admin.data.ProductBatchCompDTO;
import admin.data.ProductBatchCompData;
import admin.data.ProductBatchDTO;
import admin.data.ProductBatchData;
import admin.data.UserDTO;
import admin.data.UserData;
import ase.boundary.ASEBoundary;
import ase.data.DBConnection;

public class Flow {

	private boolean done, reset = false;
	private boolean step_done, sub_step_done;
	private int userID, productBatchID, presID, commodityID, commodityBatchID;
	private double tare, netto;

	public void start() throws IOException {
		DBConnection data = new DBConnection(); // Nyt navn afventes.
		ASEBoundary bound = new ASEBoundary();

		while (!done) {
			reset = false; // Bliver sat til true, hvis vi ønsker at
							// genstarte.
			step_done = false;
			while (!step_done) { // Step 3+4
				String userName;

				userID = bound.getID();
				try {
					IUserDAO users = new UserData();
					userName = users.getUser(userID).getUsername();
					if (bound.sendConfirm(userName)) {
						step_done = true;
					} else {
						continue;
					}
				}

				catch (DALException e) {
					bound.sendError(e.getMessage());
					continue;
				}
			}

			step_done = false;
			while (!reset && !step_done) { // step 5+6 Produktbatch input
											// state
				productBatchID = bound.getProductBatchID();
				try {
					IProductBatchDAO products = new ProductBatchData();
					IPrescritpionDAO prescriptions = new PrescriptionData();
					ProductBatchDTO item = products
							.getProductBatch(productBatchID);
					PrescriptionDTO prescription = prescriptions
							.getPrescription(item.getPrescriptionId());

					if (item.getUserID() == userID) {
						if (!bound.sendConfirm(prescription.getName()))
							if (bound.retry()) {
								continue;
							} else {
								reset = true;
							}
						step_done = true;
					} else {
						bound.sendError("Du er ikke tilknyttet det indtastede produktbatch");
						if (bound.retry()) {
							continue;
						} else {
							reset = true;
						}
					}

				} catch (DALException e) {
					bound.sendError(e.getMessage());
					if (bound.retry()) {
						continue;
					} else {
						reset = true;
					}
				}

			}

			// step 7-12. Raavarebatch input
			// state
			if (!bound.clearWeight()) {
				reset = true;
			}

			tare = bound.getTara();
			if (tare < 0) {
				reset = true;
			}

			// step 12,5
			IProductBatchCompDAO proComps = new ProductBatchCompData();

			List<PrescriptionCompDTO> preCompList = proComps
					.getUnfulfilledComps(presID);
			
			if(preCompList.isEmpty()){
				bound.sendError("Der er ikke flere komponenter der mangler at blive afvejet."); //TODO forkort besked.
				reset = true;
			}else{
				commodityID = preCompList.get(0).getCommodityId();
			}
			
			step_done = false;
			while (!reset && !step_done) {// step 13. Indtast på varebatch ID state
				ICommodityBatchDAO commBatch = new CommodityBatchData();							
				commodityBatchID = bound.getRaavareBatchID(commodityID);
				
				if(commBatch.getCommodityBatch(commodityBatchID).getCommodityId() != commodityID){
					bound.sendError("BatchID forkert.");
				}
				// if(id){
				// if(bound.retry())
				// continue;
				// else
				// break;
				// reset = true;
				// }
				// else{
				// bound.outRaavareID(id);
				// }
			}

			step_done = false;
			while (!reset && !step_done) { // step 14

				double n = bound.getNettoWeight(); // På de værdier vi får fra
													// step4.
				// double dataNetto = data.getNetto();
				// double dataTolerance = data.getTolerance();
				// En sammenligning fra værdien på vægten og det vi har fra
				// databasen.
				// if(n =< dataNetto*(1+dataTolerance) && n >=
				// dataNetto*(1-dataTolerance))
				if (bound.retry())
					continue;
				else {
					reset = true;

				}
				// else{

				// }
			}
			step_done = false;
			while (!reset && !step_done) { // Step 14.25. Step hvor data gemmes.
				// data.saveData();
			}

			while (!reset && step_done) { // step 14,5

				if (bound.getQuit()) {
					// data.produktStatus("afslutte");

				}
			}
		}
	}
}
