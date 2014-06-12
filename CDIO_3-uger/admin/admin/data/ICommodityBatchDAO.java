package admin.data;

import java.util.List;

/**
 * Interface containing the methods used for a commodity batch
 * 
 * @author Group 53
 * 
 */
public interface ICommodityBatchDAO {

	/**
	 * Method to get the commodity batch with a specific ID
	 * 
	 * @param commoditybatch_id
	 *            ID for the wanted commodity batch
	 * @return commodity batches with the wanted ID
	 * @throws DALException
	 */
	public CommodityBatchDTO getCommodityBatch(int commoditybatch_id)
			throws DALException;

	/**
	 * Method to get a list of all commodity batches
	 * 
	 * @return a list of all commodity batches present in the database
	 * @throws DALException
	 */
	public List<CommodityBatchDTO> getComBatchList() throws DALException;

	public void createCommodityBatch(CommodityBatchDTO commoditybatch)
			throws DALException;

	public void updateCommodityBatch(CommodityBatchDTO commoditybatch)
			throws DALException;

	public void deleteCommodityBatch(int commoditybatch_id) throws DALException;
}
