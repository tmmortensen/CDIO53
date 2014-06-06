package admin.data;

import java.util.Random;

/**
 * A data package containing info on an operator
 */
public class UserDTO extends Operator{
	int oprId;

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
	public UserDTO(int id, String name, String ini, String cpr, String pw) throws DALException{
		this( id, name, ini, cpr, pw, 3);
	}

	public UserDTO(int id, String name, String ini, String cpr, String pw, int admin) throws DALException{
		super(name, ini, cpr, pw, admin);
		setOprId(id);
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
	public UserDTO(int oprId, Operator op) throws DALException{
		super(op);
		setOprId(oprId);
	}

	public int getOprId() { return oprId; }

	public static boolean checkPassword(String password){
		int i = 0;
		if (password.matches(".*[0-9]+.*"))
			i++;
		if (password.matches(".*[a-z]+.*"))
			i++;
		if (password.matches(".*[A-Z]+.*"))
			i++;
		if (password.matches(".*[\\W_]+.*"))
			i++;
		if (password.contains(" ") || password.length() < 7 || password.length() > 8)
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
		String specialChar = "!\"#¤%&/()=_";
		String legalchars = numbers+lowerCase+upperCase+specialChar;
		String returnString = "";
		returnString += lowerCase.charAt(new Random().nextInt(lowerCase.length()));
		returnString += numbers.charAt(new Random().nextInt(numbers.length()));
		returnString += upperCase.charAt(new Random().nextInt(upperCase.length()));
		returnString += specialChar.charAt(new Random().nextInt(specialChar.length()));
		while(returnString.length() < 7){
			returnString += legalchars.charAt(new Random().nextInt(legalchars.length()));
		}
		return returnString;
	}
	
	public void setOprId(int id) throws DALException {
		if ( id < 1)
			throw new DALException("ID skal være større end 0");
		if (id > 99999999)
			throw new DALException("ID skal være mindre end 99999999");
		this.oprId = id;
	}
	public String getOprNavn(){
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
	
	public void setAdmin(boolean admin) {
		if (admin) this.accessLevel = 0;
		else this.accessLevel = 3;
	}
}