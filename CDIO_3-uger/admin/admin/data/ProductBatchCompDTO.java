package admin.data;

public class ProductBatchCompDTO {
	int pb_id, commoditybatch_id, user_id;
	double tara, netto;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public ProductBatchCompDTO() {

	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param pb_id
	 * @param commoditybatch_id
	 * @param user_id
	 * @param tara
	 * @param netto
	 * @throws DALException
	 */
	public ProductBatchCompDTO(int pb_id, int commoditybatch_id, int user_id,
			double tara, double netto) throws DALException {
		setPb_id(pb_id);
		setCommoditybatch_id(commoditybatch_id);
		setUser_id(user_id);
		setTara(tara);
		setNetto(netto);
	}

	public void setPb_id(int pb_id) throws DALException {
		this.pb_id = pb_id;
	}

	public void setCommoditybatch_id(int commoditybatch_id) throws DALException {
		this.commoditybatch_id = commoditybatch_id;
	}

	public void setUser_id(int user_id) throws DALException {
		this.user_id = user_id;
	}

	public void setTara(double tara) throws DALException {
		this.tara = tara;
	}

	public void setNetto(double netto) throws DALException {
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
