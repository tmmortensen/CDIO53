package admin.data;

public class ProductBatchDTO {

	int pb_id, prescription_id, status;

	public ProductBatchDTO(int pb_id, int prescription_id, int status) {
		this.pb_id = pb_id;
		this.prescription_id = prescription_id;
		this.status = status;
	}

	public void setPb_id(int pb_id) throws DALException {
		this.pb_id = pb_id;
	}

	public void setPrescription_id(int prescription_id) throws DALException {
		this.prescription_id = prescription_id;
	}

	public void setStatus(int status) throws DALException {
		this.status = status;
	}

	public int getPbId() {
		return pb_id;
	}

	public int getPrescriptionId() {
		return prescription_id;
	}

	public int getStatus() {
		return status;
	}

}
