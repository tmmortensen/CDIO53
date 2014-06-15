package ase.data;

import admin.data.DALException;

/**
 * Interface with methods for ProgramState classes
 * 
 * @author Gruppe 53
 * 
 */
public interface IDBConnection {

	public String checkName() throws DALException;

	public void checkProduktBatchID(int produktBatchID) throws DALException;

	public void getRaavareID() throws DALException;

	public void checkRaavareBatchID(int id) throws DALException;

	public void saveData() throws DALException;

	public void produktStatus(String afslutte) throws DALException;
}
