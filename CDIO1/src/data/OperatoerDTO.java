package data;

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