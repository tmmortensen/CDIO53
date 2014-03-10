package boundary;
import java.awt.*;
import java.awt.Event;
import javax.swing.*;

public class GUI implements IBoundary  {

	@Override
	public void run() { 
		 JFrame frame = new JFrame("HelloWorldSwing");
		final JLabel label = new JLabel("Hello World"); 
		frame.getContentPane().add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.pack();
		frame.setVisible(true);
		
	}
	

}
