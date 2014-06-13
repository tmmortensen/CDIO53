package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityData implements ICommodityDAO {

	@Override
	public synchronized CommodityDTO getCommodity(int commodity_id)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM commodity WHERE commodity_id = "
						+ commodity_id + ";");
		Connector.closeConnection();
		try {
			if (!rs.first()) {
				throw new DALException("the commodity with the id = "
						+ commodity_id + " does not exist");
			}
			return new CommodityDTO(rs.getInt("commodity_id"),
					rs.getString("commodity_name"), rs.getString("supplier"));
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i Commodity i metoden getCommodity()"
							+ e.getMessage());
		}
	}

	@Override
	public synchronized List<CommodityDTO> getComList() throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM commodity;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new CommodityDTO(rs.getInt("commodity_id"), rs
						.getString("commodity_name"), rs.getString("supplier")));
			}
		} catch (SQLException e) {
			throw new DALException(
					"Der skete en fejl i Commodity i metoden getComList()"
							+ e.getMessage());
		}
		return list;
	}

	@Override
	public synchronized void createCommodity(CommodityDTO commodity)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO commodity VALUES ( "
				+ commodity.getComId() + ", '" + commodity.getComName() + "','"
				+ commodity.getSupplier() + "');");
		Connector.closeConnection();
	}

	@Override
	public void updateCommodity(CommodityDTO commodity) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE commodity " + "SET"
				+ " commodity_name = '" + commodity.getComName() 
				+ "', supplier = '" + commodity.getSupplier() 
				+ "' WHERE commodity_id = " + commodity.getComId() 
				+ ";");
		Connector.closeConnection();
	}

	@Override
	public void deleteCommodity(int commodity_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM commodity WHERE commodity_id IN "
						+ "(SELECT commodity_id from prescriptioncomponent);");
		try {
			if (!rs.next()) {
				Connector
						.doUpdate("DELETE FROM commodity WHERE commodity_id = "
								+ commodity_id + ";");
				Connector.closeConnection();
			}
			else {
				throw new DALException("id'et er allerede blevet brugt i nogle afvejninger");
			}
		} catch (SQLException e) {
			throw new DALException(
					"Noget gik galt i forbindelse med databasen.");
		}

	}
}
