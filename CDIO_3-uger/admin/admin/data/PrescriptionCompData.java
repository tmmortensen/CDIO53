package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionCompData implements IPrescriptionCompDAO {

	@Override
	public synchronized void createPrescriptionComp(PrescriptionCompDTO prescription)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("INSERT INTO prescriptioncomponent VALUES ( "
				+ prescription.getPrescriptionId()
				+ ", " + prescription.getCommodityId()
				+ ", " + prescription.getNomNetto()
				+ ", " + prescription.getTolerance() + ");");
		Connector.closeConnection();
	}

	@Override
	public synchronized PrescriptionCompDTO getPrescriptionComp(int prescription_id, int commodity_id)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescriptioncomponent WHERE prescription_id = "
						+ prescription_id + " AND commodity_id = " + commodity_id +";");
		Connector.closeConnection();
		try {
			if (!rs.first()) {
				throw new DALException("the commodity with the id = "
						+ prescription_id + " does not exist");
			}
			return new PrescriptionCompDTO(rs.getInt("prescription_id"),
					rs.getInt("commodity_id"), rs.getDouble("nom_netto"),
					rs.getDouble("tolerance"));
		} catch (SQLException e) {
			throw new DALException("Der skete en forbindelse med databasen");
		}
	}

	@Override
	public List<PrescriptionCompDTO> getComponentList(int prescriptionId)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		List<PrescriptionCompDTO> list = new ArrayList<PrescriptionCompDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM weight.prescriptioncomponent "
						+ "WHERE prescription_id=" +prescriptionId + " ;");
		
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new PrescriptionCompDTO(rs.getInt("prescription_id"),
						rs.getInt("commodity_id"), rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i forbindelse med databasen");
		}
		return list;
	}

	@Override
	public List<PrescriptionCompDTO> getComponentList() throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		List<PrescriptionCompDTO> list = new ArrayList<PrescriptionCompDTO>();
		ResultSet rs = Connector
				.doQuery("SELECT * FROM prescriptioncomponent;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new PrescriptionCompDTO(rs.getInt("prescription_id"),
						rs.getInt("commodity_id"), rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en fejl i forbindelse med databasen");
		}
		return list;
	}

	@Override
	public void deletePrescriptionComp(int prescriptionId, int commodityId)
			throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		try{
		Connector.doUpdate("DELETE FROM prescriptioncomponent WHERE prescription_id = " + prescriptionId 
							+ " AND commodity_id = " + commodityId + ";" );
		Connector.closeConnection();
		}catch(DALException e){
			throw new DALException("recept komponenten kunne ikke slettes ");
			}
	}

	@Override
	public void deletePrescriptionComp(int prescriptionId) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			throw new DALException("Der kunne ikke oprettes forbindelse til databasen");
		}
		Connector.doUpdate("DELETE FROM prescriptioncomponent WHERE prescription_id = " + prescriptionId + ";");
	}
	

}
