package admin.data;

import java.util.Random;

/**
 * A data package containing info on an operator
 */
public class UserDTO{
	int userId;
	String username;
	String ini;
	String cpr;
	String password;
	int accessLevel;


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

	public UserDTO(int id, String name, String ini, String cpr, String pw, int access) throws DALException{
		setUsername(name);
		setIni(ini);
		setCpr(cpr);
		setPassword(pw);
		setAccessLevel(access);
		setUserId(id);
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
		setUsername(op.getOprNavn());
		setIni(op.getIni());
		setCpr(op.getCpr());
		setPassword(op.getPassword());
		setAccessLevel(op.getAccesLevel());
		setUserId(oprId);
	}

	public int getUserId() { return userId; }

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
	
	public void setUserId(int id) throws DALException {
		if ( id < 1)
			throw new DALException("ID skal være større end 0");
		if (id > 99999999)
			throw new DALException("ID skal være mindre end 99999999");
		this.userId = id;
	}
	
	public String getUsername(){
		return username;
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
	
	public boolean isAdmin(){
		return accessLevel == 0;
	}
	
	public int getAccesLevel(){
		return accessLevel;
	}

	public void setUsername(String newname)  throws DALException {
		if (newname.length() < 2)
			throw new DALException("Operatør navn er for kort");
		if (newname.length() > 20)
			throw new DALException("Operatør navn er for langt");
		this.username = newname;
	}

	public void setIni(String newini)  throws DALException {
		if (newini.length() < 2)
			throw new DALException("Operatør initialer er for få");
		if (newini.length() > 3)
			throw new DALException("Operatør initialer er for mange");
		this.ini = newini;
	}

	public void setCpr(String newcpr)  throws DALException {
		if (!newcpr.matches("\\d{10}"))
			throw new DALException("CPR-nummer er ikke 10 cifre");
		this.cpr = newcpr;
	}

	public void setPassword(String newpassword)  throws DALException {
		if (newpassword.length() < 7)
			throw new DALException("Kodeord er for kort");
		if (newpassword.length() > 8)
			throw new DALException("Kodeord er for langt");
		this.password = newpassword;
	}
	
	public void setAccessLevel(int access){
		this.accessLevel = access;
	}

	public void setAccessLevel(UserType type){
		this.accessLevel = type.ordinal();
	}

}