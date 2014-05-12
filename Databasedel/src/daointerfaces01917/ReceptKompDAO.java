package daointerfaces01917;

import java.util.List;

import dto01917.ReceptKompDTO;

public interface ReceptKompDAO {
	ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException;
	List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException;
	List<ReceptKompDTO> getReceptKompList() throws DALException;
		void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
	void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
}
