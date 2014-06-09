package admin.data;

import java.util.List;

public class ProductBatchData implements IProductBatchDAO {

	@Override
	public void createProductBatch(ProductBatchDTO productBatch)
			throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductBatchDTO> getCompletedProductBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBatchDTO> getInitiatedProductBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBatchDTO> getUnInitializedProductBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(int pb_id) {
		// TODO Auto-generated method stub
		
	}

}
