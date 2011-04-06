package edu.chl.tda367.booleancircuits.view;

import java.awt.*;
import javax.swing.*;

/**
 * A class that represents a workspace with a panel and potentially several tabs.
 * @author Boel
 *
 */
public class CenterStage {

	private JPanel panel = new JPanel();

	/** Returns an instance of Canvas. */
	public CenterStage() {
		panel.setLayout(new BorderLayout());
		newTab("Workspace 1");
	}
	
	/**
	 * Creates a new tab.
	 * @param s string sets the name of the tab
	 */
	public void newTab(String s){
		panel.add(new Tab(s),BorderLayout.NORTH);
	}

	/**
	 * Returns the panel of the canvas.
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}

}
