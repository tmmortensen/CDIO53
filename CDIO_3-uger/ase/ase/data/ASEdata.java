package ase.data;
import java.awt.SystemTray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import admin.data.CommodityBatchData;
import admin.data.DALException;
import admin.data.ProductBatchData;
import admin.data.UserDTO;
import admin.data.UserData;
import admin.data.UserInfo;

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

}
