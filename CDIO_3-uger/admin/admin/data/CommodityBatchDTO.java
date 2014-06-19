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
		if(commodityBatchId<=0){
			throw new DALException("Råvare batch id'et skal være større end 0");
		}
		this.commodityBatchId = commodityBatchId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) throws DALException {
		if(commodityId<=0){
			throw new DALException("Råvare id'et skal være større end 0");
		}
		this.commodityId = commodityId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) throws DALException {
		if(amount<0){
			throw new DALException("Man kan ikke trække en negativ mængde fra i lageret");
		}
		this.amount = amount;
	}
}
