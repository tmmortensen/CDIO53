package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionData implements IPrescritpionDAO {

	@Override
	public void PrescriptionDTO() throws DALException {

	}

	@Override
	public void createPrescription(PrescriptionDTO prescription)
			throws DALException {
		Connector.doUpdate("INSERT INTO prescription VALUES ( "
				+ " prescription_id = " + prescription.getPrescriptionId()
				+ " prescription_name = " + prescription.getPrescriptionName()
				+ ");");
	}

	@Override
	public PrescriptionDTO getPrescription(int prescriptionId)
			throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescription WHERE prescription_id = "
						+ prescriptionId + ";");
		try {
			if (!rs.first()) {
				throw new DALException("the commodity with the id = "
						+ prescriptionId + " does not exist");
			}
			return new PrescriptionDTO(rs.getInt("prescription_id"),
					rs.getString("prescription_name"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<PrescriptionDTO> getAllPrescription(PrescriptionDTO prescription)
			throws DALException {
		List<PrescriptionDTO> list = new ArrayList<PrescriptionDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM prescription;");
		try {
			while (rs.next()) {
				list.add(new PrescriptionDTO(rs.getInt("prescription_id"), rs
						.getString("prescription_name")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

}
