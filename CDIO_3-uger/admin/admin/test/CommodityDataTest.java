package admin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.CommodityDTO;
import admin.data.CommodityData;
import admin.data.DALException;

public class CommodityDataTest {

	// Objects needed for testing
	private CommodityDTO commodity100, commodity200, commodity300,
			commodity400, commodity500, commodity600;
	private CommodityData commodityData;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.commodity100 = new CommodityDTO(100, "ethundrede", "lev100");
		this.commodity200 = new CommodityDTO(200, "tohundrede", "lev200");
		this.commodity300 = new CommodityDTO(300, "trehundrede", "lev300");
		this.commodity400 = new CommodityDTO(400, "firehundrede", "lev400");
		this.commodity500 = new CommodityDTO(500, "femhundrede", "lev500");
		this.commodity600 = new CommodityDTO(600, "sekshundrede", "lev600");
		this.commodityData = new CommodityData();
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.commodity100 = new CommodityDTO(100, "ethundrede", "lev100");
		this.commodity200 = new CommodityDTO(200, "tohundrede", "lev200");
		this.commodity300 = new CommodityDTO(300, "trehundrede", "lev300");
		this.commodity400 = new CommodityDTO(400, "firehundrede", "lev400");
		this.commodity500 = new CommodityDTO(500, "femhundrede", "lev500");
		this.commodity600 = new CommodityDTO(600, "sekshundrede", "lev600");
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
		Assert.assertNotNull(this.commodity400);
		Assert.assertNotNull(this.commodity500);
		Assert.assertNotNull(this.commodity600);
	}

	@Test
	public void testGetCommodity() throws DALException {
		commodityData.createCommodity(commodity200);
		int expected = 200;
		int actual = this.commodity200.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.getCommodity(actual);

		expected = 200;
		actual = commodityData.getCommodity(commodity200.getComId()).getComId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		commodityData.deleteCommodity(commodity200.getComId());
	}

	@Test
	public void testGetComList() throws DALException {
		// create list of users for testing
		List<CommodityDTO> expected = new ArrayList<CommodityDTO>();

		// Put users in DB
		commodityData.createCommodity(commodity300);
		commodityData.createCommodity(commodity400);

		// Put users in List
		expected.add(commodity300);
		expected.add(commodity400);

		List<CommodityDTO> actual = commodityData.getComList();

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		commodityData.deleteCommodity(commodity300.getComId());
		commodityData.deleteCommodity(commodity400.getComId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testCreateCommodity() throws DALException {
		int expected = 100;
		int actual = this.commodity100.getComId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodityData.createCommodity(commodity100);

		expected = 100;
		actual = commodityData.getCommodity(commodity100.getComId()).getComId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		commodityData.deleteCommodity(commodity100.getComId());
	}

	@Test
	public void testUpdateCommodity() throws DALException {
		// Put user in the DB
		commodityData.createCommodity(commodity500);

		// expected and actual values before the test
		String expected = "femhundrede";
		String actual = this.commodity500.getComName();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		commodity500.setCommodity_name("femfemhundrede");
		commodityData.updateCommodity(commodity500);

		// expected and actual values after the test
		expected = "femfemhundrede";
		actual = commodityData.getCommodity(commodity500.getComId())
				.getComName();

		Assert.assertEquals(expected, actual);
		commodityData.deleteCommodity(commodity500.getComId());
	}

	@Test
	public void testDeleteCommodity() throws DALException {
		// create list of users for testing
		List<CommodityDTO> expected = new ArrayList<CommodityDTO>();

		// Put user in the DB
		commodityData.createCommodity(commodity600);

		// Put users in List
		expected.add(commodity600);

		// Perform the action to be tested
		commodityData.deleteCommodity(commodity600.getComId());

		// List after deletion
		List<CommodityDTO> actual = commodityData.getComList();

		// Run through the list an check if elements match
		if (expected.isEmpty() && actual.isEmpty()) {
			expected.equals(actual);
		}

		// clear lists after use
		expected.clear();
		actual.clear();
	}

}
