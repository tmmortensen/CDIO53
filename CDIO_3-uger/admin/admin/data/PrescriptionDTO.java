package admin.data;

public class PrescriptionDTO {

	private int id;
	private String name;

	/**
	 * Constructor with no parameters and no error checks
	 */
	public PrescriptionDTO() {
		id = 0;
		name = "";
	}

	/**
	 * Constructor with parameters from set methods and thus error checks
	 * 
	 * @param id
	 * @param name
	 * @throws DALException
	 */
	public PrescriptionDTO(int id, String name) throws DALException {
		setId(id);
		setName(name);
	}

	public void setId(int prescriptionId) throws DALException {
		if(prescriptionId<=0){
			throw new DALException("Recept id'et skal være større end 0");
		}
		this.id = prescriptionId;
	}

	public void setName(String prescriptionName) throws DALException {
		this.name = prescriptionName;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
