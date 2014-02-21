package Controller;

import java.util.List;

import data.Data;
import data.DALException;
import data.OperatoerDTO;
import data.Operator;
import Boundary.Boundary;

public class MainMenu {
	String password, name;
	int oprID;
	Boundary boundary = new Boundary();
	Data data = new Data();

	public MainMenu() {
	}

	public void RunMain() throws DALException {

		boundary.login();

		Operator flemming = new Operator("Flemming", "FH", "367495-7483","Flemse");
		OperatoerDTO Operatoer1 = new OperatoerDTO(14, flemming);

		data.createOperatoer(Operatoer1);

		List<OperatoerDTO> operatorlist = data.getOperatoerList();
		
		for (int i = 0; i<=operatorlist.size() ; i++ ){
			
			if(operatorlist.get(i).getOprNavn()==name&&operatorlist.get(i).getPassword()==password){
				
				oprID = operatorlist.get(i).getOprId();
				
			}
			
		}
		
		
		
		
	}

	public String[] getLogin() {
		String[] user = new String[3];
		user[0] = oprID;
		user[1] = password;
		user[2] = name;
		return user;

	}

}
