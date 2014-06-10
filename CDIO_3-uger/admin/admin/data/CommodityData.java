package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityData implements ICommodityDAO {

	@Override
	public synchronized CommodityDTO getCommodity(int commodity_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
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
			throw new DALException(e);
		}
	}

	@Override
	public synchronized List<CommodityDTO> getComList() throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
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
			throw new DALException(e);
		}
		return list;

	}

	@Override
	public synchronized void createCommodity(CommodityDTO commodity) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("INSERT INTO commodity VALUES ( commodity_id = "
				+ commodity.getComId() + ", commodity_name = '"
				+ commodity.getComName() + "', supplier = '"
				+ commodity.getSupplier() + "');");
		Connector.closeConnection();

	}

}
