package data;

import java.util.List;
/**
 *  The read-only interface to the data layer
 */
public interface IDataReadOnly  {
	/**
	 *	 
	 * @param oprId the id of the operator to get info on
	 * @return the in info on the operator with the given id
	 * @throws DALException if the id is out of bounds or operator id doesn't exist in the database
	 */
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	
	/**
	 * 
	 * @return a list of all operators present in the database
	 * @throws DALException
	 */
	List<OperatoerDTO> getOperatoerList() throws DALException;
}
