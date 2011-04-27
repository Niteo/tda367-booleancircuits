package edu.chl.tda367.booleancircuits.test;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import edu.chl.tda367.booleancircuits.view.Canvas;

public class TestCanvas extends javax.swing.JFrame {

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				TestCanvas inst = new TestCanvas();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public TestCanvas() {
		super();
		initGUI();
		this.add(new Canvas().getCanvas());
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
