package data;
/**
 * 
 *  The read and write access interface to the database.
 *  <p>
 *  The interface extends <code>IDataReadOnly</code> with methods needed to edit data in the database
 *
 */
public interface IOperatoerDAO extends IDataReadOnly {
	/**
	 * Inserts a new operator entry into the database
	 * @param opr the entry to be added to the database 
	 * @throws DALException if an operator with the same id already exist
	 */
	void createOperatoer(OperatoerDTO opr) throws DALException;
	
	/**
	 * Updates an existing operator already in the database
	 * @param opr the new info on the operator
	 * @throws DALException if no operator with the same id exist
	 */
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
