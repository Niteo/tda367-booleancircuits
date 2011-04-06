package edu.chl.tda367.booleancircuits.view;

import java.awt.*;
import javax.swing.*;

public class Canvas {

	private JPanel panel = new JPanel();

	/** Returns an instance of Canvas */
	public Canvas() {
		panel.setLayout(new BorderLayout());
	}

	/**
	 * Returns the panel of the canvas
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}

}
