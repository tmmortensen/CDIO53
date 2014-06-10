package admin.data;

public class ProductBatchCompDTO {
	int pb_id, commoditybatch_id, user_id;
	double tara, netto;

	public ProductBatchCompDTO(int pb_id, int commoditybatch_id, int user_id,
			double tara, double netto) {
		this.pb_id = pb_id;
		this.commoditybatch_id = commoditybatch_id;
		this.user_id = user_id;
		this.tara = tara;
		this.netto = netto;

	}

	public int getPb_id() {
		return pb_id;
	}

	public int getCommoditybatch_id() {
		return commoditybatch_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public double getTara() {
		return tara;
	}

	public double getNetto() {
		return netto;
	}

}
