package admin.data;

/**
 * 
 * The read and write access interface to the database.
 * <p>
 * The interface extends <code>IDataReadOnly</code> with methods needed to edit
 * users in the database
 * 
 */
public interface IUserDAO extends IUsersReadOnly {
	/**
	 * Inserts a new user entry into the database
	 * 
	 * @param user
	 *            the entry to be added to the database
	 * @throws DALException
	 *             if an user with the same id already exist
	 */
	void createUser(UserDTO user) throws DALException;

	/**
	 * Updates an existing user already in the database
	 * 
	 * @param user
	 *            the new info on the user
	 * @throws DALException
	 *             if no user with the same id exist
	 */
	void updateUser(UserDTO user) throws DALException;
	
	/**
	 * Deletes the user with the given ID.
	 * @param id the ID of the user to be deleted
	 * @throws DALException if no user with the given ID exist.
	 */
	void deleteUser(int id) throws DALException;
}
