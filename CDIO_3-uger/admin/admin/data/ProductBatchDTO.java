package admin.data;

import java.sql.Date;
import java.util.Calendar;

public class ProductBatchDTO {

	int pb_id, prescription_id,user_id;
	StatusType status;
	// create a sql date object so we can use it in our INSERT statement
	Calendar calendar = Calendar.getInstance();
	Date currentDate = new Date(calendar.getTime().getTime());

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
	public ProductBatchDTO(int pb_id, int prescription_id, int status, Date date, int user_id)
			throws DALException {
		setPb_id(pb_id);
		setPrescription_id(prescription_id);
		setStatus(status);
		setCurrentDate(date);
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
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
	
	public int getUserId(){
		return user_id; 
	}
	
	public void setUserId(int user_id){
		this.user_id=user_id; 
	}
}
