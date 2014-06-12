package admin.data;

import java.util.List;

public interface ICommodityDAO {

	public CommodityDTO getCommodity(int commodity_id) throws DALException;

	public List<CommodityDTO> getComList() throws DALException;

	public void createCommodity(CommodityDTO commodity) throws DALException;
	
	public void updateCommodity(CommodityDTO commodity) throws DALException;
	
	public void deleteCommodity(int commodity_id) throws DALException;

}
