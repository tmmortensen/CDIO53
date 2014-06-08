package admin.data;

import java.util.List;

/**
 * The read-only interface to the data layer
 */
public interface IUsersReadOnly {
	/**
	 * 
	 * @param oprId
	 *            the id of the user to get info on
	 * @return the in info on the user with the given id
	 * @throws DALException
	 *             if the id is out of bounds or user id doesn't exist in
	 *             the database
	 */
	UserDTO getUser(int userId) throws DALException;

	/**
	 * 
	 * @return a list of all users present in the database
	 * @throws DALException
	 */
	List<UserDTO> getUserList() throws DALException;
}
