package admin.data;

public enum UserType {
	ADMIN, PHARMACIST,FOREMAN,OPERATOR,INACTIVE;
	
	public static int getLevel(UserType type){
		return type.ordinal();
	}
	
	public static UserType fromInt(int i){
		return UserType.values()[i];
	}
}
