package admin.data;

import java.sql.SQLException;

/**
 * Data Access Layer Exception is thrown when errors occur in the data layer
 */
public class DALException extends Exception {
	private static final long serialVersionUID = 1L;

	public DALException(String s) {
		super(s);
	}

	public DALException(SQLException s) {
		super(s);
	}
}