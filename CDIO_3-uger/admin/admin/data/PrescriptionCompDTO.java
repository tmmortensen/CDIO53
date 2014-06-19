package admin.data;

public class PrescriptionCompDTO {

	private int prescriptionId, commodityId;
	private double nomNetto, tolerance;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public PrescriptionCompDTO() {
		prescriptionId = 0;
		commodityId = 0;
		nomNetto = 0;
		tolerance = 0;
	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param prescriptionId
	 * @param commodityId
	 * @param nomNetto
	 * @param tolerance
	 * @throws DALException
	 */
	public PrescriptionCompDTO(int prescriptionId, int commodityId,
			double nomNetto, double tolerance) throws DALException {
		setPrescriptionId(prescriptionId);
		setCommodityId(commodityId);
		setNomNetto(nomNetto);
		setTolerance(tolerance);
	}

	public void setPrescriptionId(int prescriptionId) throws DALException {
		if(prescriptionId<=0){
			throw new DALException("Recept id'et skal være større end 0");
		}
		this.prescriptionId = prescriptionId;
	}

	public void setCommodityId(int commodityId) throws DALException {
		if(commodityId<=0){
			throw new DALException("Råvare id'et skal være større end 0");
		}
		this.commodityId = commodityId;
	}

	public void setNomNetto(double nomNetto) throws DALException {
		if(nomNetto<0.0){
			throw new DALException("nomNetto skal være en positiv værdi");
		}
		this.nomNetto = nomNetto;
	}

	public void setTolerance(double tolerance) throws DALException {
		if(tolerance<0.0){
			throw new DALException("Man kan ikke have en negativ tolerance");
		}
		this.tolerance = tolerance;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public double getNomNetto() {
		return nomNetto;
	}

	public double getTolerance() {
		return tolerance;
	}
}
