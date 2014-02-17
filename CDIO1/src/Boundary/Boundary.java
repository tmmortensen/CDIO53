package Boundary;

public class Boundary implements IBoundary {

	@Override
	public int login(String oprID, String password) {
		
		return 0;
	}

	@Override
	public int operatorAdmin() {
		
		return 1;
	}

	@Override
	public int changePassword() {
		
		return 2;
	}

	@Override
	public int test(String password) {
		
		return 3;
	}

	@Override
	public int exit() {
	
		return 4;
	}

	@Override
	public int showmenu() {
		// TODO Auto-generated method stub
		return 5;
	}

}
