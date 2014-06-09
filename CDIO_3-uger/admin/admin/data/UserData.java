package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserData implements IUserDAO {

	public UserData() {
		// creating the sysadmin
		try {
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(1,sysAdmin, 0, SM, 1234567890, adminpw ");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public synchronized UserDTO getUser(int oprId) throws DALException {
		ResultSet rs = Connector
				.doQuery("SELECT * FROM operatoer WHERE opr_id = " + oprId);
		try {
			if (!rs.first())
				throw new DALException("the employee with user id: " + oprId
						+ "does not exist");
			return new UserDTO(rs.getInt("opr_id"), rs.getString("opr_navn"),
					rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"), rs.getInt("opr_type"));

		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = new ArrayList<UserDTO>();

		ResultSet rs = Connector
				.doQuery("SELECT * FROM Employees;");
		try {
			while (rs.next()) {
				list.add(new UserDTO(rs.getInt("opr_id"), rs
						.getString("opr_navn"), rs.getString("ini"), rs
						.getString("cpr"), rs.getString("password"), rs
						.getInt("opr_type")));

			}
		} catch (SQLException e) {
			throw new DALException(e);
		}

		return list;

	}

	@Override
	public synchronized void createUser(UserDTO opr) throws DALException {
		Connector.doUpdate("INSERT INTO operatoer VALUES (" + opr.getUserId()
				+ " , " + opr.getUsername() + " , " + opr.getIni() + " , "
				+ opr.getCpr() + " , " + opr.getPassword() + " , "
				+ opr.isAdmin() + ");");

	}

	@Override
	public synchronized void updateUser(UserDTO opr) throws DALException {
		Connector
				.doUpdate("UPDATE operatoer " + "SET opr_id = "
						+ opr.getUserId() + ", opr_navn = " + opr.getUsername()
						+ ", opr_type = " + opr.isAdmin() + ", ini = "
						+ opr.getIni() + ", cpr = " + opr.getCpr()
						+ ", password = " + opr.getPassword()
						+ "WHERE opr_id = " + opr.getUserId() + ";");
	}

	@Override
	public synchronized void deleteUser(int id) throws DALException {
		Connector.doUpdate("DELETE FROM operatoer WHERE opr_id = " + id + ";");
	}

	public void createDefaultOperators() {
		try {
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(11,Test Guy, 2, TG, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(12,Test Guy 2, 3, TG2, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(13,Test Guy 3, 2, TG3, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(14,Test Guy 4, 3, TG4, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(15,Test Guy 5, 3, TG5, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(16,Test Guy 6, 1, TG6, 1234567890, "
							+ UserDTO.generatePassword() + ";");
			Connector
					.doUpdate("INSERT INTO operatoer VALUES(10, Admin, 0, AM, 1234567890, adminpw; ");
		} catch (Exception e) {
		}
	}
}
