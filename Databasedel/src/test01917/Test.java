package test01917;

import java.sql.SQLException;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
import daoimpl01917.MySQLRaavareDAO;
import daointerfaces01917.DALException;
import dto01917.OperatoerDTO;
import dto01917.RaavareDTO;

public class Test {
	public static void main(String[] args) {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// test for raavare tabellen hvor man henter raavaren med id 4 ud
		System.out.println("Råvare nr 4:");
		MySQLRaavareDAO raavare = new MySQLRaavareDAO();
		try {
			System.out.println(raavare.getRaavare(4));
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}

		// test for raavare tabellen hvor man forsøger at sætte tomat med id 10
		// ind i tabellen
		System.out.println("indsæt tomat med id 10:");
		RaavareDTO tomat = new RaavareDTO(10, "tomat", "papa's pizza");
		MySQLRaavareDAO tomatDAO = new MySQLRaavareDAO();
		try {
			System.out.println(tomat + " blev indsat ");
			tomatDAO.createRaavare(tomat);
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}

		// test for raavare tabellen hvor man får alle raavarer ud i en
		// arraylist
		System.out.println("alle raavarer:");
		MySQLRaavareDAO raavareliste = new MySQLRaavareDAO();
		try {
			System.out.println(raavareliste.getRaavareList());
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}

	}

}
