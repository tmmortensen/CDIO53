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
		try {
			Connector.doUpdate("INSERT INTO productbatch VALUES ( "
					+ productBatch.getPbId() + ", "
					+ productBatch.getPrescriptionId() + ", "
					+ StatusType.getValue(productBatch.getStatus()) + ",'"
					+ productBatch.getCreationDate() + "', "
					+ productBatch.getUserId() + ");");
		} catch (DALException e) {
			throw new DALException(
					"recept id'et findes ikke så det givne product batch kan ikke oprettes");
		}
		Connector.closeConnection();

	}

	@Override
	public synchronized void deleteBatch(int pb_id) throws DALException {
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
						"Du kan ikke slette denne produkt batch da den allerede er påbegyndt");
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden deleteBatch(int pb_id)"
							+ e.getMessage());
		}
	}

	@Override
	public synchronized List<ProductBatchDTO> getAllProductBatches()
			throws DALException {
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
						.getDate("current_date"), rs.getInt("user_id")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getAllProductBatches()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized List<ProductBatchDTO> getListByOperator(int operatorId)
			throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE pb_id IN "
						+ " (SELECT pb_id FROM productbatchcomponent WHERE user_id = "
						+ operatorId + " );");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("current_date"), rs.getInt("user_id")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getCompletedProductBatch()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized List<ProductBatchDTO> getProductBatchByStatus(
			StatusType status) throws DALException {
		List<ProductBatchDTO> list = new ArrayList<ProductBatchDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE status  = "
						+ StatusType.getValue(status) + " ;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchDTO(rs.getInt("pb_id"), rs
						.getInt("prescription_id"), rs.getInt("status"), rs
						.getDate("current_date"), rs.getInt("user_id")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatch i metoden getCompletedProductBatch()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized void updateProductBatch(ProductBatchDTO product)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE productbatch SET " + " status = "
				+ StatusType.getValue(product.getStatus())
				+ ",  current_date = " + product.getCreationDate()
				+ ", user_id = " + product.getUserId() + ";");
		Connector.closeConnection();

	}

	@Override
	public synchronized ProductBatchDTO getProductBatch(int id)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatch WHERE pb_id IN"
						+ "(SELECT pb_id FROM productbatchcomponent WHERE user_id = "
						+ id + ");");
		try {
			if (!rs.first()) {
				throw new DALException(
						"Der er ikke nogen bruger med det id som arbejder på en productbatch");
			}
			return new ProductBatchDTO(rs.getInt("pb_id"),
					rs.getInt("prescription_id"), rs.getInt("status"),
					rs.getDate("current_date"), rs.getInt("user_id"));
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i forbindelse med databasen "
							+ e.getMessage());
		}
	}

}