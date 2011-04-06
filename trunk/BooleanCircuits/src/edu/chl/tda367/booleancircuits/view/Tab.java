package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * A class to represent a tab.
 * @author Boel
 *
 */
public final class Tab extends JPanel {

	private JButton closeButton = new JButton("X");
	private JLabel label = new JLabel();

	/**
	 * Returns an instance of tab.
	 * @param s string to set the text
	 */
	public Tab(String s, Color c) {
		setBackground(c);
		setName(s);
		add(label);
		add(closeButton);
		setBorder(new LineBorder(Color.black, 1));
	}

	/**
	 * Sets the name of the tab.
	 */
	public void setName(String s) {
		label.setText(s);
	}
}
