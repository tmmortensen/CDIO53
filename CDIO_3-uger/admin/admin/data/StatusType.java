package admin.data;

public enum StatusType {
	NEW, IN_PRODUCTION,PAUSED,FINISHED;
	
	String[] uiNames = { "Oprettet",
			"Under Produktion",
			"På Pause",
			"Afluttet",
	};
	
	public static int getValue(StatusType type){
		return type.ordinal();
	}
	
	public static StatusType fromInt(int i){
		return StatusType.values()[i];
	}
	
	public String uiName(){
		return uiNames[this.ordinal()];
	}
	
	
}
