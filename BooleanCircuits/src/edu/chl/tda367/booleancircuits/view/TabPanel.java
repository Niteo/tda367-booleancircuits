package edu.chl.tda367.booleancircuits.view;

import java.awt.Dimension;

import javax.swing.*;

public class TabPanel {

	private JButton closeButton = new JButton();
	private JLabel titleLabel = new JLabel();
	private JPanel panel = new JPanel();
	private ImageIcon closeIcon = new ImageIcon("resources/icons/cross-icon.png");

	/**
	 * Returns an instance of TabPanel.
	 * @param title
	 */
	public TabPanel(String title) {
		closeButton.setPreferredSize((new Dimension(20, 20)));
		closeButton.setIcon(closeIcon);
		titleLabel.setText(title);
		panel.add(titleLabel);
		panel.add(closeButton);
	}
	
	/**
	 * Sets the title of the tab panel.
	 * @param title
	 */
	public void setTabPanelTitle(String title){
		titleLabel.setText(title);
	}

	/**
	 * Returns the tab panel.
	 * @return panel
	 */
	public JPanel getTabPanel() {
		return panel;
	}

}
