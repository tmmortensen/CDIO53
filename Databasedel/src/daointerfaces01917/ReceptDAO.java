package daointerfaces01917;

import java.util.List;

import dto01917.ReceptDTO;

public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	List<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
