package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchCompData implements IProductBatchCompDAO {

	@Override
	public ProductBatchCompDTO getProductBathComp(int pb_id,
			int commoditybatch_id) throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id = "
						+ pb_id + " AND commoditybatch_id = "
						+ commoditybatch_id + ";");
		try {
			if (!rs.first()) {
				throw new DALException(
						"The product batch component with the pb_id = " + pb_id
								+ " and the commoditybatch_id = "
								+ commoditybatch_id + " does not exist");
			}
			return new ProductBatchCompDTO(rs.getInt("pb_id"),
					rs.getInt("commoditybatch_id"), rs.getInt("user_id"),
					rs.getInt("tara"), rs.getInt("netto"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProductBatchCompDTO> getCertainProductBatchComps(int pb_id)
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBatchCompDTO> getAllProductBatchComps()
			throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProductBatchComp(ProductBatchCompDTO productBatchComp)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateProductBatchComp(ProductBatchCompDTO productBatchComp)
			throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteProductBatchComp(int pb_id, int commoditybatch_id)
			throws DALException {
		// TODO Auto-generated method stub

	}

}
