package admin.data;

public class ProductBatchDTO {

	int pb_id, prescription_id; 
	StatusType status;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public ProductBatchDTO() {

	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param pb_id
	 * @param prescription_id
	 * @param status
	 * @throws DALException
	 */
	public ProductBatchDTO(int pb_id, int prescription_id, int status)
			throws DALException {
		setPb_id(pb_id);
		setPrescription_id(prescription_id);
		setStatus(status);
	}

	public void setPb_id(int pb_id) throws DALException {
		this.pb_id = pb_id;
	}

	public void setPrescription_id(int prescription_id) throws DALException {
		this.prescription_id = prescription_id;
	}

	public void setStatus(StatusType status) throws DALException {
		this.status = status;
	}

	public void setStatus(int status) throws DALException {
		this.status = StatusType.fromInt(status);
	}

	public int getPbId() {
		return pb_id;
	}

	public int getPrescriptionId() {
		return prescription_id;
	}

	public StatusType getStatus() {
		return status;
	}
}
