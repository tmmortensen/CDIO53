package data;

public class OperatoerDTO {
		OperatoerDTO getOperatoer(int oprId) throws DALException;
		List<OperatoerDTO> getOperatoerList() throws DALException;
		void createOperatoer(OperatoerDTO opr) throws DALException;
		void updateOperatoer(OperatoerDTO opr) throws DALException;
		
		
}
