package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityData implements ICommodityDAO {

	@Override
	public synchronized CommodityDTO getCommodity(int commodity_id) throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM commodity WHERE commodity_id = "
						+ commodity_id + ";");
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
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM commodity;");
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
		Connector.doUpdate("INSERT INTO commodity VALUES ( commodity_id = "
				+ commodity.getComId() + ", commodity_name = '"
				+ commodity.getComName() + "', supplier = '"
				+ commodity.getSupplier() + "');");

	}

}
