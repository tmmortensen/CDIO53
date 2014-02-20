package data;


public class OperatoerDTO {
	int oprId;
	String oprNavn;
	String ini;
	String cpr;
	String password;

	public OperatoerDTO(int oprId, Operator o) {
		this.oprId = oprId;
		this.oprNavn = o.getOprNavn();
		this.ini = o.getIni();
		this.cpr = o.getCpr();
		this.password = o.getPassword();
	}
	
	public int getOprId(){
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


}