package admin.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.CommodityDTO;
import admin.data.CommodityData;
import admin.data.DALException;

public class CommodityDataTest {

	// Objects needed for testing
	private CommodityDTO commodity100, commodity200, commodity300;
	private CommodityData commodityData;

	@Before
	public void setUp() throws Exception {
		this.commodity100 = new CommodityDTO();
		this.commodity200 = new CommodityDTO();
		this.commodity300 = new CommodityDTO();
		this.commodityData = new CommodityData();
	}

	@After
	public void tearDown() throws Exception {
		this.commodity100 = new CommodityDTO();
		this.commodity200 = new CommodityDTO();
		this.commodity300 = new CommodityDTO();
		this.commodityData = new CommodityData();
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.commodity100);
		Assert.assertNotNull(this.commodity200);
		Assert.assertNotNull(this.commodity300);
	}

	@Test
	public void testGetCommodity() throws DALException {
		int expected = 300;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.getCommodity(actual);

		// Result after test
		expected = 300;
		actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetComList() throws DALException {
		int expected = 300;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.getComList();

		// Result after test
		expected = 300;
		actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCreateCommodity() throws DALException {
		int expected = 300;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.createCommodity(commodity100);

		// Result after test
		expected = 300;
		actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testUpdateCommodity() throws DALException {
		int expected = 300;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.updateCommodity(commodity100);

		// Result after test
		expected = 300;
		actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeleteCommodity() throws DALException {
		int expected = 300;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.deleteCommodity(actual);

		// Result after test
		expected = 300;
		actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);
	}

}
