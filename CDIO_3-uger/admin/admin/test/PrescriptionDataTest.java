package admin.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import admin.data.DALException;
import admin.data.PrescriptionDTO;
import admin.data.PrescriptionData;

public class PrescriptionDataTest {

	// Objects needed for testing
	private PrescriptionDTO prescription100, prescription200, prescription300,
			prescription400, prescription500, prescription600;
	private PrescriptionData prescriptionData;

	/**
	 * Sets up the entities needed for the test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.prescription100 = new PrescriptionDTO(100, "ethundrede");
		this.prescription200 = new PrescriptionDTO(200, "tohundrede");
		this.prescription300 = new PrescriptionDTO(300, "trehundrede");
		this.prescription400 = new PrescriptionDTO(400, "firehundrede");
		this.prescription500 = new PrescriptionDTO(500, "femhundrede");
		this.prescription600 = new PrescriptionDTO(600, "sekhundrede");
		this.prescriptionData = new PrescriptionData();
	}

	/**
	 * Cleans up what is used for testing
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.prescription100 = new PrescriptionDTO(100, "ethundrede");
		this.prescription200 = new PrescriptionDTO(200, "tohundrede");
		this.prescription300 = new PrescriptionDTO(300, "trehundrede");
		this.prescription400 = new PrescriptionDTO(400, "firehundrede");
		this.prescription500 = new PrescriptionDTO(500, "femhundrede");
		this.prescription600 = new PrescriptionDTO(600, "sekhundrede");
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

	/**
	 * Method to test the creation of a prescription in DB
	 * 
	 * @throws DALException
	 */
	@Test
	public void testCreatePrescription() throws DALException {
		int expected = 100;
		int actual = this.prescription100.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.createPrescription(prescription100);

		expected = 100;
		actual = prescriptionData.getPrescription(prescription100.getId())
				.getId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		prescriptionData.deletePrescription(prescription100.getId());
	}

	@Test
	public void testGetPrescription() throws DALException {
		prescriptionData.createPrescription(prescription200);
		int expected = 200;
		int actual = this.prescription200.getId();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescriptionData.getPrescription(actual);

		expected = 200;
		actual = prescriptionData.getPrescription(prescription200.getId())
				.getId();
		Assert.assertEquals(expected, actual);

		// Delete from DB
		prescriptionData.deletePrescription(prescription200.getId());
	}

	@Test
	public void testGetPrescriptionList() throws DALException {
		// create list of users for testing
		List<PrescriptionDTO> expected = new ArrayList<PrescriptionDTO>();

		// Put users in DB
		prescriptionData.createPrescription(prescription300);
		prescriptionData.createPrescription(prescription400);

		// Put users in List
		expected.add(prescription300);
		expected.add(prescription400);

		List<PrescriptionDTO> actual = prescriptionData.getPrescriptionList();

		// Run through the list an check if elements match
		for (int i = 0; i < expected.size(); i++) {
			expected.equals(actual);
		}
		// Delete after use
		prescriptionData.deletePrescription(prescription300.getId());
		prescriptionData.deletePrescription(prescription400.getId());
		expected.clear();
		actual.clear();
	}

	@Test
	public void testUpdatePrescription() throws DALException {
		// Put user in the DB
		prescriptionData.createPrescription(prescription500);

		// expected and actual values before the test
		String expected = "femhundrede";
		String actual = this.prescription500.getName();
		Assert.assertEquals(expected, actual);

		// Perform the action to be tested
		prescription500.setName("femfemhundrede");
		prescriptionData.updatePrescription(prescription500);

		// expected and actual values after the test
		expected = "femfemhundrede";
		prescriptionData.getPrescription(prescription500.getId()).getName();

		Assert.assertEquals(expected, actual);
		prescriptionData.deletePrescription(prescription500.getId());
	}

	@Test
	public void testDeletePrescription() throws DALException {
		// create list of users for testing
		List<PrescriptionDTO> expected = new ArrayList<PrescriptionDTO>();

		// Put user in the DB and in list
		prescriptionData.createPrescription(prescription600);
		expected.add(prescription600);

		// Perform the action to be tested
		prescriptionData.deletePrescription(prescription600.getId());
		List<PrescriptionDTO> actual = prescriptionData.getPrescriptionList();

		// Run through the list an check if elements match
		if (expected.isEmpty() && actual.isEmpty()) {
			expected.equals(actual);
		}

		// clear lists after use
		expected.clear();
		actual.clear();
	}
}
