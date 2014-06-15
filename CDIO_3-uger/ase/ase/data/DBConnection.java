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
	public String checkName() throws DALException {
		String userName = userData.getUser(userId).getUsername();
		return userName;
	}

	@Override
	public void checkProduktBatchID(int produktBatchID) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getRaavareID() throws DALException {
		commodityData.getCommodity(commodityId);

	}

	@Override
	public void checkRaavareBatchID(int id) throws DALException {
		commodityBatchData.getCommodityBatch(id);

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

}
