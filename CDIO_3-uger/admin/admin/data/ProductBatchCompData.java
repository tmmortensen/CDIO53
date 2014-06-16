package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchCompData implements IProductBatchCompDAO {
	
	@Override
	public synchronized ProductBatchCompDTO getProductBatchComp(int pb_id,
			int commoditybatch_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id = "
						+ pb_id + " AND commoditybatch_id = "
						+ commoditybatch_id + ";");
		Connector.closeConnection();
		try {
			if (!rs.first()) {
				throw new DALException(
						"Produkt batchen med det givne produkt batch id = "
								+ pb_id
								+ " og det tilsvarende r�vare batch id = "
								+ commoditybatch_id
								+ " eksisterer ikke i databasen");
			}
			return new ProductBatchCompDTO(rs.getInt("pb_id"),
					rs.getInt("commoditybatch_id"), rs.getInt("user_id"),
					rs.getInt("tara"), rs.getInt("netto"));
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatchCompData i metoden getProductBatch(int pb_id, int commoditybatch_id)"
							+ e.getMessage());
		}
	}

	@Override
	public synchronized List<ProductBatchCompDTO> getCertainProductBatchComps(
			int pb_id) throws DALException {
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id = "
						+ pb_id + ";");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs
						.getInt("commoditybatch_id"), rs.getInt("user_id"), rs
						.getInt("tara"), rs.getInt("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatchCompData i metoden getCertainProductBatchComps(int pb_id)"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized List<ProductBatchCompDTO> getAllProductBatchComps()
			throws DALException {
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatchcomponent;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"), rs
						.getInt("commoditybatch_id"), rs.getInt("user_id"), rs
						.getInt("tara"), rs.getInt("netto")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i ProductBatchCompData i metoden getAllProductBatchComps()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized void createProductBatchComp(
			ProductBatchCompDTO productBatchComp) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO productbatchcomponent VALUES ( "
				+ productBatchComp.getPb_id() + ","
				+ productBatchComp.getCommoditybatch_id() + ","
				+ productBatchComp.getUser_id() + ","
				+ productBatchComp.getTara() + ","
				+ productBatchComp.getNetto() + ");");
		Connector.closeConnection();

	}

	@Override
	public synchronized void updateProductBatchComp(
			ProductBatchCompDTO productBatchComp) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE productbatchcomponent " + "set user_id = "
				+ productBatchComp.getUser_id() + ", tara = "
				+ productBatchComp.getTara() + ", netto = "
				+ productBatchComp.getNetto() + ";");
		Connector.closeConnection();

	}

	@Override
	public synchronized void deleteProductBatchComp(int pb_id,
			int commoditybatch_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector
				.doUpdate("DELETE FROM productbatchcomponent WHERE pb_id = "
						+ pb_id + " AND commoditybatch_id = "
						+ commoditybatch_id + ";");
		Connector.closeConnection();
	}

	@Override
	public synchronized String getName(int pb_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		try {
			ResultSet rs = Connector
					.doQuery("SELECT prescription_name FROM prescription WHERE prescription_id IN "
							+ "(SELECT prescription_id FROM productbatch WHERE pb_id ="
							+ pb_id + ");");
			return rs.getString("prescription_name");
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i forbindele med databasen"
							+ e.getMessage());
		}

	}

	@Override
	public void deleteByBatchID(int productBatchId) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("DELETE FROM productbatchcomponent WHERE pb_id = " + productBatchId +";");
		Connector.closeConnection();
	}

	@Override
	public synchronized List<PrescriptionCompDTO> getUnFulfilledComps(int pb_id)
			throws DALException {
		List<PrescriptionCompDTO> list = new ArrayList<PrescriptionCompDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM prescriptioncomponent WHERE prescription_id IN "
				+ "(SELECT prescription_id FROM productbatch WHERE pb_id = " + pb_id +")"
				+ " AND commodity_id IN (SELECT commodity_id FROM commoditybatch WHERE commoditybatch_id NOT IN "
				+ "(SELECT commoditybatch_id FROM productbatchcomponent WHERE pb_id = " + pb_id +"));");
		Connector.closeConnection();
		try {
			while(rs.next()){
				list.add(new PrescriptionCompDTO(rs.getInt("prescription_id"),rs.getInt("commodity_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance")));
			}
		}catch(SQLException e){
			throw new DALException("Der skete en fejl i unFulfilledComps " +e.getMessage());
		}
		return list; 
	}

}
