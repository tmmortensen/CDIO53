package admin.data;

public class CommodityDTO {
	int commodity_id;
	String commodity_name, supplier;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public CommodityDTO() {
		commodity_id = 0;
		commodity_name = "";
		supplier = "";
	}

	// this constructor should use the set Methods and thus do error checks
	public CommodityDTO(int commodity_id, String commodity_name, String supplier) {
		this.commodity_id = commodity_id;
		this.commodity_name = commodity_name;
		this.supplier = supplier;
	}

	public void setCommodity_id(int commodity_id) throws DALException {
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
