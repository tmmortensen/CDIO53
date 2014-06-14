package admin.data;

import java.util.List;

public interface IProductBatchDAO {

	public void createProductBatch(ProductBatchDTO productBatch)
			throws DALException;

	public List<ProductBatchDTO> getAllProductBatches() throws DALException;

	/**
	 * Get all product batches assigned to a certain operator
	 * 
	 * @param operatorId
	 *            the id of the operator assigned to the product batches
	 * @return list of product batches assigned to the specified operator
	 */
	public List<ProductBatchDTO> getListByOperator(int operatorId);

	public List<ProductBatchDTO> getCompletedProductBatch() throws DALException;

	public List<ProductBatchDTO> getInitiatedProductBatch() throws DALException;

	public List<ProductBatchDTO> getUnInitializedProductBatch()
			throws DALException;

	public void updateStatus(int pb_id) throws DALException;

	public void deleteBatch(int pb_id) throws DALException;
}