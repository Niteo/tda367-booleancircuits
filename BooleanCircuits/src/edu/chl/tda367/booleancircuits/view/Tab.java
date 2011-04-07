package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;

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

	public void addTab(String s, Canvas c) {
		canvas = c;
		tabbedPane.addTab(s, c.getCanvas());
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
