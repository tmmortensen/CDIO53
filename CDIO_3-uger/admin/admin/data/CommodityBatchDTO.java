package admin.data;

public class CommodityBatchDTO {

	int commodityBatchId, commodityId, amount;

	public CommodityBatchDTO(int commodityBatchId, int commodityId, int amount) {
		this.amount = amount;
		this.commodityBatchId = commodityBatchId;
		this.commodityId = commodityId;
	}

	public int getCommodityBatchId() {
		return commodityBatchId;
	}

	public void setCommodityBatchId(int commodityBatchId) {
		this.commodityBatchId = commodityBatchId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
