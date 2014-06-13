package simulator.boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simulator.data.IProgramState;

/**
 * Class to create the Graphical User Interface
 * 
 * @author Gruppe 53
 * 
 */
public class GUI implements IBoundary {

	private long lastRefresh = 0;
	IProgramState programState;
	int buttonCounter0 = 0;
	int buttonCounter1 = 0;
	int buttonCounter2 = 0;
	int buttonCounter3 = 0;
	int buttonCounter4 = 0;
	int buttonCounter5 = 0;
	int buttonCounter6 = 0;
	int buttonCounter7 = 0;
	int buttonCounter8 = 0;
	int buttonCounter9 = 0;

	// set up frames buttons and panels
	JFrame f = new JFrame("Vægtsimulator");
	JPanel viewPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel taraPanel = new JPanel();
	JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, clear, enter, tara, b00;
	JTextPane toweight, fromweight, digits;
	JSpinner enterWeight = new JSpinner();
	Eventhandler handler = new Eventhandler();

	@Override
	public void closeResources() {
		f.dispose();
	}

	/**
	 * Constructor that creates GUI components and their attributes
	 * 
	 * @param programState
	 *            is used when a client changes the program state
	 */
	public GUI(IProgramState programState) {
		this.programState = programState;

		// define components
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		clear = new JButton("CLEAR");
		enter = new JButton("ENTER");
		tara = new JButton("<T>");
		toweight = new JTextPane();
		fromweight = new JTextPane();
		digits = new JTextPane();
		enterWeight = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 100000.0,
				1.0));

		// define attributes on components
		clear.setToolTipText("Push to clear");
		enter.setToolTipText("Push to send");
		tara.setToolTipText("Push to tara weight");
		toweight.setBackground(Color.black);
		toweight.setForeground(Color.green);
		toweight.setFocusable(false);
		toweight.setToolTipText("Upper display");
		fromweight.setBackground(Color.black);
		fromweight.setForeground(Color.green);
		fromweight.setToolTipText("Bottom display");
		fromweight.setFocusable(false);
		digits.setBackground(Color.black);
		digits.setForeground(Color.green);
		digits.setFocusable(false);
		digits.setToolTipText("Displays input from numpad on the weight");
		enterWeight.setToolTipText("Enter brutto weight here");

		// set layoutmanagers
		viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));
		buttonPanel.setLayout(new GridLayout(4, 4));
		mainPanel.setLayout(new BorderLayout());
		taraPanel.setLayout(new BoxLayout(taraPanel, BoxLayout.X_AXIS));

		// add components
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
		buttonPanel.add(b0);
		buttonPanel.add(enter);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		mainPanel.add(viewPanel, BorderLayout.CENTER);
		mainPanel.add(enterWeight, BorderLayout.NORTH);
		viewPanel.add(digits);
		viewPanel.add(Box.createVerticalStrut(2));
		viewPanel.add(toweight);
		viewPanel.add(Box.createVerticalStrut(2));
		viewPanel.add(fromweight);
		viewPanel.add(Box.createVerticalStrut(2));
		viewPanel.add(taraPanel);
		taraPanel.add(tara);
		taraPanel.add(enterWeight);

		// add eventhandling
		b0.addActionListener(handler);
		b1.addActionListener(handler);
		b2.addActionListener(handler);
		b3.addActionListener(handler);
		b4.addActionListener(handler);
		b5.addActionListener(handler);
		b6.addActionListener(handler);
		b7.addActionListener(handler);
		b8.addActionListener(handler);
		b9.addActionListener(handler);
		enter.addActionListener(handler);
		clear.addActionListener(handler);
		tara.addActionListener(handler);
		enterWeight.addChangeListener(handler);

	}

	@Override
	public void run() {

		f.setVisible(true);

		while (programState.isRunning()) {
			if (programState.hasDisplayUpdated(lastRefresh - 10)) {
				lastRefresh = System.currentTimeMillis();
				printGui();
			}
			try {
				this.wait(100);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Method to print the GUI on screen
	 */
	public void printGui() {

		String adress = "null";
		try {
			adress = programState.getAddress().getHostAddress();
		} catch (Exception e) {
		}

		toweight.setText("*************************************************\n"
				+ "Netto: " + programState.getNet() + " kg\n"
				+ "Instruktionsdisplay: " + programState.getDisplayText()
				+ "\n*************************************************\n\n"
				+ "Debug info: \n" + "Hooked up to " + adress + "\nBrutto: "
				+ programState.getGross() + " kg" + "\nStreng modtaget: "
				+ programState.getNetString()
				+ "\n\nDenne vægt simulator lytter på ordrene "
				+ "\nD, DW, S, T, B, Q , P111 og RM20_8 "
				+ "\nPå kommunikationsporten\n" + "******\n"
				+ "Tast T for tara\n"
				+ "Tast værdi nederst for ny brutto (svarende til at "
				+ "belastningen på vægt ændres)\n"
				+ "Klik på \"x\" i hjørnet for at afslutte program program\n");
		fromweight.setText(programState.getBotDisplay());
		digits.setText("");

		// add mainPanel to Contentpane
		f.getContentPane().add(mainPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600); // set to SVGA res
		f.setVisible(true);
		f.repaint();

	}

	/**
	 * Class to handle events in GUI
	 * 
	 * @author Gruppe 53
	 * 
	 */
	private class Eventhandler implements ActionListener, ChangeListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String inputtext = digits.getText();

			if (e.getSource() == b0) {

				buttonCounter0++;

				switch (buttonCounter0) {
				case 1:
					digits.setText(inputtext + b0.getText());
					break;

				case 2:
					if (e.getSource() == b0) {
						digits.setText(" ");
					} else
						digits.setText(".");
					break;
				default:
					buttonCounter0 = 0;
				}
			} else if (e.getSource() == b1) {
				buttonCounter1++;
				switch (buttonCounter1) {
				case 1:
					digits.setText(inputtext + b1.getText());
					break;
				case 2:
					if (e.getSource() == b1) {
						digits.setText("/");
					} else
						digits.setText(inputtext + "/");
					break;
				case 3:
					if (e.getSource() == b1) {
						digits.setText("&");
					} else
						digits.setText(inputtext + "&");
					break;
				default:
					buttonCounter1 = 0;
				}

			} else if (e.getSource() == b2) {
				buttonCounter2++;
				switch (buttonCounter2) {
				case 1:
					digits.setText(inputtext + b2.getText());
					break;
				case 2:
					if (e.getSource() == b2) {
						digits.setText("a");
					} else
						digits.setText(inputtext + "a");
					break;
				case 3:
					if (e.getSource() == b2) {
						digits.setText("b");
					} else
						digits.setText(inputtext + "b");
					break;
				case 4:
					if (e.getSource() == b2) {
						digits.setText("c ");
					} else
						digits.setText(inputtext + "c");
					break;
				default:
					buttonCounter2 = 0;

				}

			} else if (e.getSource() == b3) {
				buttonCounter3++;
				switch (buttonCounter3) {
				case 1:
					digits.setText(inputtext + b3.getText());
					break;
				case 2:
					if (e.getSource() == b3) {
						digits.setText("d");
					} else
						digits.setText(inputtext + "d");
					break;
				case 3:
					if (e.getSource() == b3) {
						digits.setText("e");
					} else
						digits.setText(inputtext + "e");
					break;
				case 4:
					if (e.getSource() == b3) {
						digits.setText("f");
					} else
						digits.setText(inputtext + "f");
					break;
				default:
					buttonCounter3 = 0;

				}

			} else if (e.getSource() == b4) {
				buttonCounter4++;
				switch (buttonCounter4) {
				case 1:
					digits.setText(inputtext + b4.getText());
					break;
				case 2:
					if (e.getSource() == b4) {
						digits.setText("g");
					} else
						digits.setText(inputtext + "g");
					break;
				case 3:
					if (e.getSource() == b4) {
						digits.setText("h");
					} else
						digits.setText(inputtext + "h");
					break;
				case 4:
					if (e.getSource() == b4) {
						digits.setText("i");
					} else
						digits.setText(inputtext + "i");
					break;
				default:
					buttonCounter4 = 0;

				}

			} else if (e.getSource() == b5) {
				buttonCounter5++;
				switch (buttonCounter5) {
				case 1:
					digits.setText(inputtext + b5.getText());
					break;
				case 2:
					if (e.getSource() == b5) {
						digits.setText("j");
					} else
						digits.setText(inputtext + "j");
					break;
				case 3:
					if (e.getSource() == b5) {
						digits.setText("k");
					} else
						digits.setText(inputtext + "k");
					break;
				case 4:
					if (e.getSource() == b5) {
						digits.setText("l");
					} else
						digits.setText(inputtext + "l");
					break;
				default:
					buttonCounter5 = 0;

				}
			} else if (e.getSource() == b6) {
				if (e.getSource() != b6)
					;

				buttonCounter6++;
				switch (buttonCounter6) {
				case 1:
					digits.setText(inputtext + b6.getText());
					break;
				case 2:
					if (e.getSource() == b6) {
						digits.setText("m");
					} else
						digits.setText(inputtext + "m");
					break;
				case 3:
					if (e.getSource() == b6) {
						digits.setText("n");
					} else
						digits.setText(inputtext + "n");
					break;
				case 4:
					if (e.getSource() == b6) {
						digits.setText("o");
					} else
						digits.setText(inputtext + "o");
					break;
				default:
					buttonCounter6 = 0;

				}
			} else if (e.getSource() == b7) {

				buttonCounter7++;
				switch (buttonCounter7) {
				case 1:
					digits.setText(inputtext + b7.getText());
					break;

				case 2:
					if (e.getSource() == b7) {
						digits.setText("p");
					} else
						digits.setText(inputtext + "p");
					break;
				case 3:
					if (e.getSource() == b7) {
						digits.setText("q");
					} else
						digits.setText(inputtext + "q");
					break;
				case 4:
					if (e.getSource() == b7) {
						digits.setText("r");
					} else
						digits.setText(inputtext + "r");
					break;
				case 5:
					if (e.getSource() == b7) {
						digits.setText("s");
					} else
						digits.setText(inputtext + "s");
				default:
					buttonCounter7 = 0;

				}

			} else if (e.getSource() == b8) {
				buttonCounter8++;
				switch (buttonCounter8) {
				case 1:
					digits.setText(inputtext + b8.getText());
					break;
				case 2:
					if (e.getSource() == b8) {
						digits.setText("t");
					} else
						digits.setText(inputtext + "t");
					break;
				case 3:
					if (e.getSource() == b8) {
						digits.setText("u");
					} else
						digits.setText(inputtext + "u");
					break;
				case 4:
					if (e.getSource() == b8) {
						digits.setText("v");
					} else
						digits.setText(inputtext + "v");
					break;
				default:
					buttonCounter8 = 0;

				}

			} else if (e.getSource() == b9) {
				buttonCounter9++;
				switch (buttonCounter9) {
				case 1:
					digits.setText(inputtext + b9.getText());
					break;
				case 2:
					if (e.getSource() == b9) {
						digits.setText("w");
					} else
						digits.setText(inputtext + "w");
					break;
				case 3:
					if (e.getSource() == b9) {
						digits.setText("x");
					} else
						digits.setText(inputtext + "x");
					break;
				case 4:
					if (e.getSource() == b9) {
						digits.setText(" y");
					} else
						digits.setText(inputtext + "y");
					break;
				case 5:
					if (e.getSource() == b9) {
						digits.setText("z");
					} else
						digits.setText(inputtext + "z");
					break;
				default:
					buttonCounter9 = 0;

				}
			} else if (e.getSource() == clear) {
				digits.setText("");
				inputtext = digits.getText();
			} else if (e.getSource() == tara) {
				programState.tare();
			} else if (e.getSource().equals(enter)) {
				programState.setUserInput(inputtext);
				digits.setText("");
				inputtext = digits.getText();

			}
			if (e.getSource() != b0) {
				buttonCounter0 = 0;
			}
			if (e.getSource() != b1) {
				buttonCounter1 = 0;
			}
			if (e.getSource() != b2) {
				buttonCounter2 = 0;
			}
			if (e.getSource() != b3) {
				buttonCounter3 = 0;
			}
			if (e.getSource() != b4) {
				buttonCounter4 = 0;
			}
			if (e.getSource() != b5) {
				buttonCounter5 = 0;
			}
			if (e.getSource() != b6) {
				buttonCounter6 = 0;
			}
			if (e.getSource() != b7) {
				buttonCounter7 = 0;
				if (e.getSource() != b8) {
					buttonCounter8 = 0;
				}
				if (e.getSource() != b9) {
					buttonCounter9 = 0;
				}

			}

		}

		@Override
		public void stateChanged(ChangeEvent ce) {

			if (ce.getSource() == enterWeight) {

				programState.setGross(Double.parseDouble(enterWeight.getValue()
						.toString()));
			}

		}
	}
}
