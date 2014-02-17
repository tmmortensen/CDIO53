package Boundary;

public interface IBoundary {
	int login(String oprID, String password);
	int operator ();
	int changePassword ();
	int test( String password);
	int exit();
	

	

}
