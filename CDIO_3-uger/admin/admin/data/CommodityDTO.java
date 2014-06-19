package admin.data;

public class CommodityDTO {
	private int commodity_id;
	private String commodity_name, supplier;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public CommodityDTO() {
		commodity_id = 0;
		commodity_name = "";
		supplier = "";
	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param commodity_id
	 * @param commodity_name
	 * @param supplier
	 * @throws DALException
	 */
	public CommodityDTO(int commodity_id, String commodity_name, String supplier)
			throws DALException {
		setCommodity_id(commodity_id);
		setCommodity_name(commodity_name);
		setSupplier(supplier);
	}

	public void setCommodity_id(int commodity_id) throws DALException {
		if(commodity_id<=0){
			throw new DALException("Råvare id'et skal være større end 0");
		}
		this.commodity_id = commodity_id;
	}

	public void setCommodity_name(String commodity_name) throws DALException {
		this.commodity_name = commodity_name;
	}

	public void setSupplier(String supplier) throws DALException {
		this.supplier = supplier;
	}

	public int getComId() {
		return commodity_id;
	}

	public String getComName() {
		return commodity_name;
	}

	public String getSupplier() {
		return supplier;
	}
}
