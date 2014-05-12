package daointerfaces01917;

import java.util.List;

import dto01917.RaavareDTO;

public interface RaavareDAO {
	RaavareDTO getRaavare(int raavareId) throws DALException;
	List<RaavareDTO> getRaavareList() throws DALException;
	void createRaavare(RaavareDTO raavare) throws DALException;
	void updateRaavare(RaavareDTO raavare) throws DALException;
}
