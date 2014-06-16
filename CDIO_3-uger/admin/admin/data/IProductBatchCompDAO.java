package admin.data;

import java.util.List;

public interface IProductBatchCompDAO {
	
	public ProductBatchCompDTO getProductBatchComp(int pb_id, int commoditybatch_id) throws DALException;
	
	public List<ProductBatchCompDTO> getCertainProductBatchComps(int pb_id) throws DALException;
	
	public List<ProductBatchCompDTO> getAllProductBatchComps() throws DALException;

	public void createProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException; 
	
	public void updateProductBatchComp(ProductBatchCompDTO productBatchComp) throws DALException; 
	
	public void deleteByBatchID(int productBatchId) throws DALException;
	
	public void deleteProductBatchComp(int pb_id, int commoditybatch_id) throws DALException;
	
	public String getName(int pb_id) throws DALException;
	
	public List<PrescriptionCompDTO> getUnfulfilledComps(int pb_id) throws DALException;
	
}
