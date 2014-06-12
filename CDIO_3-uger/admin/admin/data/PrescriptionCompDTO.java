package admin.data;

public class PrescriptionCompDTO {

	int prescriptionId, commodityId;
	double nomNetto, tolerance;

	public PrescriptionCompDTO(){
		prescriptionId = 0;
		commodityId = 0;
		nomNetto = 0;
		tolerance = 0;
	}
	
	public PrescriptionCompDTO(int prescriptionId, int commodityId, double nomNetto,
			double tolerance) throws DALException {
		this.prescriptionId = prescriptionId;
		this.commodityId = commodityId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public void setPrescriptionId (int prescriptionId) 
			throws DALException{
		this.prescriptionId = prescriptionId;
	}

	public void setCommodityId(int commodityId) 
			throws DALException{
		this.commodityId = commodityId;
	}

	public void setNomNetto(double nomNetto) 
			throws DALException{
		this.nomNetto = nomNetto;
	}

	public void setTolerance(double tolerance) 
			throws DALException{
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
