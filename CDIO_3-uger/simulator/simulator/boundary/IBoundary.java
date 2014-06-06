package simulator.boundary;

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

}
