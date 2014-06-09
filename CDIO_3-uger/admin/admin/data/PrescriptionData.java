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
	public void createPrescription(admin.data.PrescriptionDTO prescription)
			throws DALException {
		ResultSet rs = Connector
				.doQuery("INSERT INTO TABLE prescription VALUES( )");
		try {
			if (!rs.first())
				throw new DALException("does not exist");
			return;

		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	@Override
	public void getPrescription(admin.data.PrescriptionDTO prescription)
			throws DALException {

	}

	@Override
	public List<PrescriptionDTO> getAllPrescription(
			admin.data.PrescriptionDTO prescription) throws DALException {
		List<PrescriptionDTO> list = new ArrayList<PrescriptionDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM prescription");
		try {
			while (rs.next()) {
				list.add(new PrescriptionDTO(rs.getInt("opr_id"), rs
						.getString("opr_navn")));

			}
		} catch (SQLException e) {
			throw new DALException(e);
		}

		return list;

	}

}
