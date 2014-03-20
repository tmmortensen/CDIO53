package boundary;

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

import data.IProgramState;

public class GUI implements IBoundary {

	private long lastRefresh = 0;
	IProgramState programState;

	JFrame f = new JFrame("Vægtsimulator");
	JPanel viewPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel taraPanel = new JPanel();
	JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, clear, enter, tara;
	JTextPane toweight, fromweight;
	// SpinnerListModel enterWeightString = new SpinnerListModel();
	JSpinner enterWeight = new JSpinner();
	Eventhandler handler = new Eventhandler();

	public GUI(IProgramState programState) {
		this.programState = programState;

		// define components
		b0 = new JButton("0");
		b0.setActionCommand("0");
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
		clear.setToolTipText("Push to clear");

		enter = new JButton("ENTER");
		enter.setToolTipText("Push to send");

		tara = new JButton("<T>");
		tara.setToolTipText("Push to tara weight");

		toweight = new JTextPane();

		fromweight = new JTextPane();

		enterWeight = new JSpinner(new SpinnerNumberModel(0, 0,
				Integer.MAX_VALUE, 1));
		enterWeight.setToolTipText("Enter weight here");

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
		mainPanel.add(enterWeight, BorderLayout.NORTH);

		viewPanel.add(toweight);
		viewPanel.add(Box.createVerticalStrut(10));
		viewPanel.add(fromweight);
		viewPanel.add(Box.createVerticalStrut(10));
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
		while (programState.isRunning()) {
			if (programState.hasDisplayUpdated(lastRefresh)) {
				lastRefresh = System.currentTimeMillis();
				printGui();
			}
			try {
				this.wait(100);
			} catch (Exception e) {
			}
		}
	}

	public void printGui() {

		String adress = "null";
		try {
			adress = programState.getAddress().getHostAddress();
		} catch (Exception e) {
		}

		// define attributes on components
		toweight.setBackground(Color.black);
		toweight.setForeground(Color.green);
		toweight.setText("*************************************************\n"
				+ "Netto: "
				+ programState.getNet()
				+ " kg\n"
				+ "Instruktionsdisplay: "
				+ programState.getDisplayText()
				+ "\n*************************************************\n\n"
				+ "Debug info: \n"
				+ "Hooked up to "
				+ adress
				+ "\nBrutto: "
				+ programState.getGross()
				+ " kg"
				+ "\tStreng modtaget: "
				+ programState.getNetString()
				+ "\n\nDenne vægt simulator lytter på ordrene "
				+ "\nD, DN, S, T, B, Q "
				+ "På kommunikationsporten\n"
				+ "******\n"
				+ "Tast T for tara\n"
				+ "Tast værdi nederst for ny brutto (svarende til at belastningen på vægt ændres)\n"
				+ "Klik på \"x\" i hjørnet for at afslutte program program\n");
		fromweight.setBackground(Color.black);
		fromweight.setForeground(Color.green);
		fromweight.setText("");
		enterWeight.setToolTipText("Enter brutto weight here");

		// add textPanel to Contentpane
		f.getContentPane().add(mainPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set to SVGA res
		f.setSize(800, 600);
		f.setVisible(true);

	}

	private class Eventhandler implements ActionListener, ChangeListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String inputtext = fromweight.getText();

			if (e.getSource() == b0) {
				fromweight.setText(inputtext + b0.getText());
			} else if (e.getSource() == b1) {
				fromweight.setText(inputtext + b1.getText());
			} else if (e.getSource() == b2) {
				fromweight.setText(inputtext + b2.getText());
			} else if (e.getSource() == b3) {
				fromweight.setText(inputtext + b3.getText());
			} else if (e.getSource() == b4) {
				fromweight.setText(inputtext + b4.getText());
			} else if (e.getSource() == b5) {
				fromweight.setText(inputtext + b5.getText());
			} else if (e.getSource() == b6) {
				fromweight.setText(inputtext + b6.getText());
			} else if (e.getSource() == b7) {
				fromweight.setText(inputtext + b7.getText());
			} else if (e.getSource() == b8) {
				fromweight.setText(inputtext + b8.getText());
			} else if (e.getSource() == b9) {
				fromweight.setText(inputtext + b9.getText());
			} else if (e.getSource() == clear) {
				fromweight.setText("");
				inputtext = fromweight.getText();
			} else if (e.getSource().equals(tara)) {
				programState.tare();
			} else if (e.getSource().equals(enter)) {
				programState.setGross(Double.parseDouble(inputtext));
			}

		}

		@Override
		public void stateChanged(ChangeEvent ce) {

			if (ce.getSource() == enterWeight) {
				String userInput = enterWeight.getValue().toString();
				programState.setGross(Double.parseDouble(userInput));
				fromweight.setText(userInput);
			}

		}
	}
}
