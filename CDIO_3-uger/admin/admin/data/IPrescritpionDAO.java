package admin.data;

import java.util.List;

public interface IPrescritpionDAO {

	public void PrescriptionDTO() throws DALException;

	public void createPrescription(PrescriptionDTO prescription)
			throws DALException;

	public PrescriptionDTO getPrescription(int prescriptionId)
			throws DALException;

	public List<PrescriptionDTO> getPrescriptionList()
			throws DALException;

}
