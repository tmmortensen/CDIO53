package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionCompData implements IPrescriptionCompDAO {

	@Override
	public void createPrescriptionComp(PrescriptionCompDTO prescription)
			throws DALException {
		Connector.doUpdate("INSERT INTO prescriptioncomponent VALUES ( "
				+ " prescription_id = " + prescription.getPrescriptionId()
				+ " commidity_id = " + prescription.getCommodityId()
				+ " nom_netto = " + prescription.getNomNetto()
				+ " tolerance = " + prescription.getTolerance() + ");");
	}

	@Override
	public PrescriptionCompDTO getPrescriptionComp(int prescriptionId)
			throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescriptioncomponent WHERE prescription_id = "
						+ prescriptionId + ";");
		try {
			if (!rs.first()) {
				throw new DALException("the commodity with the id = "
						+ prescriptionId + " does not exist");
			}
			return new PrescriptionCompDTO(rs.getInt("prescription_id"),
					rs.getInt("commodity_id"), rs.getDouble("nom_netto"),
					rs.getDouble("tolerance"));
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<PrescriptionCompDTO> getAllPrescription(
			PrescriptionCompDTO prescription) throws DALException {
		List<PrescriptionCompDTO> list = new ArrayList<PrescriptionCompDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescriptioncomponent;");
		try {
			while (rs.next()) {
				list.add(new PrescriptionCompDTO(rs.getInt("prescription_id"),
						rs.getInt("commodity_id"), rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

}
