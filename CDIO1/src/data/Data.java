package data;

import java.util.*;

public class Data implements IOperatoerDAO {

	Map<Integer, Operator> operators;

	public Data() {
		operators = new HashMap<Integer, Operator>();

	}

	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		if (oprId < 10 || oprId > 99)
			throw new IOperatoerDAO.DALException("Id out of bounds");
		Operator operator = operators.get(oprId);
		if (operator == null)
			throw new IOperatoerDAO.DALException("Id not found");
		return new OperatoerDTO(oprId, operator);
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		// Iterator<Map.Entry<Integer, Operator>> iOperator =
		// operators.entrySet().iterator();
		List<OperatoerDTO> returnlist = new ArrayList<OperatoerDTO>();
		for (Map.Entry<Integer, Operator> entry : operators.entrySet()) {
			returnlist.add(new OperatoerDTO(entry.getKey(), entry.getValue()));
		}

		return returnlist;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result != null) {
			operators.put(opr.getOprId(), result);
			throw new DALException("Operator ID already in use");

		}
	}

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Operator result = operators.put(opr.getOprId(), new Operator(opr));
		if (result == null) {
			operators.remove(opr.getOprId());
			throw new DALException("Operator ID does not exist.");
		}
	}

}
