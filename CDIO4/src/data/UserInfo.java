package data;

public class UserInfo {
	public int id;
	public String ini;
	public String name;
	public String cpr;
	public boolean admin;
	public String deleURL;
	public String editURL;
	
	public UserInfo(){
		
	}
	
	public UserInfo(OperatoerDTO operator){
		id = operator.getOprId();
		ini = operator.getIni();
		name = operator.getOprNavn();
		cpr = operator.getCpr();
		admin = operator.isAdmin();
		deleURL = "confirm_delete?id=" + id;
		editURL = "edit_user?id=" + id;
	}

}
