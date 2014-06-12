package admin.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url
	 *            must have the form "jdbc:mysql://<server>/<database>" for
	 *            default port (3306) OR
	 *            "jdbc:mysql://<server>:<port>/<database>" for specific port
	 *            more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */
	public static Connection connectToDatabase(String url, String username,
			String password) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// get Connection-object via DriverManager
		return (Connection) DriverManager
				.getConnection(url, username, password);
	}

	private static Connection conn;
	private static Statement stm;

	/**
	 * Connects with info on server, database, username and password
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		conn = connectToDatabase("jdbc:mysql://" + Constant.server + ":"
				+ Constant.port + "/" + Constant.database, Constant.username,
				Constant.password);
		stm = conn.createStatement();
	}

	/**
	 * Gets a resultset from when you do a query
	 * 
	 * @param cmd
	 *            string with the query
	 * @return
	 * @throws DALException
	 */
	public static ResultSet doQuery(String cmd) throws DALException {
		try {
			return stm.executeQuery(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	/**
	 * Used for updating tuples in data base
	 * 
	 * @param cmd
	 *            string with the query
	 * @return
	 * @throws DALException
	 */
	public static int doUpdate(String cmd) throws DALException {
		try {
			return stm.executeUpdate(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	/**
	 * Close connection to data base
	 */
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {

		}
	}
}
