package admin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.ProductBatchDTO;
import admin.data.ProductBatchData;
import admin.data.UserDTO;

public class ProductBatchDataTest {

	// Objects needed for testing
	private ProductBatchDTO productBatch100, productBatch200, productBatch300,
			productBatch400, productBatch500, productBatch600;
	private ProductBatchData productBatchData;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.productBatchData = new ProductBatchData();
		this.productBatch100 = new ProductBatchDTO();
		this.productBatch200 = new ProductBatchDTO();
		this.productBatch300 = new ProductBatchDTO();
		this.productBatch400 = new ProductBatchDTO();
		this.productBatch500 = new ProductBatchDTO();
		this.productBatch600 = new ProductBatchDTO();
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.productBatchData = new ProductBatchData();
		this.productBatch100 = new ProductBatchDTO();
		this.productBatch200 = new ProductBatchDTO();
		this.productBatch300 = new ProductBatchDTO();
		this.productBatch400 = new ProductBatchDTO();
		this.productBatch500 = new ProductBatchDTO();
		this.productBatch600 = new ProductBatchDTO();
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.productBatch100);
		Assert.assertNotNull(this.productBatch200);
		Assert.assertNotNull(this.productBatch300);
		Assert.assertNotNull(this.productBatch400);
		Assert.assertNotNull(this.productBatch500);
		Assert.assertNotNull(this.productBatch600);
	}

	@Test
	public void testCreateProductBatch() throws DALException {
		int expected = 100;
		int actual = this.productBatch100.getPbId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		productBatchData.createProductBatch(productBatch100);

		expected = 100;
		actual = productBatchData.getProductBatch(productBatch100.getPbId())
				.getPbId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		productBatchData.deleteBatch(productBatch100.getPbId());
	}

	@Test
	public void testGetCompletedProductBatch() throws DALException {
		// create list of for testing
		List<ProductBatchDTO> expected = new ArrayList<ProductBatchDTO>();
		productBatchData.createProductBatch(productBatch200);

		// Put users in List
		expected.add(productBatch200);

		List<UserDTO> actual = productBatchData.getProductBatchByStatus();

		// Perform the action to be tested
		productBatch200.setStatus(4);
		productBatchData.updateProductBatch(productBatch200);

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		productBatchData.deleteBatch(productBatch200.getPbId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testGetInitiatedProductBatch() {
		// create list of for testing
		List<ProductBatchDTO> expected = new ArrayList<ProductBatchDTO>();
		productBatchData.createProductBatch(productBatch200);

		// Put users in List
		expected.add(productBatch200);

		List<UserDTO> actual = productBatchData.getProductBatchByStatus();

		// Perform the action to be tested
		productBatch200.setStatus(4);
		productBatchData.updateProductBatch(productBatch200);

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		productBatchData.deleteBatch(productBatch200.getPbId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testGetUnInitializedProductBatch() {
		// create list of for testing
		List<ProductBatchDTO> expected = new ArrayList<ProductBatchDTO>();
		productBatchData.createProductBatch(productBatch200);

		// Put users in List
		expected.add(productBatch200);

		List<UserDTO> actual = productBatchData.getProductBatchByStatus();

		// Perform the action to be tested
		productBatch200.setStatus(4);
		productBatchData.updateProductBatch(productBatch200);

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		productBatchData.deleteBatch(productBatch200.getPbId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testUpdateStatus() {
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
	public void testDeleteBatch() {
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
