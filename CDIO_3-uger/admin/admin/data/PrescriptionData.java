package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionData implements IPrescritpionDAO {

	@Override
	public synchronized void createPrescription(PrescriptionDTO prescription)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO prescription VALUES ( "
				+ prescription.getId() + ",'" + prescription.getName() + "');");
		Connector.closeConnection();
	}

	@Override
	public synchronized PrescriptionDTO getPrescription(int prescriptionId)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescription WHERE prescription_id = "
						+ prescriptionId + ";");
		Connector.closeConnection();
		try {
			if (!rs.first()) {
				throw new DALException("råvaren med råvare id = "
						+ prescriptionId + " kunne ikke findes i databasen");
			}
			return new PrescriptionDTO(rs.getInt("prescription_id"),
					rs.getString("prescription_name"));
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i getPrescription(int prescripttionId)" +e.getMessage());
		}
	}

	@Override
	public synchronized List<PrescriptionDTO> getPrescriptionList()
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		List<PrescriptionDTO> list = new ArrayList<PrescriptionDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM prescription;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new PrescriptionDTO(rs.getInt("prescription_id"), rs
						.getString("prescription_name")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i getPrescriptionList()" +e.getMessage());
		}
		return list;
	}

	@Override
	public void updatePrescription(PrescriptionDTO prescription)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("UPDATE prescription SET "  
				+ " prescription_name = '" + prescription.getName() + "'" 
				+ " WHERE prescription_id = " + prescription.getId() + ";");
		Connector.closeConnection();
	}

	@Override
	public void deletePrescription(int id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		try{
		 Connector
				.doQuery("SELECT * FROM prescription WHERE prescription_id IN "
						+ "(SELECT prescription_id from prescriptioncomponent);");
		}catch(DALException e){
				Connector.doUpdate("DELETE FROM prescription WHERE prescription_id = " + id +";");
				Connector.closeConnection();
		}
		throw new DALException("du kan ikke slette den ønskede recept");
		
		
		
	}
}
