package admin.data;

import java.util.List;

public interface IProductBatchDAO {
	
	public void createProductBatch(ProductBatchDTO productBatch) throws DALException;
	
	public List<ProductBatchDTO> getCompletedProductBatch() throws DALException;
	
	public List<ProductBatchDTO> getInitiatedProductBatch() throws DALException;
	
	public List<ProductBatchDTO> getUnInitializedProductBatch() throws DALException;
	
	public void updateStatus(int pb_id) throws DALException;
}