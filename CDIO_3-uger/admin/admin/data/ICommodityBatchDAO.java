package admin.data;

import java.util.List;

public interface ICommodityBatchDAO {

	public CommodityBatchDTO getCommodityBatch(int commoditybatch_id)
			throws DALException;

	public List<CommodityBatchDTO> getComBatchList() throws DALException;

	public void createCommodityBatch(CommodityBatchDTO commodity)
			throws DALException;
}
