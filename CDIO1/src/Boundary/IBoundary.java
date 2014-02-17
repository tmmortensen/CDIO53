package Boundary;

public interface IBoundary {
	String login(String oprid, String password);
	int operator ();
	int changePassword ();
	int test( String password);
	int exit();

	

}
