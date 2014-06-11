package ase.data;
import java.awt.SystemTray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.data.CommodityBatchData;
import admin.data.Connector;
import admin.data.DALException;
import admin.data.ProductBatchData;
import admin.data.UserDTO;
import admin.data.UserData;
import admin.data.UserInfo;
import admin.data.UserType;

public class ASEdata {
	private String sentence, varenavn, choice;
	private String modifiedSentence;
	private double brutto = 0, tara = 0, netto = 0;
	private Socket clientSocket;
	private BufferedReader inFromServer;
	private DataOutputStream outToServer;
	
	UserData userdata;
	UserInfo userinfo;
	ProductBatchData productdata;
	
	
	public void initiateConnection() {
		try {
			clientSocket = new Socket("localhost", 4567);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToServer = new DataOutputStream(clientSocket.getOutputStream());

		} catch (Exception e) {
			e.getMessage();
		}		
		
	}
	
	public boolean oprIDIsReal(){
		
		// tjek for oprID findes i databasen
		
		return true;
	}
	
	public String produckbatchSQL(){
	
		
		return "sgh";
	}
	
	
//	public getVarenavn(int batch){
//		
//		return receptNavn;
//	}
	
	public String getDBName(int oprID){
		
	try {
		userdata.getUser(oprID);
	} catch (DALException e) {
		
		e.printStackTrace();
	}
	
		System.out.println(userinfo.name);
		return userinfo.name;
	}
	
//	public String getproductbatchname(int productbatch){
//		
//		String Productbatchname = product.
//		
//		return Productbatchname;
//		
//	}
	public UserDTO UserDTOgetUser(int user_id) throws DALException {
		try {
			Connector.connect();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			ResultSet rs = Connector
					.doQuery("SELECT * FROM users WHERE user_id = " + user_id
							+ ";");
			Connector.closeConnection();
			if (!rs.first()) {
				throw new DALException("the user with user id: " + user_id
						+ "does not exist");
			}
			return new UserDTO(rs.getInt("user_id"), rs.getString("user_name"),
					rs.getString("ini"), rs.getString("cpr"),
					rs.getString("password"), rs.getInt("user_type"));

		} catch (SQLException e) {
			throw new DALException(e);
		}
	}
	public String UserInfo(UserDTO operator){
		String name = operator.getUsername();
		return name;
	}
}
