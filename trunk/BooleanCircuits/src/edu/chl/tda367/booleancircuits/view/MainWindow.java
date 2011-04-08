/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.controller.ActionController;
import edu.chl.tda367.booleancircuits.controller.MasterController;
import edu.chl.tda367.booleancircuits.model.ModelManager;

/**
 * 
 * @author Boel
 */
public class MainWindow extends javax.swing.JFrame implements
		PropertyChangeListener {

	private MasterController mc;
	private Toolbar toolbar = new Toolbar();
	private CenterStage cs = new CenterStage();
	private ActionController actionController = new ActionController(mc);

	/** Creates new form View */
	public MainWindow() {
		ModelManager mm = new ModelManager();
		mm.addPropertyChangeListener(this);
		mc = new MasterController(mm);

		initComponents();
		initToolbar();
		initMenu();
		initButtons();
		horizontalSplitPane.setRightComponent(cs.getPanel());
		mc.newWorkspace();
		setTitle("Boolean Circuits");
	}

	private void initToolbar() {
		verticalSplitPane.setTopComponent(toolbar.getToolbarPanel());
		verticalSplitPane.setEnabled(false);
		verticalSplitPane.setDividerSize(0);
	}

	private void initMenu() {
		// init file menu
		newWorkspaceMenuItem.addActionListener(actionController);
		openFileMenuItem.addActionListener(actionController);
		closeMenuItem.addActionListener(actionController);
		saveMenuItem.addActionListener(actionController);
		saveAsMenuItem.addActionListener(actionController);
		saveAllMenuItem.addActionListener(actionController);
		exitMenuItem.addActionListener(actionController);

		// init edit menu
		undoMenuItem.addActionListener(actionController);
		redoMenuItem.addActionListener(actionController);
		cutMenuItem.addActionListener(actionController);
		copyMenuItem.addActionListener(actionController);
		pasteMenuItem.addActionListener(actionController);
		deleteMenuItem.addActionListener(actionController);
		selectMenuItem.addActionListener(actionController);
		selectAllMenuItem.addActionListener(actionController);

		// init representation menu
		iecStandardRadioButtonMenuItem.addActionListener(actionController);
		usStandardRadioButtonMenuItem.addActionListener(actionController);

		// init background menu
		dotsRadioButtonMenuItem.addActionListener(actionController);
		squaresRadioButtonMenuItem.addActionListener(actionController);
		blankRadioButtonMenuItem.addActionListener(actionController);

		// init insert menu
		componentMenuItem.addActionListener(actionController);

		// init help menu
		helpMenuItem.addActionListener(actionController);
		aboutMenuItem.addActionListener(actionController);
	}

	private void initButtons() {

		toolbar.getToolbarPanel().getCutButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getCopyButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getNewWorkspaceButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getOpenFileButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getPasteButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getRedoButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getSaveAsComponentButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getSaveAllButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getSaveButton()
				.addActionListener(actionController);
		toolbar.getToolbarPanel().getUndoButton()
				.addActionListener(actionController);
	}

	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		representationButtonGroup = new javax.swing.ButtonGroup();
		backgroundButtonGroup = new javax.swing.ButtonGroup();
		verticalSplitPane = new javax.swing.JSplitPane();
		horizontalSplitPane = new javax.swing.JSplitPane();
		paletteContainerPanel = new javax.swing.JPanel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		newWorkspaceMenuItem = new javax.swing.JMenuItem();
		openFileMenuItem = new javax.swing.JMenuItem();
		fileSeparator1 = new javax.swing.JSeparator();
		closeMenuItem = new javax.swing.JMenuItem();
		fileSeparator2 = new javax.swing.JSeparator();
		saveMenuItem = new javax.swing.JMenuItem();
		saveAsMenuItem = new javax.swing.JMenuItem();
		saveAllMenuItem = new javax.swing.JMenuItem();
		fileSeparator3 = new javax.swing.JSeparator();
		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		undoMenuItem = new javax.swing.JMenuItem();
		redoMenuItem = new javax.swing.JMenuItem();
		editSeparator1 = new javax.swing.JSeparator();
		cutMenuItem = new javax.swing.JMenuItem();
		copyMenuItem = new javax.swing.JMenuItem();
		pasteMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new javax.swing.JMenuItem();
		selectMenuItem = new javax.swing.JMenuItem();
		selectAllMenuItem = new javax.swing.JMenuItem();
		viewMenu = new javax.swing.JMenu();
		representationMenu = new javax.swing.JMenu();
		iecStandardRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
		usStandardRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
		backgroundMenu = new javax.swing.JMenu();
		blankRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
		dotsRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
		squaresRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
		insertMenu = new javax.swing.JMenu();
		componentMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		helpMenuItem = new javax.swing.JMenuItem();
		aboutMenuItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		verticalSplitPane.setDividerLocation(24);
		verticalSplitPane.setDividerSize(0);
		verticalSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		javax.swing.GroupLayout paletteContainerPanelLayout = new javax.swing.GroupLayout(
				paletteContainerPanel);
		paletteContainerPanel.setLayout(paletteContainerPanelLayout);
		paletteContainerPanelLayout
				.setHorizontalGroup(paletteContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 0, Short.MAX_VALUE));
		paletteContainerPanelLayout
				.setVerticalGroup(paletteContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 300, Short.MAX_VALUE));

		horizontalSplitPane.setLeftComponent(paletteContainerPanel);

		verticalSplitPane.setBottomComponent(horizontalSplitPane);

		fileMenu.setText("File");

		newWorkspaceMenuItem.setText("New Workspace");
		fileMenu.add(newWorkspaceMenuItem);

		openFileMenuItem.setText("Open File...");
		fileMenu.add(openFileMenuItem);
		fileMenu.add(fileSeparator1);

		closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W,
				java.awt.event.InputEvent.CTRL_MASK));
		closeMenuItem.setText("Close");
		fileMenu.add(closeMenuItem);
		fileMenu.add(fileSeparator2);

		saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		saveMenuItem.setText("Save");
		fileMenu.add(saveMenuItem);

		saveAsMenuItem.setText("Save As...");
		fileMenu.add(saveAsMenuItem);

		saveAllMenuItem.setText("Save All");
		fileMenu.add(saveAllMenuItem);
		fileMenu.add(fileSeparator3);

		exitMenuItem.setText("Exit");
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");

		undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Z,
				java.awt.event.InputEvent.CTRL_MASK));
		undoMenuItem.setText("Undo");
		editMenu.add(undoMenuItem);

		redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Y,
				java.awt.event.InputEvent.CTRL_MASK));
		redoMenuItem.setText("Redo");
		editMenu.add(redoMenuItem);
		editMenu.add(editSeparator1);

		cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_X,
				java.awt.event.InputEvent.CTRL_MASK));
		cutMenuItem.setText("Cut");
		editMenu.add(cutMenuItem);

		copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_C,
				java.awt.event.InputEvent.CTRL_MASK));
		copyMenuItem.setText("Copy");
		editMenu.add(copyMenuItem);

		pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_V,
				java.awt.event.InputEvent.CTRL_MASK));
		pasteMenuItem.setText("Paste");
		editMenu.add(pasteMenuItem);

		deleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_DELETE, 0));
		deleteMenuItem.setText("Delete");
		editMenu.add(deleteMenuItem);

		selectMenuItem.setText("Select");
		editMenu.add(selectMenuItem);

		selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_A,
				java.awt.event.InputEvent.CTRL_MASK));
		selectAllMenuItem.setText("Select All");
		editMenu.add(selectAllMenuItem);

		menuBar.add(editMenu);

		viewMenu.setText("View");

		representationMenu.setText("Representation");

		representationButtonGroup.add(iecStandardRadioButtonMenuItem);
		iecStandardRadioButtonMenuItem.setSelected(true);
		iecStandardRadioButtonMenuItem.setText("IEC Standard");
		representationMenu.add(iecStandardRadioButtonMenuItem);

		representationButtonGroup.add(usStandardRadioButtonMenuItem);
		usStandardRadioButtonMenuItem.setSelected(true);
		usStandardRadioButtonMenuItem.setText("US Standard");
		representationMenu.add(usStandardRadioButtonMenuItem);

		viewMenu.add(representationMenu);

		backgroundMenu.setText("Background");

		backgroundButtonGroup.add(blankRadioButtonMenuItem);
		blankRadioButtonMenuItem.setSelected(true);
		blankRadioButtonMenuItem.setText("Blank");
		backgroundMenu.add(blankRadioButtonMenuItem);

		backgroundButtonGroup.add(dotsRadioButtonMenuItem);
		dotsRadioButtonMenuItem.setSelected(true);
		dotsRadioButtonMenuItem.setText("Dots");
		backgroundMenu.add(dotsRadioButtonMenuItem);

		backgroundButtonGroup.add(squaresRadioButtonMenuItem);
		squaresRadioButtonMenuItem.setSelected(true);
		squaresRadioButtonMenuItem.setText("Squares");
		backgroundMenu.add(squaresRadioButtonMenuItem);

		viewMenu.add(backgroundMenu);

		menuBar.add(viewMenu);

		insertMenu.setText("Insert");

		componentMenuItem.setText("Component...");
		insertMenu.add(componentMenuItem);

		menuBar.add(insertMenu);

		helpMenu.setText("Help");

		helpMenuItem.setText("Help");
		helpMenu.add(helpMenuItem);

		aboutMenuItem.setText("About");
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				verticalSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 589,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				verticalSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 327,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainWindow().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.ButtonGroup backgroundButtonGroup;
	private javax.swing.JMenu backgroundMenu;
	private javax.swing.JRadioButtonMenuItem blankRadioButtonMenuItem;
	private javax.swing.JMenuItem closeMenuItem;
	private javax.swing.JMenuItem componentMenuItem;
	private javax.swing.JMenuItem copyMenuItem;
	private javax.swing.JMenuItem cutMenuItem;
	private javax.swing.JMenuItem deleteMenuItem;
	private javax.swing.JRadioButtonMenuItem dotsRadioButtonMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JSeparator editSeparator1;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JSeparator fileSeparator1;
	private javax.swing.JSeparator fileSeparator2;
	private javax.swing.JSeparator fileSeparator3;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JMenuItem helpMenuItem;
	private javax.swing.JSplitPane horizontalSplitPane;
	private javax.swing.JRadioButtonMenuItem iecStandardRadioButtonMenuItem;
	private javax.swing.JMenu insertMenu;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem newWorkspaceMenuItem;
	private javax.swing.JMenuItem openFileMenuItem;
	private javax.swing.JPanel paletteContainerPanel;
	private javax.swing.JMenuItem pasteMenuItem;
	private javax.swing.JMenuItem redoMenuItem;
	private javax.swing.ButtonGroup representationButtonGroup;
	private javax.swing.JMenu representationMenu;
	private javax.swing.JMenuItem saveAllMenuItem;
	private javax.swing.JMenuItem saveAsMenuItem;
	private javax.swing.JMenuItem saveMenuItem;
	private javax.swing.JMenuItem selectAllMenuItem;
	private javax.swing.JMenuItem selectMenuItem;
	private javax.swing.JRadioButtonMenuItem squaresRadioButtonMenuItem;
	private javax.swing.JMenuItem undoMenuItem;
	private javax.swing.JRadioButtonMenuItem usStandardRadioButtonMenuItem;
	private javax.swing.JSplitPane verticalSplitPane;
	private javax.swing.JMenu viewMenu;

	// End of variables declaration//GEN-END:variables

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof ModelManager) {
			cs.update((ModelManager) evt.getSource());
		}

	}

}