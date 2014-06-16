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
	 * Asks the operator for User ID and returns what he entered
	 * 
	 * @return userID
	 * @throws IOException
	 */
	public int getID() throws IOException;

	/**
	 * Gives the Operator his name that correspond to the ID that he entered.
	 * returns true if the Operator presses enter 
	 * returns false if he presses anything but enter
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
	 * Asks the operator to empty the weight and press enter afterwards. 
	 * It won't continue if the operator doesn't press enter
	 * 
	 * @throws IOException
	 */
	public boolean clearWeight() throws IOException;

	/**
	 * Receives the tara weight
	 * Tares the weight.
	 * 
	 * 
	 * @return taraWeight
	 * @throws IOException
	 */
	public double getTara() throws IOException;

	/**
	 * Receives the net weight of the desired commodity
	 * 
	 * @return nettoWeight
	 * @throws IOException
	 */
	public double getNettoWeight(double target,double tolerance) throws IOException;

	/**
	 * Asks the operator to enter the commodity batch ID
	 * 
	 * @return raavareBatchID
	 * @throws IOException
	 */
	public int getRaavareBatchID(int commodityID) throws IOException;

	/**
	 * Asks the operator if he is finished
	 * 
	 * @return true/false
	 * @throws IOException
	 */
	public boolean getQuit() throws IOException;

}
