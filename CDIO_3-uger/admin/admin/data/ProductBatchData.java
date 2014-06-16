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
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO productbatch VALUES ( "
				+ productBatch.getPbId() + ", "
				+ productBatch.getPrescriptionId() + ", "
				+ productBatch.getStatus() + productBatch.getCurrentDate()
				+ ");");
		Connector.closeConnection();

	}

	@Override
	public void deleteBatch(int pb_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch "
				+ "WHERE pb_id in(SELECT pb_id from productbatchcomponent);");
		try {
			if (!rs.first()) {
				Connector.doUpdate("DELETE FROM productbatch WHERE pb_id = "
						+ pb_id + ";");
				Connector.closeConnection();
			} else {
				throw new DALException(
						"you cannot delete that productbatch because it has been begun");
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden deleteBatch(int pb_id)"
							+ e.getMessage());
		}
	}

	@Override
	public List<ProductBatchDTO> getAllProductBatches() throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("date")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getAllProductBatches()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public List<ProductBatchDTO> getListByOperator(int operatorId) throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE pb_id IN "
						+ " (SELECT pb_id FROM productbatchcomponent WHERE user_id = " + operatorId + " );");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("date")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getCompletedProductBatch()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public List<ProductBatchDTO> getProductBatchByStatus(StatusType status) throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status  = " + StatusType.getValue(status) +" ;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("date")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getCompletedProductBatch()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public void updateProductBatch(ProductBatchDTO product) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE productbatch SET "
						+ " status = " + StatusType.getValue(product.getStatus())
						+ ",  date = " + product.getCurrentDate() + ";");
		Connector.closeConnection();

	}

	@Override
	public ProductBatchDTO getProductBatch(int id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatch WHERE pb_id IN"
										+ "(SELECT pb_id FROM productbatchcomponent WHERE user_id = " + id +");");
		try {
			if(!rs.first()){
				throw new DALException("Der er ikke nogen bruger med det id som arbejder på en productbatch");
			}
			return new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("date"));
		}catch(SQLException e){
			throw new DALException("Der skete en fejl i forbindelse med databasen " +e.getMessage());
		}
	}

}