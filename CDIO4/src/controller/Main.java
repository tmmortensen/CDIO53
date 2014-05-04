package controller;

import boundary.Boundary;
import boundary.IBoundary;
import data.DALException;
import data.Data;
import data.IOperatoerDAO;
import data.OperatoerDTO;

public class Main {
	public static void main(String[] args) throws DALException {
		IBoundary boundary = new Boundary();
		IOperatoerDAO data = new Data();
		MainMenuOld mainMenu = new MainMenuOld(boundary, data);
		
		//TODO sætte passwords til noget der overholder opgave beskrivelsen
		OperatoerDTO newOp = new OperatoerDTO(11,"Test Guy", "TG", "123456-7890", OperatoerDTO.generatePassword());
		boundary.showStringMessage(newOp.getOprNavn() + " with id: 11 has the password: \"" + newOp.getPassword() + "\"");
		data.createOperatoer(newOp);
		newOp = new OperatoerDTO(12,"Test Guy 2", "TG2", "123456-7890", OperatoerDTO.generatePassword());
		boundary.showStringMessage(newOp.getOprNavn() + " with id: 12 has the password: \"" + newOp.getPassword() + "\"");
		data.createOperatoer(newOp);
		newOp = new OperatoerDTO(13,"Test Guy 3", "TG3", "123456-7890", OperatoerDTO.generatePassword());
		boundary.showStringMessage(newOp.getOprNavn() + " with id: 13 has the password: \"" + newOp.getPassword() + "\"");
		data.createOperatoer(newOp);
		newOp = new OperatoerDTO(14,"Test Guy 4", "TG4", "123456-7890", OperatoerDTO.generatePassword());
		boundary.showStringMessage(newOp.getOprNavn() + " with id: 14 has the password: \"" + newOp.getPassword() + "\"");
		data.createOperatoer(newOp);
		
		newOp = new OperatoerDTO(10,"Admin", "A", "123456-7890", "adminpassword");
		data.createOperatoer(newOp);
		
		mainMenu.RunMain();
	}
}
