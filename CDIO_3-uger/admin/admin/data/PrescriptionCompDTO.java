package admin.data;

public class PrescriptionCompDTO {

	int prescriptionId, commodityId;
	double nomNetto, tolerance;

	PrescriptionCompDTO(int prescriptionId, int commodityId, double nomNetto,
			double tolerance) {
		this.prescriptionId = prescriptionId;
		this.commodityId = commodityId;
		this.nomNetto = nomNetto;
		this.nomNetto = nomNetto;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public void setNomNetto(double nomNetto) {
		this.nomNetto = nomNetto;
	}

	public void setTolerance(double tolerance) {
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
