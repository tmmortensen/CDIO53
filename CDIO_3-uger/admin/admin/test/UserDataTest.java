package admin.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.UserDTO;
import admin.data.UserData;

public class UserDataTest {

	// Objects needed for testing
	private UserDTO user100, user200, user300;
	private UserData userData;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.userData = new UserData();
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
		userData.createUser(user300);

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
		userData.getUser(actual);

		expected = 1;
		actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetUserList() throws DALException {
		int expected = 1;
		int actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);

		userData.getUserList();

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
		user100.setCpr("1111111112");
		userData.updateUser(user100);

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
		userData.deleteUser(actual);

		expected = 300;
		actual = this.user300.getUserId();
		Assert.assertEquals(expected, actual);
	}

}
