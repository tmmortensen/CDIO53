package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
				b1.get
				
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
			
					
		    }); 
		b2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent b2) {
			
				
			}

			@Override
			public void mouseEntered(MouseEvent b2) {
			
				
			}

			@Override
			public void mouseExited(MouseEvent b2) {
				
				
			}

			@Override
			public void mousePressed(MouseEvent b2) {
				
				
			}

			@Override
			public void mouseReleased(MouseEvent b2) {
			
				
			}
			
		});
	b3.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b3) {
			
		}

		@Override
		public void mouseEntered(MouseEvent b3) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b3) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b3) {
			
		}

		@Override
		public void mouseReleased(MouseEvent b3) {
			
		}
			
			
		
		
	});
	
		    
	b4.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b4) {
		
			
		}

		@Override
		public void mouseEntered(MouseEvent b4) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b4) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b4) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b4) {
		
			
		}
		
	});
	b5.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b5) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b5) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b5) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b5) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b5) {
						
		}
		
	});
	b6.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b6) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b6) {
		
			
		}

		@Override
		public void mouseExited(MouseEvent b6) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b6) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b6) {
			
			
		}
		
	});
	b7.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b7) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b7) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b7) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b7) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b7) {
			
			
		}
		
	});
	b8.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b8) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b8) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b8) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b8) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b8) {
			
			
		}
		
	});
	b9.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b9) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b9) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b9) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b9) {
			
			
		}

		@Override
		public void mouseReleased(MouseEvent b9) {
			
			
		}
		
	});
	
	b0.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent b0) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent b0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent b0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent b0) {
		
			
		}

		@Override
		public void mouseReleased(MouseEvent b0) {
			
			
		}
		
	});
	
	clear.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent clear) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent clear) {
			
		}

		@Override
		public void mouseExited(MouseEvent clear) {
			
		}

		@Override
		public void mousePressed(MouseEvent clear) {
			
		}

		@Override
		public void mouseReleased(MouseEvent clear) {
			
		}
		
	});
	
	enter.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent enter) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	tara.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent tara) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent tara) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent tara) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent tara) {
			
		}

		@Override
		public void mouseReleased(MouseEvent tara) {
			
		}
		
	});
	    
	b0.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b0) {
			b0.setKeyCode(0);			
		}

		@Override
		public void keyReleased(KeyEvent b0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b0) {
			// TODO Auto-generated method stub
			
		}

		
	});
		

	
	b1.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b1) {
			b1.setKeyCode(1);			
		}

		@Override
		public void keyReleased(KeyEvent b1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b1) {
			// TODO Auto-generated method stub
			
		}

		
		});
		
	
	
	b2.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b2) {
			b2.setKeyCode(2);			
		}

		@Override
		public void keyReleased(KeyEvent b2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b2) {
			// TODO Auto-generated method stub
			
		}
		
	});

		
		
	
	b3.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b3) {
			b3.setKeyCode(3);
			
		}

		@Override
		public void keyReleased(KeyEvent b3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b3) {
			// TODO Auto-generated method stub
			
		}
		
	});

		
	
	b4.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b4) {
			b4.setKeyCode(4);
			
		}

		@Override
		public void keyReleased(KeyEvent b4) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b4) {
			// TODO Auto-generated method stub
			
		}
		
	});

		
	
	b5.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b5) {
			b5.setKeyCode(5);
			
		}

		@Override
		public void keyReleased(KeyEvent b5) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b5) {
			
		}
			
		});

	
	
	
	b6.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b6) {
			b6.setKeyCode(6);
			
		}

		@Override
		public void keyReleased(KeyEvent b6) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b6) {
			// TODO Auto-generated method stub
			
		}

	
		});
		

	
	b7.addKeyListener(new KeyListener() {

		

		@Override
		public void keyPressed(KeyEvent b7) {
			b7.setKeyCode(7);
		}

		@Override
		public void keyReleased(KeyEvent b7) {
			b7.setKeyCode(7);
			
		}

		@Override
		public void keyTyped(KeyEvent b7) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	b8.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b8) {
			b8.setKeyCode(8);
			
		}

		@Override
		public void keyReleased(KeyEvent b8) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b8) {
			// TODO Auto-generated method stub
			
		}

		
	});
	
	
	b9.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent b9) {
			b9.setKeyCode(9);
			
		}

		@Override
		public void keyReleased(KeyEvent b9) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent b9) {
			// TODO Auto-generated method stub
			
		}
			
		});
		

	clear.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent clear) {
			
			
		}

		@Override
		public void keyReleased(KeyEvent clear) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent clear) {
			// TODO Auto-generated method stub
			
		}

			
		});
		
	
	
	enter.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent enter) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent enter) {
			// TODO Auto-generated method stub
			
		}


			
		});
		

	tara.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	});

	








	
	    
	    
	    
	    
	    
	       
		

	
	

	
		 
	
		
		
		   

	

	}
}

