package admin.test;

import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.Connector;
import admin.data.DALException;
import admin.data.UserDTO;

public class UserDataTest {

	private UserDTO user;

	@Before
	public void setUp() throws Exception {
		this.user = new UserDTO(1, "UserTestGuy1", "UTG", "2111822231",
				"12345678", 1);
	}

	@After
	public void tearDown() throws Exception {
		this.user = new UserDTO(1, "UserTestGuy1", "UTG", "2111822231",
				"12345678", 1);
	}

	@Test
	public void testEntities() {
		Assert.assertNotNull(this.user);
	}

	@Test
	public void testGetUser() throws DALException {
		int expected = 1;
		int actual = this.user.getUserId();
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
							+ this.user.getUserId() + ";");
			Connector.closeConnection();
			if (!rs.first()) {
				throw new DALException("the user with user id: "
						+ this.user.getUserId() + "does not exist");
			}
			this.user = new UserDTO(rs.getInt("user_id"),
					rs.getString("user_name"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"),
					rs.getInt("user_type"));
		} catch (SQLException e) {
			throw new DALException(e);
		}

		expected = 1;
		actual = this.user.getUserId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

}
