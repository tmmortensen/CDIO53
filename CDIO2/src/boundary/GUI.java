package boundary;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Vægtsimulator");
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		GridLayout grid = new GridLayout(4, 4);
		frame.setPreferredSize(new Dimension(500, 500));

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
		JButton b10 = new JButton("CLEAR");
		JButton b11 = new JButton("ENTER");

		frame.setVisible(true);
		panel.setLayout(grid);
		frame.pack();
		panel2.setLayout(null);

		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		panel.add(b9);
		panel.add(b10);
		panel.add(b0);
		panel.add(b11);

		frame.setContentPane(panel);

		// JButton tara = new JButton("TARA");
		// JTextArea weight = new JTextArea(10, 2);
		//
		// panel2.add(tara);
		// panel2.add(panel, BorderLayout.EAST);
		// panel2.add(weight, BorderLayout.WEST);
		//
		// frame.getContentPane().add(panel2);
	}
}
