package admin.test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.ProductBatchCompDTO;
import admin.data.ProductBatchCompData;
import admin.data.UserDTO;

public class ProductBatchCompDataTest {

	// Objects needed for testing
	private ProductBatchCompDTO productBatchComp100, productBatchComp200,
			productBatchComp300, productBatchComp400, productBatchComp500,
			productBatchComp600;
	private ProductBatchCompData productBatchCompData;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.productBatchCompData = new ProductBatchCompData();
		this.productBatchComp100 = new ProductBatchCompDTO(100, 100, 100, 1.1,
				11.1);
		this.productBatchComp200 = new ProductBatchCompDTO(200, 200, 200, 2.2,
				22.2);
		this.productBatchComp300 = new ProductBatchCompDTO(300, 300, 300, 3.3,
				33.3);
		this.productBatchComp400 = new ProductBatchCompDTO(400, 400, 400, 4.4,
				44.4);
		this.productBatchComp500 = new ProductBatchCompDTO(500, 500, 500, 5.5,
				55.5);
		this.productBatchComp600 = new ProductBatchCompDTO(600, 600, 600, 6.6,
				66.6);
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.productBatchCompData = new ProductBatchCompData();
		this.productBatchComp100 = new ProductBatchCompDTO(100, 100, 100, 1.1,
				11.1);
		this.productBatchComp200 = new ProductBatchCompDTO(200, 200, 200, 2.2,
				22.2);
		this.productBatchComp300 = new ProductBatchCompDTO(300, 300, 300, 3.3,
				33.3);
		this.productBatchComp400 = new ProductBatchCompDTO(400, 400, 400, 4.4,
				44.4);
		this.productBatchComp500 = new ProductBatchCompDTO(500, 500, 500, 5.5,
				55.5);
		this.productBatchComp600 = new ProductBatchCompDTO(600, 600, 600, 6.6,
				66.6);
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.productBatchComp100);
		Assert.assertNotNull(this.productBatchComp200);
		Assert.assertNotNull(this.productBatchComp300);
		Assert.assertNotNull(this.productBatchComp400);
		Assert.assertNotNull(this.productBatchComp500);
		Assert.assertNotNull(this.productBatchComp600);
	}

	@Test
	public void testGetProductBatchComp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCertainProductBatchComps() {
		// create list of for testing
		List<ProductBatchCompDTO> expected = new ArrayList<ProductBatchCompDTO>();
		productBatchCompData.createProductBatchComp(productBatchComp200);

		// Put users in List
		expected.add(productBatchComp200);

		List<UserDTO> actual = productBatchCompData.getProductBatchByStatus();

		// Perform the action to be tested
		productBatchComp200.setStatus(4);
		productBatchCompData.updateProductBatch(productBatchComp200);

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		productBatchCompData.deleteBatch(productBatchComp200.getPbId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testGetAllProductBatchComps() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateProductBatchComp() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateProductBatchComp() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteProductBatchComp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

}
