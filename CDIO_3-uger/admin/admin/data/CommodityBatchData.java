package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityBatchData implements ICommodityBatchDAO {

	@Override
	public CommodityBatchDTO getCommodityBatch(int commoditybatch_id)
			throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM user WHERE commodity_id = "
						+ commoditybatch_id + ";");
		try {
			if (!rs.first()) {
				throw new DALException("the commodity with the id = "
						+ commoditybatch_id + " does not exist");
			}
			return new CommodityBatchDTO(rs.getInt("commoditybatch_id"),
					rs.getInt("commodity_id"), rs.getInt("amount"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<CommodityBatchDTO> getComBatchList() throws DALException {
		List<CommodityDTO> list = new ArrayList<CommodityDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM user;");
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
	public void createCommodityBatch(CommodityBatchDTO commodity)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
