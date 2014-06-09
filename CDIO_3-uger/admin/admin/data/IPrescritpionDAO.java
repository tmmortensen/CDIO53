package admin.data;

import java.util.List;

public interface IPrescritpionDAO {

	public void PrescriptionDTO() throws DALException;

	public void createPrescription(PrescriptionDTO prescription)
			throws DALException;

	public void getPrescription(PrescriptionDTO prescription)
			throws DALException;

	public List<PrescriptionDTO> getAllPrescription(PrescriptionDTO prescription)
			throws DALException;

}
