package admin.data;

/**
 * Storage class for info on an operator (not including the id)
 */
public class Operator {
	String oprNavn;
	String ini;
	String cpr;
	String password;
	boolean admin;

	/**
	 * @param oprNavn
	 *            The name of the operator
	 * @param ini
	 *            The operators initials
	 * @param cpr
	 *            The social security number of the operator
	 * @param password
	 *            The operator's password
	 */
	public Operator(String oprNavn, String ini, String cpr, String password) throws DALException {
		this(oprNavn, ini, cpr, password, false);
	}
	
	public Operator(String oprNavn, String ini, String cpr, String password, boolean admin) throws DALException {
		setOprNavn(oprNavn);
		setIni(ini);
		setCpr(cpr);
		setPassword(password);
		setAdmin(admin);
	}


	/**
	 * Creates a new <code>Operator</code> from a <code>OperatoerDTO</code>
	 * -object (the id of the <code>OperatoerDTO</code>-object is discarded)
	 * 
	 * @param op
	 *            the <code>OperatoerDTO</code>-object containing the operator
	 *            info
	 */
	public Operator(Operator op) throws DALException {
		setOprNavn(op.getOprNavn());
		setIni(op.getIni());
		setCpr(op.getCpr());
		setPassword(op.getPassword());
		setAdmin(op.isAdmin());
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
	
	public boolean isAdmin(){
		return admin;
	}

	public void setOprNavn(String newname)  throws DALException {
		if (newname.length() < 2)
			throw new DALException("Operatør navn er for kort");
		if (newname.length() > 20)
			throw new DALException("Operatør navn er for langt");
		this.oprNavn = newname;
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
	
	public void setAdmin(boolean admin){
		this.admin = admin;
	}
}
