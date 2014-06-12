package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityBatchData implements ICommodityBatchDAO {

	@Override
	public synchronized CommodityBatchDTO getCommodityBatch(
			int commoditybatch_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM commoditybatch WHERE "
				+ "commoditybatch_id = " + commoditybatch_id + ";");
		Connector.closeConnection();
		try {
			if (!rs.first()) {
				throw new DALException("the commoditybatch with the id = "
						+ commoditybatch_id + " does not exist");
			}
			return new CommodityBatchDTO(rs.getInt("commoditybatch_id"),
					rs.getInt("commodity_id"), rs.getInt("amount"));
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i CommodityBatchData i getCommodityBatch" + e.getMessage());
		}
	}

	@Override
	public synchronized List<CommodityBatchDTO> getComBatchList()
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		List<CommodityBatchDTO> list = new ArrayList<CommodityBatchDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM commoditybatch;");
		try {
			while (rs.next()) {
				list.add(new CommodityBatchDTO(rs
						.getInt("productbatchbatch_id"), rs
						.getInt("commoditybatch_"), rs.getInt("supplier")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i CommodityBatchData i getComBatchList()" + e.getMessage());
		}
		Connector.closeConnection();
		return list;
	}

	@Override
	public synchronized void createCommodityBatch(CommodityBatchDTO commodity)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO user VALUES ( "
				+ commodity.getCommodityBatchId() 
				+ ", " + commodity.getCommodityId() 
				+ ", " + commodity.getAmount()
				+ ");");
		Connector.closeConnection();
	}

}
