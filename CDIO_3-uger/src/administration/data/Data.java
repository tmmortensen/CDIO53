package administration.data;

import java.util.*;

public class Data implements IOperatoerDAO {

	Map<Integer, Operator> operators;
	int adminCount;

	public Data() {
		operators = new HashMap<Integer, Operator>();
		adminCount = 0;
		
		// creating the sysadmin
		try {
		createOperatoer(new OperatoerDTO(1,"Sysadmin", "SA", "1234567890", "adminpw",true));
		} catch (Exception e) {}
		
		//TODO remove this when done testing
		createDefaultOperators();
	}

	@Override
	public synchronized OperatoerDTO getOperatoer(int oprId) throws DALException {
		if (oprId < 1 || oprId > 99999999)
			throw new DALException("Id out of bounds");
		Operator operator = operators.get(oprId);
		if (operator == null)
			throw new DALException("Id not found");
		return new OperatoerDTO(oprId, operator);
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> returnlist = new ArrayList<OperatoerDTO>();
		for (Map.Entry<Integer, Operator> entry : operators.entrySet()) {
			returnlist.add(new OperatoerDTO(entry.getKey(), entry.getValue()));
		}
		return returnlist;
	}

	@Override
	public synchronized void createOperatoer(OperatoerDTO opr) throws DALException {
		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result != null) {
			operators.put(opr.getOprId(), result);
			throw new DALException("Operator ID already in use");
		}
		if (opr.isAdmin())
			adminCount++;
	}

	@Override
	public synchronized void updateOperatoer(OperatoerDTO opr) throws DALException {
		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result == null) {
			operators.remove(opr.getOprId());
			throw new DALException("Operator ID does not exist.");
		}
		if (!opr.isAdmin() && result.isAdmin()){
			if(adminCount == 1){
				operators.put(opr.getOprId(), result);
				throw new DALException("Kan ikke fjerne administratorrettighedder fra den sidste administrator");
			} else
				adminCount--;
		}
		if (opr.isAdmin() && !result.isAdmin())
			adminCount++;
	}
	
	@Override
	public synchronized void deleteOperatoer(int id)throws DALException{
		if (id < 1 || id > 99999999)
			throw new DALException("Id out of bounds");
		Operator result = operators.remove(id);
		if (result == null)
			throw new DALException("Operator does not exist");
		if (result.isAdmin()){
			if(adminCount == 1){
				operators.put(id, result);
				throw new DALException("Kan ikke fjerne den sidste administrator");
			} else
				adminCount--;
		}
	}
	
	public void createDefaultOperators(){
		try {
			OperatoerDTO newOp = new OperatoerDTO(11,"Test Guy", "TG", "1234567890", OperatoerDTO.generatePassword());
			createOperatoer(newOp);

			newOp = new OperatoerDTO(12,"Test Guy 2", "TG2", "1234567890", OperatoerDTO.generatePassword());
			createOperatoer(newOp);
		
			newOp = new OperatoerDTO(13,"Test Guy 3", "TG3", "1234567890", OperatoerDTO.generatePassword());
			createOperatoer(newOp);
		
			newOp = new OperatoerDTO(14,"Test Guy 4", "TG4", "1234567890", OperatoerDTO.generatePassword());
			createOperatoer(newOp);
		
			newOp = new OperatoerDTO(10,"Admin", "AM", "1234567890", "adminpw",true);
			createOperatoer(newOp);
		} catch (Exception e) {}
	}
}
