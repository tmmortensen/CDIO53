package admin.data;

public class CommodityBatchDTO {

	private int commodityBatchId, commodityId;
	private double amount;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public CommodityBatchDTO() {

	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param commodityBatchId
	 * @param commodityId
	 * @param amount
	 * @throws DALException
	 */
	public CommodityBatchDTO(int commodityBatchId, int commodityId,
			double amount) throws DALException {
		setAmount(amount);
		setCommodityBatchId(commodityBatchId);
		setCommodityId(commodityId);
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

	public void setAmount(double amount) throws DALException {
		this.amount = amount;
	}
}
