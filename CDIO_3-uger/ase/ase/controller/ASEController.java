package ase.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

import admin.data.CommodityBatchData;
import admin.data.DALException;
import admin.data.ICommodityBatchDAO;
import admin.data.IPrescritpionDAO;
import admin.data.IProductBatchCompDAO;
import admin.data.IProductBatchDAO;
import admin.data.IUserDAO;
import admin.data.PrescriptionCompDTO;
import admin.data.PrescriptionDTO;
import admin.data.PrescriptionData;
import admin.data.ProductBatchCompDTO;
import admin.data.ProductBatchCompData;
import admin.data.ProductBatchDTO;
import admin.data.ProductBatchData;
import admin.data.StatusType;
import admin.data.UserData;
import ase.boundary.ASEBoundary;

public class ASEController {

	private static boolean done, reset = false;
	private static boolean step_done, subRoutineDone;
	private static int userID, productBatchID, presID, commodityID, commodityBatchID;
	private static double tare, netto;

	static IProductBatchDAO products = new ProductBatchData();
	static IProductBatchCompDAO proComps = new ProductBatchCompData();

	public static void main(String args[]) throws IOException {
		ASEBoundary bound = new ASEBoundary(args[1]);

		while (!done) {
			reset = false; // Bliver sat til true, hvis vi ønsker at
							// genstarte.
			step_done = false;
			while (!step_done) { // Step 3+4
				String userName;

				userID = bound.getID();
				if (userID < 0)
					continue;
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
					bound.sendError("Fejl i bruger id");
					continue;
				}
			}

			step_done = false;
			while (!reset && !step_done) { // step 5+6 Produktbatch input
											// state
				productBatchID = bound.getProductBatchID();
				try {
					IPrescritpionDAO prescriptions = new PrescriptionData();
					ProductBatchDTO item = products
							.getProductBatch(productBatchID);
					PrescriptionDTO prescription = prescriptions
							.getPrescription(item.getPrescriptionId());

					if (item.getUserId() != userID) {
						bound.sendError("Forkert Operatoer");
						if (bound.retry()) {
							continue;
						} else {
							reset = true;
						}
					} else if (item.getStatus() != StatusType.NEW && item.getStatus() != StatusType.PAUSED){
						bound.sendError("Fejl i status:"+ item.getStatus().ordinal());
						if (bound.retry()) {
							continue;
						} else {
							reset = true;
						}
					} else {
						if (!bound.sendConfirm(prescription.getName()))
							if (bound.retry()) {
								continue;
							} else {
								reset = true;
							}
						step_done = true;
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

			while (!subRoutineDone) {
				// step 12,5
				PrescriptionCompDTO component = null;

				try {
					List<PrescriptionCompDTO> preCompList = proComps
							.getUnfulfilledComps(presID);
					if (preCompList.isEmpty()) {
						bound.sendError("Batch afsluttet"); 
						ProductBatchDTO product = products.getProductBatch(productBatchID);
						product.setStatus(StatusType.FINISHED);
						products.updateProductBatch(product);							
						reset = true;
					} else {
						component = preCompList.get(0);
						commodityID = component.getCommodityId();
					}
				} catch (DALException e) {
					bound.sendError("Der skete en fejl");
					reset = true;
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


				step_done = false;
				while (!reset && !step_done) {// step 13. Indtast på varebatch
												// ID
												// state
					ICommodityBatchDAO commBatch = new CommodityBatchData();
					commodityBatchID = bound.getRaavareBatchID(commodityID);
					if (commodityBatchID == -1){
						reset = true;
					}
					try {
						if (commBatch.getCommodityBatch(commodityBatchID)
								.getCommodityId() != commodityID) {
							bound.sendError("BatchID forkert.");
							if (bound.retry()) {
								continue;
							} else {
								reset = true;
							}
						}
					} catch (DALException e) {
						bound.sendError("Der skete en fejl");
						reset = true;
					}

				}

				step_done = false;
				while (!reset && !step_done) { // step 14

					double dataNetto = component.getNomNetto();
					double dataTolerance = component.getTolerance();
					netto = bound.getNettoWeight(dataNetto, dataTolerance);
					if (netto == -1.0){
						reset = true;
					} else if (netto >= dataNetto * (1 + dataTolerance / 100)){
						bound.sendError("Nettovaegt for stor");
						if (bound.retry())
							continue;
						else {
							reset = true;
						}
					}else if (netto <= dataNetto * (1 - dataTolerance / 100)) {
						bound.sendError("Nettovaegt for lav");
						if (bound.retry())
							continue;
						else {
							reset = true;
						}
					} else {
						try {
							ProductBatchCompDTO saveData = new ProductBatchCompDTO(productBatchID,commodityBatchID,userID,tare,netto);
							proComps.createProductBatchComp(saveData);
						} catch (DALException e) {
							bound.sendError("Kunne ikke gemme");
							if (bound.retry())
								continue;
							else {
								reset = true;
							}
							
						}
					}
				}

				step_done = false;
				while (!reset && !step_done) { // step 14,5
					if (bound.getQuit()) {
						reset = true;
						try {
							ProductBatchDTO product = products.getProductBatch(productBatchID);
							List<PrescriptionCompDTO> preCompList = proComps
									.getUnfulfilledComps(presID);
							if (preCompList.isEmpty()) {
								product.setStatus(StatusType.FINISHED);
							} else {
								product.setStatus(StatusType.PAUSED);
							}
							products.updateProductBatch(product);							
						} catch (DALException e) {
							bound.sendError("Uventet fejl!");
							reset = true;
						}
					} else 
						break;
				}
			}
		}
	}
}
