/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import edu.chl.tda367.booleancircuits.controller.implementation.ActionController;
import edu.chl.tda367.booleancircuits.controller.implementation.MasterController;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.view.draw.implementation.BlankBackground;
import edu.chl.tda367.booleancircuits.view.draw.implementation.DottedBackground;
import edu.chl.tda367.booleancircuits.view.draw.implementation.GridBackground;

/**
 * The Main window
 * 
 * @author Boel
 */
public final class MainWindow extends javax.swing.JFrame implements
		PropertyChangeListener {

	private MasterController mc;
	private ToolbarPanel toolbar = new ToolbarPanel();
	private CenterStage cs;
	private ActionController actionController;
	private Palette palette;

	private ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == dotsRadioButtonMenuItem) {
				cs.setBackground(new DottedBackground());
			} else if (e.getSource() == gridRadioButtonMenuItem) {
				cs.setBackground(new GridBackground());
			} else if (e.getSource() == blankRadioButtonMenuItem) {
				cs.setBackground(new BlankBackground());
			} else if (e.getSource() == iecStandardRadioButtonMenuItem) {
				cs.setUSStandard(false);
			} else if (e.getSource() == usStandardRadioButtonMenuItem) {
				cs.setUSStandard(true);
			}

		}

	};

	private Action closeWorkspace = new AbstractAction("", new ImageIcon(
			"resources/icons/cross-icon.png")) {

		@Override
		public void actionPerformed(ActionEvent e) {
			int tabCount = cs.getTabManager().getTabCount();
			JButton button = (JButton) e.getSource();
			for (int i = 0; i < tabCount; i++) {
				if (button == cs.getTabManager().getTabPanel(i)
						.getCloseButton()) {
					mc.closeWorkspace(i);
					return;
				}
			}
		}
	};

	@Override
	public synchronized void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof ModelManager) {
			cs.update((ModelManager) evt.getSource());
		}
	}

	/** Creates new form View */
	public MainWindow() {
		ModelManager mm = new ModelManager();
		mm.addPropertyChangeListener(this);
		mc = new MasterController(mm);
		actionController = new ActionController(mc);
		cs = new CenterStage(closeWorkspace, this, mc);
		palette = new Palette(mc);

		cs.setBackground(new GridBackground());

		initComponents();
		initToolbar();
		initMenu();
		initButtons();
		initMenuItemText();
		initShortcuts();
		toolbar.initIcons();

		horizontalSplitPane.setRightComponent(cs.getPanel());
		horizontalSplitPane.setLeftComponent(palette.getView());
		mc.newWorkspace();
		initTabbedPane();
		setTitle("Boolean Circuits");
		setVisible(true);
		setIconImage(new ImageIcon("resources/icons/frameIcon.png").getImage());
	}

	private void initToolbar() {
		verticalSplitPane.setTopComponent(toolbar);
		verticalSplitPane.setEnabled(false);
		verticalSplitPane.setDividerSize(0);
	}

	private void initMenu() {
		// init file menu
		newWorkspaceMenuItem
				.setAction(actionController.getNewWorkspaceAction());
		importToWorkspaceMenuItem.setAction(actionController.getImportWorkspaceAction());
		openFileMenuItem.setAction(actionController.getOpenWorkspaceAction());
		closeMenuItem.setAction(actionController
				.getCloseActiveWorkspaceAction());
		closeAllMenuItem.setAction(actionController
				.getCloseAllWorkspacesAction());
		saveMenuItem.setAction(actionController.getSaveActiveWorkspaceAction());
		saveAsMenuItem.setAction(actionController.getSaveAsAction());
		saveAllMenuItem
				.setAction(actionController.getSaveAllWorkspacesAction());
		exitMenuItem.setAction(actionController.getExitAction());

		// init edit menu
		undoMenuItem.setAction(actionController.getUndoAction());
		redoMenuItem.setAction(actionController.getRedoAction());
		cutMenuItem
				.setAction(actionController.getCutSelectedComponentsAction());
		copyMenuItem.setAction(actionController
				.getCopySelectedComponentsAction());
		pasteMenuItem.setAction(actionController
				.getPasteSelectedComponentAction());
		deleteMenuItem.setAction(actionController
				.getRemoveSelectedComponentsAction());
		selectAllMenuItem.setAction(actionController
				.getSelectAllComponentsAction());
		
		// init clock pulse menu
		startClockMenuItem.setAction(actionController.getStartClockAction());
		pauseClockMenuItem.setAction(actionController.getPauseClockAction());

		// init representation menu
		iecStandardRadioButtonMenuItem.addActionListener(listener);
		usStandardRadioButtonMenuItem.addActionListener(listener);
		iecStandardRadioButtonMenuItem.setSelected(true);

		// init background menu
		dotsRadioButtonMenuItem.addActionListener(listener);
		gridRadioButtonMenuItem.addActionListener(listener);
		blankRadioButtonMenuItem.addActionListener(listener);
		gridRadioButtonMenuItem.setSelected(true);

		/*
		 * // init help menu helpMenuItem.setAction(actionController);
		 * aboutMenuItem.setAction(actionController);
		 */
	}

	private void initButtons() {

		// Set actions
		toolbar.getCutButton().setAction(
				actionController.getCutSelectedComponentsAction());
		toolbar.getCopyButton().setAction(
				actionController.getCopySelectedComponentsAction());
		toolbar.getNewWorkspaceButton().setAction(
				actionController.getNewWorkspaceAction());
		toolbar.getOpenFileButton().setAction(
				actionController.getOpenWorkspaceAction());
		toolbar.getPasteButton().setAction(
				actionController.getPasteSelectedComponentAction());
		toolbar.getPauseClockButton().setAction(
				actionController.getPauseClockAction());
		toolbar.getRedoButton().setAction(actionController.getRedoAction());
		toolbar.getSaveAsComponentButton().setAction(
				actionController.getImportWorkspaceAction());
		toolbar.getSaveAllButton().setAction(
				actionController.getSaveAllWorkspacesAction());
		toolbar.getSaveButton().setAction(
				actionController.getSaveActiveWorkspaceAction());
		toolbar.getStartClockButton().setAction(
				actionController.getStartClockAction());
		toolbar.getUndoButton().setAction(actionController.getUndoAction());

		// Disable unimplemented and clock pulse toggle
		actionController.getPauseClockAction().setEnabled(false);
		toolbar.getRedoButton().setEnabled(false);
		toolbar.getUndoButton().setEnabled(false);

		// Tooltips
		toolbar.getCutButton().setToolTipText("Cut");
		toolbar.getCopyButton().setToolTipText("Copy");
		toolbar.getNewWorkspaceButton().setToolTipText("New Workspace");
		toolbar.getOpenFileButton().setToolTipText("Open File");
		toolbar.getPasteButton().setToolTipText("Paste");
		toolbar.getRedoButton().setToolTipText("Redo");
		toolbar.getSaveAsComponentButton().setToolTipText("Import to Workspace");
		toolbar.getSaveAllButton().setToolTipText("Save All");
		toolbar.getSaveButton().setToolTipText("Save");
		toolbar.getUndoButton().setToolTipText("Undo");

	}

	private void initMenuItemText() {
		// File menu
		newWorkspaceMenuItem.setText("New Workspace");
		importToWorkspaceMenuItem.setText("Import to Workspace...");
		openFileMenuItem.setText("Open File...");
		closeMenuItem.setText("Close");
		closeAllMenuItem.setText("Close all");
		saveMenuItem.setText("Save");
		saveAsMenuItem.setText("Save As...");
		saveAllMenuItem.setText("Save All");
		exitMenuItem.setText("Exit");

		// Edit menu
		undoMenuItem.setText("Undo");
		redoMenuItem.setText("Redo");
		cutMenuItem.setText("Cut");
		copyMenuItem.setText("Copy");
		pasteMenuItem.setText("Paste");
		deleteMenuItem.setText("Delete");
		selectAllMenuItem.setText("Select All");
		
		//Clock pulse menu
		startClockMenuItem.setText("Start");
		pauseClockMenuItem.setText("Pause");

		// View menu
		representationMenu.setText("Representation");
		iecStandardRadioButtonMenuItem.setText("IEC Standard");
		usStandardRadioButtonMenuItem.setText("US Standard");

		backgroundMenu.setText("Background");
		blankRadioButtonMenuItem.setText("Blank");
		dotsRadioButtonMenuItem.setText("Dotted");
		gridRadioButtonMenuItem.setText("Grid");

		// Help menu
		helpMenuItem.setText("Help");
		aboutMenuItem.setText("About");
	}

	private void initShortcuts() {
		closeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W,
				java.awt.event.InputEvent.CTRL_MASK));
		saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Z,
				java.awt.event.InputEvent.CTRL_MASK));
		redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_Y,
				java.awt.event.InputEvent.CTRL_MASK));
		cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_X,
				java.awt.event.InputEvent.CTRL_MASK));
		copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_C,
				java.awt.event.InputEvent.CTRL_MASK));
		pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_V,
				java.awt.event.InputEvent.CTRL_MASK));
		deleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_DELETE, 0));
		selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_A,
				java.awt.event.InputEvent.CTRL_MASK));
		newWorkspaceMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_N,
				java.awt.event.InputEvent.CTRL_MASK));
		openFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O,
				java.awt.event.InputEvent.CTRL_MASK));
	}

	private void initTabbedPane() {
		cs.getTabManager().getTabbedPane().addChangeListener(actionController);
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		representationButtonGroup = new ButtonGroup();
		backgroundButtonGroup = new ButtonGroup();
		verticalSplitPane = new JSplitPane();
		horizontalSplitPane = new JSplitPane();
		paletteContainerPanel = new JPanel();
		menuBar = new JMenuBar();
		fileMenu = new JMenu();
		newWorkspaceMenuItem = new JMenuItem();
		openFileMenuItem = new JMenuItem();
		fileSeparator1 = new JSeparator();
		closeMenuItem = new JMenuItem();
		closeAllMenuItem = new JMenuItem();
		fileSeparator2 = new JSeparator();
		saveMenuItem = new JMenuItem();
		saveAsMenuItem = new JMenuItem();
		saveAllMenuItem = new JMenuItem();
		fileSeparator3 = new JSeparator();
		exitMenuItem = new JMenuItem();
		editMenu = new JMenu();
		undoMenuItem = new JMenuItem();
		redoMenuItem = new JMenuItem();
		editSeparator1 = new JSeparator();
		cutMenuItem = new JMenuItem();
		copyMenuItem = new JMenuItem();
		pasteMenuItem = new JMenuItem();
		deleteMenuItem = new JMenuItem();
		selectAllMenuItem = new JMenuItem();
		viewMenu = new JMenu();
		representationMenu = new JMenu();
		iecStandardRadioButtonMenuItem = new JRadioButtonMenuItem();
		importToWorkspaceMenuItem = new JMenuItem();
		usStandardRadioButtonMenuItem = new JRadioButtonMenuItem();
		backgroundMenu = new javax.swing.JMenu();
		blankRadioButtonMenuItem = new JRadioButtonMenuItem();
		dotsRadioButtonMenuItem = new JRadioButtonMenuItem();
		gridRadioButtonMenuItem = new JRadioButtonMenuItem();
		helpMenu = new JMenu();
		helpMenuItem = new JMenuItem();
		aboutMenuItem = new JMenuItem();
		clockpulseMenu = new JMenu();
		startClockMenuItem = new JMenuItem();
		pauseClockMenuItem = new JMenuItem();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		verticalSplitPane.setDividerLocation(30);
		verticalSplitPane.setDividerSize(0);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		horizontalSplitPane.setDividerLocation(120);
		horizontalSplitPane.setDividerSize(0);
		horizontalSplitPane.setEnabled(false);

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
		fileMenu.add(newWorkspaceMenuItem);
		fileMenu.add(importToWorkspaceMenuItem);
		fileMenu.add(openFileMenuItem);
		fileMenu.add(fileSeparator1);
		fileMenu.add(closeMenuItem);
		fileMenu.add(closeAllMenuItem);
		fileMenu.add(fileSeparator2);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(saveAllMenuItem);
		fileMenu.add(fileSeparator3);
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");
		editMenu.add(undoMenuItem);
		editMenu.add(redoMenuItem);
		editMenu.add(editSeparator1);
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		editMenu.add(deleteMenuItem);
		editMenu.add(selectAllMenuItem);

		menuBar.add(editMenu);
		
		clockpulseMenu.setText("Clock-signal");
		menuBar.add(clockpulseMenu);
		
		clockpulseMenu.add(startClockMenuItem);
		clockpulseMenu.add(pauseClockMenuItem);

		viewMenu.setText("View");

		representationButtonGroup.add(iecStandardRadioButtonMenuItem);
		iecStandardRadioButtonMenuItem.setSelected(true);
		representationMenu.add(iecStandardRadioButtonMenuItem);

		representationButtonGroup.add(usStandardRadioButtonMenuItem);
		usStandardRadioButtonMenuItem.setSelected(true);
		representationMenu.add(usStandardRadioButtonMenuItem);

		viewMenu.add(representationMenu);

		backgroundButtonGroup.add(blankRadioButtonMenuItem);
		blankRadioButtonMenuItem.setSelected(true);
		backgroundMenu.add(blankRadioButtonMenuItem);

		backgroundButtonGroup.add(dotsRadioButtonMenuItem);
		dotsRadioButtonMenuItem.setSelected(true);
		backgroundMenu.add(dotsRadioButtonMenuItem);

		backgroundButtonGroup.add(gridRadioButtonMenuItem);
		gridRadioButtonMenuItem.setSelected(true);
		backgroundMenu.add(gridRadioButtonMenuItem);

		viewMenu.add(backgroundMenu);

		menuBar.add(viewMenu);

		helpMenu.setText("Help");
		helpMenu.add(helpMenuItem);
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
		// GEN-END:initComponents

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private JMenuItem aboutMenuItem;
	private ButtonGroup backgroundButtonGroup;
	private JMenu backgroundMenu;
	private JRadioButtonMenuItem blankRadioButtonMenuItem;
	private JMenu clockpulseMenu;
	private JMenuItem closeAllMenuItem;
	private JMenuItem closeMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenuItem deleteMenuItem;
	private JRadioButtonMenuItem dotsRadioButtonMenuItem;
	private JMenu editMenu;
	private JSeparator editSeparator1;
	private JMenuItem exitMenuItem;
	private JMenu fileMenu;
	private JSeparator fileSeparator1;
	private JSeparator fileSeparator2;
	private JSeparator fileSeparator3;
	private JRadioButtonMenuItem gridRadioButtonMenuItem;
	private JMenu helpMenu;
	private JMenuItem helpMenuItem;
	private JSplitPane horizontalSplitPane;
	private JRadioButtonMenuItem iecStandardRadioButtonMenuItem;
	private JMenuItem importToWorkspaceMenuItem;
	private JMenuBar menuBar;
	private JMenuItem newWorkspaceMenuItem;
	private JMenuItem openFileMenuItem;
	private JPanel paletteContainerPanel;
	private JMenuItem pasteMenuItem;
	private JMenuItem pauseClockMenuItem;
	private JMenuItem redoMenuItem;
	private ButtonGroup representationButtonGroup;
	private JMenu representationMenu;
	private JMenuItem saveAllMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem selectAllMenuItem;
	private JMenuItem startClockMenuItem;
	private JMenuItem undoMenuItem;
	private JRadioButtonMenuItem usStandardRadioButtonMenuItem;
	private JSplitPane verticalSplitPane;
	private JMenu viewMenu;

	// End of variables declaration//GEN-END:variables

}