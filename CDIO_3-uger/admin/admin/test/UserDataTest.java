package admin.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.Connector;
import admin.data.DALException;
import admin.data.UserDTO;

public class UserDataTest {

	// New UserDTO object needed for test
	private UserDTO user100, user200, user300;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.user100 = new UserDTO(100, "UserTestGuy100", "U1", "1111111111",
				"12345678", 1);
		this.user200 = new UserDTO(1, "sysAdmin", "SM", "1234567890",
				"Adminpw1", 0);
		this.user300 = new UserDTO(300, "UserTestGuy300", "U3", "3333333333",
				"12345678", 1);
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.user100 = new UserDTO(100, "UserTestGuy1", "UTG", "2111822231",
				"12345678", 1);
		this.user200 = new UserDTO(1, "sysAdmin", "SM", "1234567890",
				"Adminpw1", 0);
		this.user300 = new UserDTO(300, "UserTestGuy3", "U3", "3333333333",
				"12345678", 1);
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.user100);
		Assert.assertNotNull(this.user200);
		Assert.assertNotNull(this.user300);
	}

	@Test
	public void testCreateUser() throws DALException {
		int expected = 300;
		int actual = this.user300.getUserId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Connector.doUpdate("INSERT INTO users VALUES ('" + user300.getUserId()
				+ "' , '" + user300.getUsername() + "' , '" + user300.getIni()
				+ "' , '" + user300.getCpr() + "' , '" + user300.getPassword()
				+ "' , '" + user300.getAccesLevel() + "');");
		Connector.closeConnection();

		expected = 300;
		actual = this.user300.getUserId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetUser() throws DALException {
		int expected = 1;
		int actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			ResultSet rs = Connector
					.doQuery("SELECT * FROM users WHERE user_id = "
							+ this.user200.getUserId() + ";");
			Connector.closeConnection();
			if (!rs.first()) {
				throw new DALException("the user with user id: "
						+ this.user200.getUserId() + "does not exist");
			}
			this.user200 = new UserDTO(rs.getInt("user_id"),
					rs.getString("user_name"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"),
					rs.getInt("user_type"));
		} catch (SQLException e) {
			throw new DALException(e);
		}

		expected = 1;
		actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetUserList() throws DALException {
		int expected = 1;
		int actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);

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
		expected = 1;
		actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testUpdateUser() throws DALException {
		String expected = "1111111111";
		String actual = this.user100.getCpr();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		user100.setCpr("1111111112");
		Connector.doUpdate("UPDATE users " + "SET user_name = " + "'"
				+ user100.getUsername() + "'" + ", ini = " + "'"
				+ user100.getIni() + "'" + ", cpr = " + "'" + user100.getCpr()
				+ "'" + ", password = " + "'" + user100.getPassword() + "'"
				+ ", user_type = " + user100.getAccesLevel()
				+ " WHERE user_id = " + user100.getUserId() + ";");
		Connector.closeConnection();
		expected = "1111111112";
		actual = this.user100.getCpr();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeleteUser() throws DALException {
		int expected = 300;
		int actual = this.user300.getUserId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ResultSet rs = Connector
				.doQuery("SELECT * FROM users where user_id in ( SELECT user_id from productbatchcomponent );");
		try {
			if (!rs.first()) {
				Connector.doUpdate("DELETE FROM users WHERE user_id = "
						+ this.user300.getUserId() + ";");
				Connector.closeConnection();
			} else {
				throw new DALException(
						"You cannot delete the user, because it has a productbatchcomponent attached to it's id");
			}

		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

}
