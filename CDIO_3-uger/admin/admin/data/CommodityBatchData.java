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
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
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
			throw new DALException(
					"Der skete en fejl i CommodityBatchData i getCommodityBatch"
							+ e.getMessage());
		}
	}

	@Override
	public synchronized List<CommodityBatchDTO> getComBatchList()
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
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
			throw new DALException(
					"Der skete en fejl i CommodityBatchData i getComBatchList()"
							+ e.getMessage());
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
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO user VALUES ( "
				+ commodity.getCommodityBatchId() + ", "
				+ commodity.getCommodityId() + ", " + commodity.getAmount()
				+ ");");
		Connector.closeConnection();
	}

	@Override
	public void updateCommodityBatch(CommodityBatchDTO commoditybatch)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException(
					"Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE commoditybatch " + "set commodity_id = "
				+ commoditybatch.getCommodityBatchId() + ", commodity_name = "
				+ commoditybatch.commodityId + ", commodity_id = "
				+ commoditybatch.amount + ";");
		Connector.closeConnection();

	}

	@Override
	public void deleteCommodityBatch(int commoditybatch_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM commoditybatch where commoditybatch_id in ( SELECT commoditybatch_id from productbatchcomponent );");
		try {
			if (!rs.first()) {
				Connector
						.doUpdate("DELETE FROM commoditybatch WHERE commoditybatch_id = "
								+ commoditybatch_id + ";");
				Connector.closeConnection();
			} else {
				throw new DALException(
						"You cannot delete the commodity batch, because it has a productbatchcomponent attached to it's id");
			}

		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

}
