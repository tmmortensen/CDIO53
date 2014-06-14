package admin.data;

public class CommodityBatchDTO {

	int commodityBatchId, commodityId;
	double amount;

	public CommodityBatchDTO(int commodityBatchId, int commodityId, double amount) {
		this.amount = amount;
		this.commodityBatchId = commodityBatchId;
		this.commodityId = commodityId;
	}

	public int getCommodityBatchId() {
		return commodityBatchId;
	}

	public void setCommodityBatchId(int commodityBatchId) throws DALException {
		this.commodityBatchId = commodityBatchId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) throws DALException {
		this.commodityId = commodityId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(int amount) throws DALException {
		this.amount = amount;
	}
}
