package data;

public class Operator {
	String oprNavn;
	String ini;
	String cpr;
	String password;

	
	
	public Operator(String oprNavn, String ini, String cpr, String password){
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	}
	
	public Operator(OperatoerDTO op){
		this.oprNavn = op.getOprNavn();
		this.ini = op.getIni();
		this.cpr = op.getCpr();
		this.password = op.getPassword();
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

	public void setOprNavn(String newname) {
		this.oprNavn = newname;
	}

	public void setIni(String newini) {
		this.ini = newini;

	}

	public void setCpr(String newcpr) {
		this.cpr = newcpr;
	}

	public void setPassword(String newpassword) {
		this.password = newpassword;
	}
	
}
