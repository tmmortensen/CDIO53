package Boundary;

public interface IBoundary {
	public String[] login();
	public String getString(String msg);
	public int getInt(String msg);
	public int menu(String[] options, String header);
	public void showStringMessage (String msg);
	
}
