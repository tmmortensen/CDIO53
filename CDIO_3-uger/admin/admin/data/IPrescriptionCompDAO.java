package admin.data;

import java.util.List;

public interface IPrescriptionCompDAO {

	/**
	 * Get the prescription component containing the specified commodity
	 * assigned to the specified prescription
	 * 
	 * @param prescriptionId
	 *            the id of the prescription containing the component
	 * @param commodityId
	 *            the id of the commodity in the component
	 * @return the one component with the given attributes
	 * @throws DALException
	 *             if no component with the given specifications exist
	 */
	public PrescriptionCompDTO getPrescriptionComp(int prescriptionId,
			int commodityId) throws DALException;

	/**
	 * Get the list of components assigned to a specific prescription
	 * 
	 * @param prescriptionId
	 *            the id of the prescription having the components
	 * @return a list of components assigned to the specified prescription
	 * @throws DALException
	 *             if no presciption with the given id exist.
	 */
	public List<PrescriptionCompDTO> getComponentList(int prescriptionId)
			throws DALException;

	/**
	 * Get the list of all prescription components in the database
	 * 
	 * @return the complete list of prescription components
	 * @throws DALException
	 *             maybe ?
	 */
	public List<PrescriptionCompDTO> getComponentList() throws DALException;

	/**
	 * Creates a new entry in the database with the information given in the
	 * provided <code>PrescriptionCompDTO</code>
	 * 
	 * @param prescription
	 *            the <code>PrescriptionCompDTO</code> containing the
	 *            information to be added to the database
	 * @throws DALException
	 *             if a component with the same prescription id and commodity id
	 *             already exists
	 */
	public void createPrescriptionComp(PrescriptionCompDTO component)
			throws DALException;

	/**
	 * Removes the prescription component with the given prescription id and
	 * commodity id from the database
	 * 
	 * @param prescriptionId
	 *            the id of the prescription having the component
	 * @param commodityId
	 *            the id of the commodity contained in the component
	 * @throws DALException
	 */
	public void deletePrescriptionComp(int prescriptionId, int commodityId)
			throws DALException;

}
