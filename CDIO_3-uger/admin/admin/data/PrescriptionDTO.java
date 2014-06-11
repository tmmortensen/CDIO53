package admin.data;

public class PrescriptionDTO {

	int id;
	String name;
	
	public PrescriptionDTO() {
		this(0,"");
	}

	public PrescriptionDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int prescriptionId) {
		this.id = prescriptionId;
	}

	public void setName(String prescriptionName) {
		this.name = prescriptionName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
