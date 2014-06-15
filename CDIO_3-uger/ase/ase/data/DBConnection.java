package ase.data;

import admin.data.CommodityBatchDTO;
import admin.data.CommodityBatchData;
import admin.data.CommodityDTO;
import admin.data.CommodityData;
import admin.data.DALException;
import admin.data.PrescriptionData;
import admin.data.ProductBatchDTO;
import admin.data.ProductBatchData;
import admin.data.UserDTO;
import admin.data.UserData;

/**
 * Class used to change the program state
 * 
 * @author Gruppe 53
 * 
 */
public class DBConnection implements IDBConnection {

	UserData userData;
	ProductBatchData PBData;
	CommodityData commodityData;
	CommodityDTO commodity;
	CommodityBatchData commodityBatchData;
	CommodityBatchDTO commodityBatch;
	PrescriptionData prescription;
	UserDTO user;
	ProductBatchDTO product;

	int userId;
	int commodityId;

	// test
	@Override
	public String checkName(int id) throws DALException {
		String userName = userData.getUser(id).getUsername();
		return userName;
	}

	@Override
	public int checkProduktBatchID(int produktBatchID) throws DALException {
		int produktBatch = 1;

		return produktBatch;

	}

	@Override
	public void getRaavareID() throws DALException {
		commodityData.getCommodity(commodityId);

	}

	@Override
	public int checkRaavareBatchID(int id) throws DALException {
		int batchID = commodityBatchData.getCommodityBatch(id)
				.getCommodityBatchId();
		return batchID;
	}

	@Override
	public void saveData() throws DALException {
		// TODO Auto-generated method stub
		userData.updateUser(user);
		PBData.updateProductBatch(product);
		commodityData.updateCommodity(commodity);
		commodityBatchData.updateCommodityBatch(commodityBatch);
	}

	@Override
	public void produktStatus(String afslutte) {
		// TODO Auto-generated method stub

	}

	public void getNetto() {

	}

	public void getTolerance() {

	}

}
