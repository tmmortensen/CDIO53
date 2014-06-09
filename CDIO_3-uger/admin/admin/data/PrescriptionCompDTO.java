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
