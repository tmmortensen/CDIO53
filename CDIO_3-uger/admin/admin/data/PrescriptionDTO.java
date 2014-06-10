package admin.data;

public class PrescriptionDTO {

	int prescriptionId;
	String prescriptionName;

	public PrescriptionDTO(int prescriptionId, String prescriptionName) {
		this.prescriptionId = prescriptionId;
		this.prescriptionName = prescriptionName;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

}
