package admin.data;

import java.util.List;

public interface IProductBatchDAO {
	
	public void createProductBatch(ProductBatchDTO productBatch) throws DALException;
	
	public List<ProductBatchDTO> getCompletedProductBatch();
	
	public List<ProductBatchDTO> getInitiatedProductBatch();
	
	public List<ProductBatchDTO> getUnInitializedProductBatch();
	
	public void updateStatus(int pb_id);
}
