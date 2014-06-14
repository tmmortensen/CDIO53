package admin.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.CommodityBatchDTO;
import admin.data.CommodityBatchData;
import admin.data.DALException;

public class CommodityBatchDataTest {

	// Objects needed for testing
	private CommodityBatchDTO commodityBatch100, commodityBatch200,
			commodityBatch300;
	private CommodityBatchData commodityBatchData;

	@Before
	public void setUp() throws Exception {
		this.commodityBatch100 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatch200 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatch300 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatchData = new CommodityBatchData();
	}

	@After
	public void tearDown() throws Exception {
		this.commodityBatch100 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatch200 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatch300 = new CommodityBatchDTO(0, 0, 0);
		this.commodityBatchData = new CommodityBatchData();
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.commodityBatch100);
		Assert.assertNotNull(this.commodityBatch200);
		Assert.assertNotNull(this.commodityBatch300);
	}

	@Test
	public void testGetCommodityBatch() throws DALException {
		int expected = 300;
		int actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityBatchData.getCommodityBatch(actual);

		// Result after test
		expected = 300;
		actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetComBatchList() throws DALException {
		int expected = 300;
		int actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityBatchData.getComBatchList();

		// Result after test
		expected = 300;
		actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testCreateCommodityBatch() throws DALException {
		int expected = 300;
		int actual = this.commodityBatch200.getCommodityBatchId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityBatchData.createCommodityBatch(commodityBatch200);

		// Result after test
		expected = 300;
		actual = this.commodityBatch200.getCommodityBatchId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testUpdateCommodityBatch() throws DALException {
		int expected = 300;
		int actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityBatchData.updateCommodityBatch(commodityBatch100);

		// Result after test
		expected = 300;
		actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeleteCommodityBatch() throws DALException {
		int expected = 300;
		int actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityBatchData.deleteCommodityBatch(actual);

		// Result after test
		expected = 300;
		actual = this.commodityBatch100.getCommodityBatchId();
		Assert.assertEquals(expected, actual);
	}

}
