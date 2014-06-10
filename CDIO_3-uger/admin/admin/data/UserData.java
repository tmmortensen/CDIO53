package admin.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserData implements IUserDAO {

	public UserData() {
		// if there are no users in the database, create a sysadmin
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			ResultSet rs = Connector.doQuery("SELECT COUNT(*) FROM users");
			Connector.closeConnection();
			if(rs.next()){
				if(!(rs.getInt(1)>0))
				// creating users for testing
				createDefaultUsers();	
			}
		} catch(Exception e){
			e.getMessage();
		}
	}

	@Override
	public synchronized UserDTO getUser(int user_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			ResultSet rs = Connector
					.doQuery("SELECT * FROM users WHERE user_id = " + user_id
							+ ";");
			Connector.closeConnection();
			if (!rs.first()) {
				throw new DALException("the user with user id: " + user_id
						+ "does not exist");
			}
			return new UserDTO(rs.getInt("user_id"), rs.getString("user_name"),
					rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"), rs.getInt("user_type"));

		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		List<UserDTO> list = new ArrayList<UserDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM users;");
		Connector.closeConnection();
		try {
			while (rs.next()) {
				list.add(new UserDTO(rs.getInt("user_id"), rs
						.getString("user_name"), rs.getString("ini"), rs
						.getString("cpr"), rs.getString("password"), rs
						.getInt("user_type")));

			}
		} catch (SQLException e) {
			throw new DALException(e);
		}

		return list;

	}

	@Override
	public synchronized void createUser(UserDTO opr) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("INSERT INTO users VALUES ('" + opr.getUserId()
				+ "' , '" + opr.getUsername() + "' , '" + opr.getIni()
				+ "' , '" + opr.getCpr() + "' , '" + opr.getPassword()
				+ "' , '" + opr.isAdmin() + "');");
		Connector.closeConnection();

	}

	@Override
	public synchronized void updateUser(UserDTO opr) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("UPDATE users " 
				+ "SET user_name = " + "'" + opr.getUsername() + "'" 
				+ ", ini = " + "'" + opr.getIni() + "'" 
				+ ", cpr = " + "'" + opr.getCpr() + "'" 
				+ ", password = " + "'" + opr.getPassword() + "'" 
				+ ", user_type = " + opr.isAdmin() 
				+ " WHERE user_id = " + opr.getUserId() + ";");
		Connector.closeConnection();
	}

	@Override
	public synchronized void deleteUser(int id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("UPDATE users SET user_type = 4  WHERE user_id = "
				+ id + ";");
		Connector.closeConnection();
	}

	public synchronized void createDefaultUsers() {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			Connector
					.doUpdate("INSERT INTO users VALUES(1,'sysAdmin', 'SM', '1234567890', 'adminpw', 0); ");
			Connector
					.doUpdate("INSERT INTO users VALUES(11,'Test Guy', 'TG', '1234567890', '"
							+ UserDTO.generatePassword() + "', 2);");
			Connector
					.doUpdate("INSERT INTO users VALUES(12,'Test Guy 2', 'TG2', '1234567890', '"
							+ UserDTO.generatePassword() + "', 3);");
			Connector
					.doUpdate("INSERT INTO users VALUES(13,'Test Guy 3', 'TG3', '1234567890', '"
							+ UserDTO.generatePassword() + "', 2);");
			Connector
					.doUpdate("INSERT INTO users VALUES(14,'Test Guy 4', 'TG4', '1234567890', '"
							+ UserDTO.generatePassword() + "', 3);");
			Connector
					.doUpdate("INSERT INTO users VALUES(15,'Test Guy 5', 'TG5', '1234567890', '"
							+ UserDTO.generatePassword() + "', 3);");
			Connector
					.doUpdate("INSERT INTO users VALUES(16,'Test Guy 6', 'TG6', '1234567890', '"
							+ UserDTO.generatePassword() + "', 1);");
			Connector
					.doUpdate("INSERT INTO users VALUES(10, 'Admin', 'AM', '1234567890', 'adminpw', 0); ");
			Connector.closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
