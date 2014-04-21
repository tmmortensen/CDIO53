package client;

public class Main {

	public static void main(String[] args) {
		TCPConnect tcpConnection = new TCPConnect(); 
		try{
			tcpConnection.run();
		}
		catch(Exception e){
			System.out.println("Ingen forbindelse til vægten.");
		}
	}

}
