package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchData implements IProductBatchDAO {

	public synchronized void createProductBatch(ProductBatchDTO productBatch)
			throws DALException {
		Connector.doUpdate("INSERT INTO productbatch VALUES ( "
				+ productBatch.getPbId() 
				+ ", "+ productBatch.getPrescriptionId() 
				+ ", "+ productBatch.getStatus() + ");");

	}

	public synchronized List<ProductBatchDTO> getCompletedProductBatch() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 2;");

		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public synchronized List<ProductBatchDTO> getInitiatedProductBatch() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 1;");

		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public synchronized List<ProductBatchDTO> getUnInitializedProductBatch()
			throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 0;");

		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public synchronized void updateStatus(int pb_id) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBatch(int pb_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch "
											+ "WHERE pb_id in(SELECT pb_id from productbatchcomponent);");
		try {
			if(!rs.first()){
				Connector.doUpdate("DELETE FROM productbatch WHERE pb_id = " +pb_id + ";");
				Connector.closeConnection();
			}
			else {
				throw new DALException("you cannot delete that productbatch because it has been begun");
			}
		}catch(SQLException e){
			throw new DALException(e);
		}
	}

}
