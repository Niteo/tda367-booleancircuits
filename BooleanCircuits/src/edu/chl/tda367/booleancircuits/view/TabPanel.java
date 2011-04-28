package edu.chl.tda367.booleancircuits.view;

import java.awt.Dimension;

import javax.swing.*;

public class TabPanel extends JPanel{

	private JButton closeButton = new JButton();
	private JLabel titleLabel = new JLabel();

	/**
	 * Returns an instance of TabPanel.
	 * @param title
	 */
	public TabPanel(String title) {
		closeButton.setPreferredSize((new Dimension(20, 20)));
		titleLabel.setText(title);
		add(titleLabel);
		add(closeButton);
		setOpaque(false);
	}
	
	/**
	 * Sets the title of the tab panel.
	 * @param title
	 */
	public void setTabPanelTitle(String title){
		titleLabel.setText(title);
	}
	
	/**
	 * Returns the close button
	 * @return JButton
	 */
	public JButton getCloseButton() {
		return closeButton;
	}

}
