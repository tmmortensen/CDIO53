package admin.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.PrescriptionCompDTO;
import admin.data.PrescriptionCompData;

public class PrescriptionCompDataTest {

	// Objects needed for testing
	private PrescriptionCompDTO prescriptionComp100, prescriptionComp200,
			prescriptionComp300;
	private PrescriptionCompData prescriptionCompData;

	@Before
	public void setUp() throws Exception {
		this.prescriptionComp100 = new PrescriptionCompDTO();
		this.prescriptionComp200 = new PrescriptionCompDTO();
		this.prescriptionComp300 = new PrescriptionCompDTO();
		this.prescriptionCompData = new PrescriptionCompData();
	}

	@After
	public void tearDown() throws Exception {
		this.prescriptionComp100 = new PrescriptionCompDTO();
		this.prescriptionComp200 = new PrescriptionCompDTO();
		this.prescriptionComp300 = new PrescriptionCompDTO();
		this.prescriptionCompData = new PrescriptionCompData();
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.prescriptionComp100);
		Assert.assertNotNull(this.prescriptionComp200);
		Assert.assertNotNull(this.prescriptionComp300);
	}

	@Test
	public void testCreatePrescriptionComp() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.createPrescriptionComp(prescriptionComp100);

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetPrescriptionComp() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.getComponentList();

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetComponentListInt() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.getComponentList(actual);

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetComponentList() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.getComponentList();

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeletePrescriptionCompIntInt() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.deletePrescriptionComp(actual,
				this.prescriptionComp100.getCommodityId());

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeletePrescriptionCompInt() throws DALException {
		int expected = 300;
		int actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionCompData.createPrescriptionComp(prescriptionComp100);

		// Result after test
		expected = 300;
		actual = this.prescriptionComp100.getPrescriptionId();
		Assert.assertEquals(expected, actual);
	}

}
