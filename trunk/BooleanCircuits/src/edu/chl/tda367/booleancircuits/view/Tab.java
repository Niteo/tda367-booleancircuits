package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * A class to represent a tab.
 * 
 * @author Boel
 * 
 */
public final class Tab {

	private JTabbedPane tabbedPane = new JTabbedPane();
	private Canvas canvas;

	/**
	 * Adds a new tab to the tabbedPane.
	 * @param name String displayed name of the tab
	 * @param canvas Canvas the canvas connected to the tab
	 */
	public void addTab(String name, Canvas canvas) {
		this.canvas = canvas;
		JScrollPane scrollPane = new JScrollPane(canvas.getCanvas());
		tabbedPane.addTab(name, scrollPane);
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1 , new TabPanel(name));
	}

	/**
	 * Returns the tabbedPane.
	 * @return JTabbedPane the tabbedPane containing the tabs
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
}
