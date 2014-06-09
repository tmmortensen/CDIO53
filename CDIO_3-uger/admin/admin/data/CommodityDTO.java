package admin.data;

public class CommodityDTO {
	int commodity_id;
	String commodity_name, supplier;

	public CommodityDTO(int commodity_id, String commodity_name, String supplier) {
		this.commodity_id = commodity_id;
		this.commodity_name = commodity_name;
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
