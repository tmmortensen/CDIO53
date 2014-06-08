package admin.data;

public class UserInfo {
	public int id;
	public String ini;
	public String name;
	public String cpr;
	public UserType access;
	public String deleURL;
	public String editURL;
	
	public UserInfo(){
		
	}
	
	public UserInfo(UserDTO operator){
		id = operator.getUserId();
		ini = operator.getIni();
		name = operator.getUsername();
		cpr = operator.getCpr();
		access = UserType.fromInt(operator.getAccesLevel());
		deleURL = "user_confirm_delete?id=" + id;
		editURL = "user_edit_or_new_user?id=" + id;
	}

}
