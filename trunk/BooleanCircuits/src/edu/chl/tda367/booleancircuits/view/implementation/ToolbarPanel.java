/*
 * ToolbarPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view.implementation;

import javax.swing.*;

import edu.chl.tda367.booleancircuits.view.IToolbarPanel;

/**
 * A tool bar containing several buttons.
 * 
 * @author Boel
 */
public class ToolbarPanel extends javax.swing.JPanel implements IToolbarPanel {

	/** Creates new form ToolbarPanel */
	public ToolbarPanel() {
		initComponents();
	}

	public JButton getCutButton() {
		return cutButton;
	}

	public JButton getCopyButton() {
		return copyButton;
	}

	public JButton getNewWorkspaceButton() {
		return newWorkspaceButton;
	}

	public JButton getOpenFileButton() {
		return openFileButton;
	}

	public JButton getPasteButton() {
		return pasteButton;
	}

	public JButton getPauseClockButton() {
		return pauseClockButton;
	}

	public JButton getRedoButton() {
		return redoButton;
	}

	public JButton getSaveAllButton() {
		return saveAllButton;
	}

	public JButton getSaveAsComponentButton() {
		return saveAsComponentButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getStartClockButton() {
		return startClockButton;
	}

	public JButton getUndoButton() {
		return undoButton;
	}

	public void initIcons() {
		newWorkspaceButton.setIcon(new ImageIcon(
				"Resources/Icons/New-Document-icon.png"));
		openFileButton.setIcon(new ImageIcon("Resources/Icons/Open-icon.png"));
		saveButton.setIcon(new ImageIcon("Resources/Icons/Save-icon.png"));
		saveAllButton
				.setIcon(new ImageIcon("Resources/Icons/save-all-icon.png"));
		saveAsComponentButton.setIcon(new ImageIcon(
				"Resources/Icons/save-component.png"));
		startClockButton.setIcon(new ImageIcon(
				"Resources/Icons/clock-play-icon.png"));
		cutButton.setIcon(new ImageIcon("Resources/Icons/cut-icon.png"));
		copyButton.setIcon(new ImageIcon("Resources/Icons/copy-icon.png"));
		pasteButton.setIcon(new ImageIcon("Resources/Icons/paste-icon.png"));
		pauseClockButton.setIcon(new ImageIcon(
				"Resources/Icons/clock-pause-icon.png"));
		undoButton.setIcon(new ImageIcon("Resources/Icons/Undo-icon.png"));
		redoButton.setIcon(new ImageIcon("Resources/Icons/Redo-icon.png"));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		newWorkspaceButton = new javax.swing.JButton();
		openFileButton = new javax.swing.JButton();
		saveButton = new javax.swing.JButton();
		saveAllButton = new javax.swing.JButton();
		saveAsComponentButton = new javax.swing.JButton();
		cutButton = new javax.swing.JButton();
		copyButton = new javax.swing.JButton();
		pasteButton = new javax.swing.JButton();
		undoButton = new javax.swing.JButton();
		redoButton = new javax.swing.JButton();
		startClockButton = new JButton();
		pauseClockButton = new JButton();

		setPreferredSize(new java.awt.Dimension(260, 24));
		setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

		newWorkspaceButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(newWorkspaceButton);

		openFileButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(openFileButton);

		saveAsComponentButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(saveAsComponentButton);

		saveButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(saveButton);

		saveAllButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(saveAllButton);
		
		add(new JSeparator());

		cutButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(cutButton);

		copyButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(copyButton);

		pasteButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(pasteButton);
		
		add(new JSeparator());

		undoButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(undoButton);

		redoButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(redoButton);
		
		add(new JSeparator());

		startClockButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(startClockButton);
		
		pauseClockButton.setPreferredSize(new java.awt.Dimension(20, 20));
		add(pauseClockButton);
	}// </editor-fold>
		// GEN-END:initComponents

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton copyButton;
	private javax.swing.JButton cutButton;
	private javax.swing.JButton newWorkspaceButton;
	private javax.swing.JButton openFileButton;
	private javax.swing.JButton pasteButton;
	private javax.swing.JButton redoButton;
	private javax.swing.JButton saveAllButton;
	private javax.swing.JButton saveAsComponentButton;
	private javax.swing.JButton saveButton;
	private javax.swing.JButton undoButton;
	private javax.swing.JButton pauseClockButton;
	private javax.swing.JButton startClockButton;
	// End of variables declaration//GEN-END:variables

}