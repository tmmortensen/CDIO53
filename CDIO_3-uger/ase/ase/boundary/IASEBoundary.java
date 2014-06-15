package ase.boundary;

import java.io.IOException;

/**
 * Interface with methods for all Boundary classes
 * 
 * @author Gruppe 53
 * 
 */
public interface IASEBoundary {

	/**
	 * 
	 * asks the operator for ID and returns what he entered to the controller
	 * 
	 * @return userID
	 * @throws IOException
	 */
	public int getID() throws IOException;

	/**
	 * Gives the Operators name that correspond to the ID that he gave. returns
	 * true if the Operator presses enter and returns false if he presses
	 * anything but enter
	 * 
	 * @param UserName
	 * @return true/false
	 * @throws IOException
	 */

	public boolean sendUsername(String UserName) throws IOException;

	/**
	 * Asks the operator to type the productbatch ID
	 * 
	 * @return productBatchID
	 * @throws IOException
	 */

	public int getProductBatchID() throws IOException;

	/**
	 * Asks the operator to empty the weight and press enter afterwards won't
	 * continue if the operator doesn't press enter
	 * 
	 * @throws IOException
	 */
	public void drainWeight() throws IOException;

	/**
	 * Receives the tara weight and sends it to the controller
	 * 
	 * 
	 * @return taraWeight
	 * @throws IOException
	 */
	public double getTara() throws IOException;

	/**
	 * Receives the net weight and sends it to the controller
	 * 
	 * @return nettoWeight
	 * @throws IOException
	 */
	public double getNettoWeight() throws IOException;

	/**
	 * 
	 * Tells the operator which commodity that shall be the next to weight
	 * 
	 * @param raavareID
	 * @throws IOException
	 */
	public void outRaavareID(int raavareID) throws IOException;

	/**
	 * Asks the operator to enter the commoditybatch ID
	 * 
	 * @return raavareBatchID
	 * @throws IOException
	 */
	public int getRaavareBatchID() throws IOException;

	/**
	 * Asks the operator if he is finished
	 * 
	 * @return true/false
	 * @throws IOException
	 */
	public boolean getQuit() throws IOException;

}
