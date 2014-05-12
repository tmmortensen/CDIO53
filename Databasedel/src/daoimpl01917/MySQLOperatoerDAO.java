package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO {
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
		try {
			if (!rs.first())
				throw new DALException("Operatoeren " + oprId + " findes ikke");
			return new OperatoerDTO(rs.getInt("opr_id"),
					rs.getString("opr_navn"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"));
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	public void createOperatoer(OperatoerDTO opr) throws DALException {
		Connector
				.doUpdate("INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES "
						+ "("
						+ opr.getOprId()
						+ ", '"
						+ opr.getOprNavn()
						+ "', '"
						+ opr.getIni()
						+ "', '"
						+ opr.getCpr()
						+ "', '" + opr.getPassword() + "')");
	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate("UPDATE operatoer SET  opr_navn = '"
				+ opr.getOprNavn() + "', ini =  '" + opr.getIni()
				+ "', cpr = '" + opr.getCpr() + "', password = '"
				+ opr.getPassword() + "' WHERE opr_id = " + opr.getOprId());
	}

	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
		try {
			while (rs.next()) {
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs
						.getString("opr_navn"), rs.getString("ini"), rs
						.getString("cpr"), rs.getString("password")));
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

}
