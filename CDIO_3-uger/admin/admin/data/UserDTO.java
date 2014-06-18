package admin.data;

import java.util.Random;

/**
 * A data package containing info on an operator
 */
public class UserDTO {
	private int userId;
	private String username;
	private String ini;
	private String cpr;
	private String password;
	private UserType type;

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
	public UserDTO(int id, String name, String ini, String cpr, String pw)
			throws DALException {
		this(id, name, ini, cpr, pw, 3);
	}

	public UserDTO(int id, String name, String ini, String cpr, String pw,
			int access) throws DALException {
		setUsername(name);
		setIni(ini);
		setCpr(cpr);
		// setPassword(pw);
		// på denne måde kan man oprette instanser af UserDTO med passwords der
		// ikke overholder regler.
		password = pw;
		setAccessLevel(access);
		setUserId(id);
	}

	public int getUserId() {
		return userId;
	}

	public static boolean checkPassword(String password) {
		return chkPassWithMsg(password) == null;
	}

	public static String chkPassWithMsg(String password) {
		int i = 4;
		String msg = "";
		if (!password.matches(".*[0-9]+.*")) {
			i--;
			msg += "Password har ingen tal\n";
		}
		if (!password.matches(".*[a-z]+.*")) {
			i--;
			msg += "Password har ingen små bogstaver\n";
		}
		if (!password.matches(".*[A-Z]+.*")) {
			i--;
			msg += "Password har ingen store bogstaver\n";
		}
		if (!password.matches(".*[\\W_]+.*")) {
			i--;
			msg += "Password har ingen specialtegn\n";
		}
		if (password.length() < 5) {
			i = 0;
			msg += "Password er for kort (midre end 5 karakterer)";
		}
		if (password.length() > 8) {
			i = 0;
			msg += "Password er for langt (mrer end 8 karakterer)";
		}
		if (i >= 3)
			return null;
		else
			return msg;
	}

	public static String generatePassword() {
		String numbers = "0123456789";
		String lowerCase = "abcdefghijklmnopqrstuwxyz";
		String upperCase = lowerCase.toUpperCase();
		String specialChar = "!\"#¤%&/()=_";
		String legalchars = numbers + lowerCase + upperCase + specialChar;
		String returnString = "";
		returnString += lowerCase.charAt(new Random().nextInt(lowerCase
				.length()));
		returnString += numbers.charAt(new Random().nextInt(numbers.length()));
		returnString += upperCase.charAt(new Random().nextInt(upperCase
				.length()));
		returnString += specialChar.charAt(new Random().nextInt(specialChar
				.length()));
		while (returnString.length() < 7) {
			returnString += legalchars.charAt(new Random().nextInt(legalchars
					.length()));
		}
		return returnString;
	}

	public void setUserId(int id) throws DALException {
		if (id < 1)
			throw new DALException("ID skal være større end 0");
		if (id > 99999999)
			throw new DALException("ID skal være mindre end 99999999");
		this.userId = id;
	}

	public String getUsername() {
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

	public boolean isAdmin() {
		return type == UserType.ADMIN;
	}

	public int getAccesLevel() {
		return type.ordinal();
	}

	public void setUsername(String newname) throws DALException {
		if (newname.length() < 2)
			throw new DALException("Operatør navn er for kort");
		if (newname.length() > 20)
			throw new DALException("Operatør navn er for langt");
		this.username = newname;
	}

	public void setIni(String newini) throws DALException {
		if (newini.length() < 2)
			throw new DALException("Operatør initialer er for få");
		if (newini.length() > 3)
			throw new DALException("Operatør initialer er for mange");
		this.ini = newini;
	}

	public void setCpr(String newcpr) throws DALException {
		if (!newcpr.matches("\\d{10}"))
			throw new DALException("CPR-nummer er ikke 10 cifre");
		this.cpr = newcpr;
	}

	public void setPassword(String newpassword) throws DALException {
		String msg = chkPassWithMsg(newpassword);
		if (msg != null)
			throw new DALException(msg);
		this.password = newpassword;
	}

	public void setAccessLevel(int access) {
		this.type = UserType.values()[access];
	}

	public void setAccessLevel(UserType type) {
		this.type = type;
	}

	public UserType getUserType() {
		return type;
	}

}