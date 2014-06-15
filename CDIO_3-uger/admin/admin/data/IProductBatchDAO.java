package admin.data;

import java.util.List;

public interface IProductBatchDAO {

	public List<ProductBatchDTO> getAllProductBatches() throws DALException;

	/**
	 * Get all product batches assigned to a certain operator
	 * 
	 * @param operatorId
	 *            the id of the operator assigned to the product batches
	 * @return list of product batches assigned to the specified operator
	 */
	public List<ProductBatchDTO> getListByOperator(int operatorId)
			throws DALException;

	/**
	 * Get all product batches with the specified status
	 * 
	 * @param status
	 *            the StatusType of the desired product batches
	 * @return a list of all product batches that have the specified status
	 */
	public List<ProductBatchDTO> getProductBatchByStatus(StatusType status)
			throws DALException;

	public ProductBatchDTO getProductBatch(int id) throws DALException;

	public void createProductBatch(ProductBatchDTO productBatch)
			throws DALException;

	public void updateProductBatch(ProductBatchDTO product) throws DALException;

	public void deleteBatch(int pb_id) throws DALException;
}