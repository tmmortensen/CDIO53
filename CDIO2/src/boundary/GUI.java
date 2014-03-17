package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

public class GUI implements IBoundary {

	JFrame f = new JFrame("Vægtsimulator");
	JPanel viewPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel taraPanel = new JPanel();
	EventHandler handler = new EventHandler();

	@Override
	public void run() {
		// define components
		JButton b0 = new JButton("0");
		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		JButton b5 = new JButton("5");
		JButton b6 = new JButton("6");
		JButton b7 = new JButton("7");
		JButton b8 = new JButton("8");
		JButton b9 = new JButton("9");
		JButton clear = new JButton("CLEAR");
		JButton enter = new JButton("ENTER");
		JButton tara = new JButton("<T>");
		JTextPane toweight = new JTextPane();
		JTextPane fromweight = new JTextPane();
		JSpinner incDecWeight = new JSpinner();

		// define attributes on components
		toweight.setBackground(Color.black);
		toweight.setForeground(Color.green);
		fromweight.setBackground(Color.black);
		fromweight.setSelectedTextColor(Color.green);

		// set layoutmanagers
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new GridLayout(4, 4));
		mainPanel.setLayout(new BorderLayout());
		taraPanel.setLayout(new BoxLayout(taraPanel, BoxLayout.X_AXIS));

		// add components
		buttonPanel.add(b0);
		buttonPanel.add(b1);
		buttonPanel.add(b2);
		buttonPanel.add(b3);
		buttonPanel.add(b4);
		buttonPanel.add(b5);
		buttonPanel.add(b6);
		buttonPanel.add(b7);
		buttonPanel.add(b8);
		buttonPanel.add(b9);
		buttonPanel.add(clear);
		buttonPanel.add(enter);

		mainPanel.add(buttonPanel, BorderLayout.EAST);
		mainPanel.add(viewPanel, BorderLayout.CENTER);
		mainPanel.add(incDecWeight, BorderLayout.NORTH);

		viewPanel.add(toweight);
		viewPanel.add(Box.createVerticalStrut(10));
		viewPanel.add(fromweight);
		viewPanel.add(Box.createVerticalStrut(10));
		viewPanel.add(taraPanel);

		taraPanel.add(tara);
		taraPanel.add(incDecWeight);

		// add textPanel to Contentpane
		f.getContentPane().add(mainPanel);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(640, 480);
		f.setVisible(true);
		
		b1.addMouseListener(new MouseListener() {
		    

			@Override
			public void mouseClicked(MouseEvent b1) {
				
				
			}

			@Override
			public void mouseEntered(MouseEvent b1) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent b1) {
			
				
			}

			@Override
			public void mousePressed(MouseEvent b1) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent b1) {
				
			}
			
			
	b2.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent b2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent b2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent b2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent b2) {
			// TODO Auto-generated method stub
			
		}


	
	
					
		    }); 
	
	

	
		 
	
		
		
		   

	

	}

