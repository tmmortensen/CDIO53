package admin.data;

import java.util.*;

public class DataOld implements IUserDAO {

	Map<Integer, Operator> operators;
	int adminCount;

	public DataOld() {
		operators = new HashMap<Integer, Operator>();
		adminCount = 0;
		
		// creating the sysadmin
		try {
		createUser(new UserDTO(1,"Sysadmin", "SA", "1234567890", "adminpw",0));
		} catch (Exception e) {}
		
		//TODO remove this when done testing
		createDefaultOperators();
	}

	@Override
	public synchronized UserDTO getUser(int oprId) throws DALException {
		if (oprId < 1 || oprId > 99999999)
			throw new DALException("Id out of bounds");
		Operator operator = operators.get(oprId);
		if (operator == null)
			throw new DALException("Id not found");
		return new UserDTO(oprId, operator);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> returnlist = new ArrayList<UserDTO>();
		for (Map.Entry<Integer, Operator> entry : operators.entrySet()) {
			returnlist.add(new UserDTO(entry.getKey(), entry.getValue()));
		}
		return returnlist;
	}

	@Override
	public synchronized void createUser(UserDTO opr) throws DALException {
		Operator result = operators.put(opr.getUserId(), new Operator(opr));
		if (result != null) {
			operators.put(opr.getUserId(), result);
			throw new DALException("Operator ID already in use");
		}
		if (opr.isAdmin())
			adminCount++;
	}

	@Override
	public synchronized void updateUser(UserDTO opr) throws DALException {
		Operator result = operators.put(opr.getUserId(), new Operator(opr));
		if (result == null) {
			operators.remove(opr.getUserId());
			throw new DALException("Operator ID does not exist.");
		}
		if (!opr.isAdmin() && result.isAdmin()){
			if(adminCount == 1){
				operators.put(opr.getUserId(), result);
				throw new DALException("Kan ikke fjerne administratorrettighedder fra den sidste administrator");
			} else
				adminCount--;
		}
		if (opr.isAdmin() && !result.isAdmin())
			adminCount++;
	}
	
	@Override
	public synchronized void deleteUser(int id)throws DALException{
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
			UserDTO newOp = new UserDTO(11,"Test Guy", "TG", "1234567890", UserDTO.generatePassword());
			createUser(newOp);

			newOp = new UserDTO(12,"Test Guy 2", "TG2", "1234567890", UserDTO.generatePassword());
			createUser(newOp);
		
			newOp = new UserDTO(13,"Test Guy 3", "TG3", "1234567890", UserDTO.generatePassword());
			createUser(newOp);
		
			newOp = new UserDTO(14,"Test Guy 4", "TG4", "1234567890", UserDTO.generatePassword());
			createUser(newOp);
		
			newOp = new UserDTO(10,"Admin", "AM", "1234567890", "adminpw",0);
			createUser(newOp);
		} catch (Exception e) {}
	}
}
