package ase.data;

import java.net.InetAddress;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;
import admin.data.CommodityDTO;
import admin.data.Connector;
import admin.data.DALException;
import admin.data.UserDTO;

/**
 * Class used to change the program state
 * 
 * @author Gruppe 53
 * 
 */
public class ProgramState implements IProgramState {
	double tare = 0;
	double gross = 0;
	String display = "";
	String netString = "";
	String userInput = "";
	String receptName;
	int temp_id, commodityID;

	CommodityDTO commodity = new CommodityDTO();

	int port = 0;
	InetAddress address;

	boolean exit = false;
	long lastUpdate = 0;
	long lastInput = 0;

	public ProgramState() {
		lastUpdate = System.currentTimeMillis();
	}

	public String UserInfo(UserDTO operator) {
		String userName = operator.getUsername();
		return userName;
	}

	public UserDTO UserDTOgetUser(int temp_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			ResultSet rs = (ResultSet) Connector
					.doQuery("SELECT * FROM users WHERE user_id = " + temp_id
							+ ";");
			Connector.closeConnection();
			if (!rs.first()) {
				throw new DALException("the user with user id: " + temp_id
						+ "does not exist");
			}
			return new UserDTO(rs.getInt("user_id"), rs.getString("user_name"),
					rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"), rs.getInt("user_type"));

		} catch (SQLException e) {
			throw new DALException(e);
		}
	}
	
	
	// Mangler metode til at hente receptnavn fra Databasen
	

	@Override
	public void setNetString(String netString) {
		this.netString = netString;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public boolean hasDisplayUpdated(Long since) {
		return lastUpdate > since;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void setAddress(InetAddress address) {
		this.address = address;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public InetAddress getAddress() {
		return address;
	}

	@Override
	public boolean isRunning() {
		return !exit;
	}

	@Override
	public void tare(double Tweight) {
		tare = Tweight;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void setGross(double weight) {
		gross = weight;
		lastUpdate = System.currentTimeMillis();

	}

	public void setCommodityID(int comID) {

		commodityID = comID;

	}

	@Override
	public String getDisplayText() {
		return display;
	}

	@Override
	public void setDisplayText(String text) {
		display = text;
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void reset() {
		gross = 0;
		tare = 0;
		display = "";
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void quit() {
		exit = true;
	}

	@Override
	public boolean haveNewUserInput(Long since) {
		return lastInput > since;
	}

	@Override
	public String getUserInput() {
		return userInput;
	}

	@Override
	public void setUserInput(String text) {
		userInput = text;
		lastInput = System.currentTimeMillis();
	}

	@Override
	public String getNetString() {
		
		return null;
	}

}
