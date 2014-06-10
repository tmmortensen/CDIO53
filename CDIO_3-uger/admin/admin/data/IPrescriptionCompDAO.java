package admin.data;

import java.util.List;

public interface IPrescriptionCompDAO {

	public void createPrescriptionComp(PrescriptionCompDTO prescription)
			throws DALException;

	public PrescriptionCompDTO getPrescriptionComp(int prescriptionId)
			throws DALException;

	public List<PrescriptionCompDTO> getAllPrescription(
			PrescriptionCompDTO prescription) throws DALException;
}
