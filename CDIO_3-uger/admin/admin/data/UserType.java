package admin.data;

public enum UserType {
	ADMIN, PHARMACIST,FOREMAN,OPERATOR,INACTIVE;
	
	String[] uiNames = { "Administrator",
			"Farmaceut",
			"Værkfører",
			"Operatør",
			"-deaktiveret-"
	};
	
	public static int getLevel(UserType type){
		return type.ordinal();
	}
	
	public static UserType fromInt(int i){
		return UserType.values()[i];
	}
	
	public String uiName(){
		return uiNames[this.ordinal()];
	}
	
	
}
