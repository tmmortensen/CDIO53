package data;

import java.util.List;

public interface IOperatoerDAO {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;

	public class DALException extends Exception {

		public DALException(String s) {
			super(s);
		}

	}

}
