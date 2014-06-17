package admin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.UserDTO;
import admin.data.UserData;

public class UserDataTest {

	// Objects needed for testing
	private UserDTO user100, user200, user300, user400, user500, user600;
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
		this.user200 = new UserDTO(200, "UserTestGuy200", "U2", "2222222222",
				"Adminpw1", 0);
		this.user300 = new UserDTO(300, "UserTestGuy300", "U3", "3333333333",
				"12345678", 1);
		this.user400 = new UserDTO(400, "UserTestGuy400", "U4", "4444444444",
				"12345678", 1);
		this.user500 = new UserDTO(500, "UserTestGuy500", "U5", "5555555555",
				"12345678", 1);
		this.user600 = new UserDTO(600, "UserTestGuy600", "U6", "6666666666",
				"12345678", 1);
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.userData = new UserData();
		this.user100 = new UserDTO(100, "UserTestGuy100", "U1", "1111111111",
				"12345678", 1);
		this.user200 = new UserDTO(200, "UserTestGuy200", "U2", "2222222222",
				"Adminpw1", 0);
		this.user300 = new UserDTO(300, "UserTestGuy300", "U3", "3333333333",
				"12345678", 1);
		this.user400 = new UserDTO(400, "UserTestGuy400", "U4", "4444444444",
				"12345678", 1);
		this.user500 = new UserDTO(500, "UserTestGuy500", "U5", "5555555555",
				"12345678", 1);
		this.user600 = new UserDTO(600, "UserTestGuy600", "U6", "6666666666",
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
		Assert.assertNotNull(this.user400);
		Assert.assertNotNull(this.user500);
		Assert.assertNotNull(this.user600);
	}

	/**
	 * Method to test the creation of users in DB
	 * 
	 * @throws DALException
	 */
	@Test
	public void testCreateUser() throws DALException {
		int expected = 100;
		int actual = this.user100.getUserId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		userData.createUser(user100);

		expected = 100;
		actual = userData.getUser(user100.getUserId()).getUserId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		userData.deleteUser(user100.getUserId());
	}

	@Test
	public void testGetUser() throws DALException {
		userData.createUser(user200);
		int expected = 200;
		int actual = this.user200.getUserId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		userData.getUser(actual);

		expected = 200;
		actual = userData.getUser(user200.getUserId()).getUserId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		userData.deleteUser(user200.getUserId());
	}

	@Test
	public void testGetUserList() throws DALException {
		// create list of users for testing
		List<UserDTO> expected = new ArrayList<UserDTO>();

		// Put users in DB
		userData.createUser(user300);
		userData.createUser(user400);

		// Put users in List
		expected.add(user300);
		expected.add(user400);

		List<UserDTO> actual = userData.getUserList();

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		userData.deleteUser(user300.getUserId());
		userData.deleteUser(user400.getUserId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testUpdateUser() throws DALException {
		// Put user in the DB
		userData.createUser(user500);

		// expected and actual values before the test
		String expected = "5555555555";
		String actual = this.user500.getCpr();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		user500.setCpr("1111111112");
		userData.updateUser(user500);

		// expected and actual values after the test
		expected = "1111111112";
		actual = userData.getUser(user500.getUserId()).getCpr();

		Assert.assertEquals(expected, actual);
		userData.deleteUser(user500.getUserId());
	}

	@Test
	public void testDeleteUser() throws DALException {
		// create list of users for testing
		List<UserDTO> expected = new ArrayList<UserDTO>();

		// Put user in the DB
		userData.createUser(user600);

		// Put users in List
		expected.add(user600);

		// Perform the action to be tested
		userData.deleteUser(user600.getUserId());

		// List after deletion
		List<UserDTO> actual = userData.getUserList();

		// Run through the list an check if elements match
		if (expected.isEmpty() && actual.isEmpty()) {
			expected.equals(actual);
		}

		// clear lists after use
		expected.clear();
		actual.clear();
	}

}
