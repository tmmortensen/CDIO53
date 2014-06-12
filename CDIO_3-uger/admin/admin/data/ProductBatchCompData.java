package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchCompData implements IProductBatchCompDAO {

	@Override
	public synchronized ProductBatchCompDTO getProductBathComp(int pb_id,
			int commoditybatch_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id = "
						+ pb_id + " AND commoditybatch_id = "
						+ commoditybatch_id + ";");
		Connector.closeConnection();
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
	public synchronized List<ProductBatchCompDTO> getCertainProductBatchComps(int pb_id)
			throws DALException {
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatchcomponent WHERE pb_id = " + pb_id +";");
		Connector.closeConnection();
		try {
			while(rs.next()){
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"),
					rs.getInt("commoditybatch_id"), rs.getInt("user_id"),
					rs.getInt("tara"), rs.getInt("netto")));
			}
		}catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public synchronized List<ProductBatchCompDTO> getAllProductBatchComps()
			throws DALException {
		List<ProductBatchCompDTO> list = new ArrayList<ProductBatchCompDTO>();
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector.doQuery("SELECT * FROM productbatchcomponent;");
		Connector.closeConnection();
		try {
			while(rs.next()){
				list.add(new ProductBatchCompDTO(rs.getInt("pb_id"),
					rs.getInt("commoditybatch_id"), rs.getInt("user_id"),
					rs.getInt("tara"), rs.getInt("netto")));
			}
		}catch(SQLException e){
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public synchronized void createProductBatchComp(ProductBatchCompDTO productBatchComp)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("INSERT INTO productbatchcomponent VALUES ( "
				+ productBatchComp.getPb_id() 
				+ "," + productBatchComp.getCommoditybatch_id()
				+ ","+ productBatchComp.getUser_id()
				+ ", 0 "
				+ ", 0);");
		Connector.closeConnection();

	}

	@Override
	public synchronized void updateProductBatchComp(ProductBatchCompDTO productBatchComp)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("UPDATE productbatchcomponent "
				+ "set user_id = " + productBatchComp.getUser_id() 
				+ ", tara = " + productBatchComp.getTara() 
				+ ", netto = " + productBatchComp.getNetto()+";");
		Connector.closeConnection();

	}

	@Override
	public synchronized void deleteProductBatchComp(int pb_id, int commoditybatch_id)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("DELETE *  FROM productbatchcomponent WHERE pb_id = " + pb_id 
				+ " AND commoditybatch_id = " + commoditybatch_id +";");
		Connector.closeConnection();
	}

}
