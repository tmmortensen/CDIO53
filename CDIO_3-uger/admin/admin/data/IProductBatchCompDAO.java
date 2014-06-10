package admin.data;

import java.util.List;

public interface IProductBatchCompDAO {
	
	public ProductBatchCompDTO getProductBathComp(int pb_id, int commoditybatch_id) throws DALException;
	
	public List<ProductBatchCompDTO> getCertainProductBatchComps(int pb_id) throws DALException;
	
	public List<ProductBatchCompDTO> getAllProductBatchComps() throws DALException;

	public void createProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException; 
	
	public void updateProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException; 
	
	public void deleteProductBatchComp(int pb_id, int commoditybatch_id) throws DALException;
	
	
}
