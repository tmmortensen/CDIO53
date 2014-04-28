package data;

import java.util.Random;

/**
 * A data package containing info on an operator
 */
public class OperatoerDTO {
	int oprId;
	String oprNavn;
	String ini;
	String cpr;
	String password;

	/**
	 * @param id
	 *            The id of the operator
	 * @param name
	 *            The name of the operator
	 * @param ini
	 *            The operators initials
	 * @param cpr
	 *            The social security number of the operator
	 * @param pw
	 *            The operator's password
	 */
	public OperatoerDTO(int id, String name, String ini, String cpr, String pw) {
		this.oprId = id;
		this.oprNavn = name;
		this.ini = ini;
		this.cpr = cpr;
		this.password = pw;
	}

	/**
	 * Creates a new <code>OperatoerDTO</code> with the info from the provided
	 * <code>Operator</code>-object and the given id
	 * 
	 * @param oprId
	 *            the id of the operator
	 * @param op
	 *            The <code>Operator</code>-object containing the rest of the
	 *            information
	 */
	public OperatoerDTO(int oprId, Operator op) {
		this.oprId = oprId;
		this.oprNavn = op.getOprNavn();
		this.ini = op.getIni();
		this.cpr = op.getCpr();
		this.password = op.getPassword();
	}

	public static boolean checkPassword(String password){
		int i = 0;
		if (password.matches(".*[0-9]+.*"))
			i++;
		if (password.matches(".*[a-z]+.*"))
			i++;
		if (password.matches(".*[A-Z]+.*"))
			i++;
		if (password.matches(".*\\W+.*"))
			i++;
		if (password.contains(" ") || password.length() < 6)
			i=0;
		if(i >= 3)
			return true;
		else
			return false;
	}
	public static String generatePassword(){
		String numbers = "0123456789";
		String lowerCase = "abcdefghijklmnopqrstuwxyz";
		String upperCase = lowerCase.toUpperCase();
		String specialChar = "!\"#¤%&/()=";
		String legalchars = numbers+lowerCase+upperCase+specialChar;
		String returnString = "";
		for(int i=0; i<6; i++){
			returnString += legalchars.charAt(new Random().nextInt(legalchars.length()));
		}
		if (!returnString.matches(".*[0-9]+.*"))
			returnString += numbers.charAt(new Random().nextInt(numbers.length()));
		if (!returnString.matches(".*[a-z]+.*"))
			returnString += lowerCase.charAt(new Random().nextInt(lowerCase.length()));
		if (!returnString.matches(".*[A-Z]+.*"))
			returnString += upperCase.charAt(new Random().nextInt(upperCase.length()));
		if (!returnString.matches(".*\\W+.*"))
			returnString += specialChar.charAt(new Random().nextInt(specialChar.length()));
		return returnString;
	}
	public int getOprId() {
		return oprId;
	}

	public String getOprNavn() {
		return oprNavn;
	}

	public String getIni() {
		return ini;
	}

	public String getCpr() {
		return cpr;
	}

	public String getPassword() {
		return password;
	}

	public void setOprId(int id) {
		this.oprId = id;
	}

	public void setOprNavn(String name) {
		this.oprNavn = name;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public void setPassword(String pw) {
		this.password = pw;
	}

}