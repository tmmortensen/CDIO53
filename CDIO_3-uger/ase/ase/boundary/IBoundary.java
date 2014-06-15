package ase.boundary;

/**
 * Interface with methods for all Boundary classes
 * 
 * @author Gruppe 53
 * 
 */
public interface IBoundary extends Runnable {

	/**
	 * 
	 * The method to run the input from the user thread.
	 * 
	 */
	public void run();

	/**
	 * closes resources used by the boundary
	 */
	public void closeResources();

	public int getID();
	
	public boolean sendUsername(String UserName);
	
	public int getProductBatchID();
	
	public void drainWeight();
	
	public double getTara();
	
	public double getNettoWeight();
	
	public void outRaavareID(int raavareID);
	
	public int getRaavareBatchID();
	
	public boolean getQuit();
	
}
