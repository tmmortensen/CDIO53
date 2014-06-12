package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchData implements IProductBatchDAO {

	public synchronized void createProductBatch(ProductBatchDTO productBatch)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO productbatch VALUES ( "
				+ productBatch.getPbId() 
				+ ", "+ productBatch.getPrescriptionId() 
				+ ", "+ productBatch.getStatus() + ");");
		Connector.closeConnection();
		
	}

	public synchronized List<ProductBatchDTO> getCompletedProductBatch() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 2;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i forbindelse med databasen");
		}
		return list;
	}

	public synchronized List<ProductBatchDTO> getInitiatedProductBatch() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 1;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i forbindelse med databasen");
		}
		return list;
	}

	public synchronized List<ProductBatchDTO> getUnInitializedProductBatch()
			throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status = 0;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i forbindelse med databasen");
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
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
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
			throw new DALException("Der skete en fejl i forbindelse med databasen");
		}
	}

}
