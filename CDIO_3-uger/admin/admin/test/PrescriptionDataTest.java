package admin.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.PrescriptionDTO;
import admin.data.PrescriptionData;

public class PrescriptionDataTest {

	// Objects needed for testing
	private PrescriptionDTO prescription100, prescription200, prescription300;
	private PrescriptionData prescriptionData;

	@Before
	public void setUp() throws Exception {
		this.prescription100 = new PrescriptionDTO();
		this.prescription200 = new PrescriptionDTO();
		this.prescription300 = new PrescriptionDTO();
		this.prescriptionData = new PrescriptionData();
	}

	@After
	public void tearDown() throws Exception {
		this.prescription100 = new PrescriptionDTO();
		this.prescription200 = new PrescriptionDTO();
		this.prescription300 = new PrescriptionDTO();
		this.prescriptionData = new PrescriptionData();
	}

	/**
	 * Test of entities
	 */
	@Test
	public void testEntities() {
		Assert.assertNotNull(this.prescription100);
		Assert.assertNotNull(this.prescription200);
		Assert.assertNotNull(this.prescription300);
	}

	@Test
	public void testCreatePrescription() throws DALException {
		int expected = 300;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.createPrescription(prescription100);

		// Result after test
		expected = 300;
		actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetPrescription() throws DALException {
		int expected = 300;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.getPrescription(actual);

		// Result after test
		expected = 300;
		actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetPrescriptionList() throws DALException {
		int expected = 300;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.getPrescriptionList();

		// Result after test
		expected = 300;
		actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testUpdatePrescription() throws DALException {
		int expected = 300;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.updatePrescription(prescription100);

		// Result after test
		expected = 300;
		actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testDeletePrescription() throws DALException {
		int expected = 300;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.createPrescription(prescription100);

		// Result after test
		expected = 300;
		actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);
	}

}
