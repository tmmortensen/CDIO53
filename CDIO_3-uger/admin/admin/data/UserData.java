package admin.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserData implements IUserDAO {

	Map<Integer, Operator> operators;
	int adminCount;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/weight";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	Connection conn = null;
	Statement stmt = null;

	public UserData() {
		// operators = new HashMap<Integer, Operator>();
		// adminCount = 0;

		// creating the sysadmin
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		// createOperatoer(new OperatoerDTO(1, "Sysadmin", "SA", "1234567890",
		// "adminpw", true));
		// } catch (Exception e) {
		// }
		//
		// // TODO remove this when done testing
		// createDefaultOperators();
	}

	@Override
	public synchronized UserDTO getOperatoer(int oprId)
			throws DALException {
		// get operator
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		if (oprId < 1 || oprId > 99999999)
			throw new DALException("Id out of bounds");
		Operator operator = operators.get(oprId);
		if (operator == null)
			throw new DALException("Id not found");
		return new UserDTO(oprId, operator);
	}

	@Override
	public List<UserDTO> getOperatoerList() throws DALException {
		// Get operator list
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		List<UserDTO> returnlist = new ArrayList<UserDTO>();
		for (Map.Entry<Integer, Operator> entry : operators.entrySet()) {
			returnlist.add(new UserDTO(entry.getKey(), entry.getValue()));
		}
		return returnlist;
	}

	@Override
	public synchronized void createOperatoer(UserDTO opr)
			throws DALException {
		// creating the operator
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO employees VALUES (" + opr.getOprId() + " , "
					+ opr.getOprNavn() + " , " + opr.getIni() + " , "
					+ opr.getCpr() + " , " + opr.getPassword() + " , "
					+ opr.isAdmin() + ");";
//			ResultSet rs = stmt.executeUpdate(sql);

			// Clean-up environment
//			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result != null) {
			operators.put(opr.getOprId(), result);
			throw new DALException("Operator ID already in use");
		}
		if (opr.isAdmin())
			adminCount++;
	}

	@Override
	public synchronized void updateOperatoer(UserDTO opr)
			throws DALException {

		// Update operator
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result == null) {
			operators.remove(opr.getOprId());
			throw new DALException("Operator ID does not exist.");
		}
		if (!opr.isAdmin() && result.isAdmin()) {
			if (adminCount == 1) {
				operators.put(opr.getOprId(), result);
				throw new DALException(
						"Kan ikke fjerne administratorrettighedder fra den sidste administrator");
			} else
				adminCount--;
		}
		if (opr.isAdmin() && !result.isAdmin())
			adminCount++;
	}

	@Override
	public synchronized void deleteOperatoer(int id) throws DALException {
		// Delete operator
		try {
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Employees";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id1 = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");

				// Display values
				System.out.print("ID: " + id1);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);
			}
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}// nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		if (id < 1 || id > 99999999)
			throw new DALException("Id out of bounds");
		Operator result = operators.remove(id);
		if (result == null)
			throw new DALException("Operator does not exist");
		if (result.isAdmin()) {
			if (adminCount == 1) {
				operators.put(id, result);
				throw new DALException(
						"Kan ikke fjerne den sidste administrator");
			} else
				adminCount--;
		}
	}

	public void createDefaultOperators() {
		try {
			UserDTO newOp = new UserDTO(11, "Test Guy", "TG",
					"1234567890", UserDTO.generatePassword());
			createOperatoer(newOp);

			newOp = new UserDTO(12, "Test Guy 2", "TG2", "1234567890",
					UserDTO.generatePassword());
			createOperatoer(newOp);

			newOp = new UserDTO(13, "Test Guy 3", "TG3", "1234567890",
					UserDTO.generatePassword());
			createOperatoer(newOp);

			newOp = new UserDTO(14, "Test Guy 4", "TG4", "1234567890",
					UserDTO.generatePassword());
			createOperatoer(newOp);

			newOp = new UserDTO(10, "Admin", "AM", "1234567890",
					"adminpw", 0);
			createOperatoer(newOp);
		} catch (Exception e) {
		}
	}
}
