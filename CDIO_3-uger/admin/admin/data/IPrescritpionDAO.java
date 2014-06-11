package admin.data;

import java.util.List;

public interface IPrescritpionDAO {

	public PrescriptionDTO getPrescription(int prescriptionId)
			throws DALException;

	public List<PrescriptionDTO> getPrescriptionList()
			throws DALException;

	public void createPrescription(PrescriptionDTO prescription) 
			throws DALException;

	public void updatePrescription(PrescriptionDTO prescription)
			throws DALException;

	public void deletePrescription(int id)
			throws DALException;


}
