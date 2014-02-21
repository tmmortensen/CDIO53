package data;

public interface IOperatoerDAO extends IDataReadOnly {
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
