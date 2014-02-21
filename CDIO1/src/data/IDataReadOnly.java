package data;

import java.util.List;

public interface IDataReadOnly  {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
}
