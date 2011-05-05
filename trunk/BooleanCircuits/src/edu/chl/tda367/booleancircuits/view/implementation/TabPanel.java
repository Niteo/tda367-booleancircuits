package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Dimension;

import javax.swing.*;

import edu.chl.tda367.booleancircuits.view.ITabPanel;

/**
 * A graphical class containing a button and a label.
 * 
 * @author Boel
 * 
 */

public class TabPanel extends JPanel implements ITabPanel {

	private JButton closeButton = new JButton();
	private JLabel titleLabel = new JLabel();

	/**
	 * Returns an instance of TabPanel.
	 * 
	 * @param title
	 */
	public TabPanel(String title) {
		closeButton.setPreferredSize((new Dimension(20, 20)));
		titleLabel.setText(title);
		add(titleLabel);
		add(closeButton);
		setOpaque(false);
	}

	public void setTabPanelTitle(String title) {
		titleLabel.setText(title);
	}

	public JButton getCloseButton() {
		return closeButton;
	}

}
