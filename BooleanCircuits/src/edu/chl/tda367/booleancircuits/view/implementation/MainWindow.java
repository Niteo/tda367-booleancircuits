/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.controller.implementation.ActionController;
import edu.chl.tda367.booleancircuits.controller.implementation.MasterController;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.IPalette;
import edu.chl.tda367.booleancircuits.view.draw.implementation.BlankBackground;
import edu.chl.tda367.booleancircuits.view.draw.implementation.DottedBackground;
import edu.chl.tda367.booleancircuits.view.draw.implementation.GridBackground;

/**
 * The Main window
 *
 * @author Boel
 */
public final class MainWindow extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private JMenuItem aboutMenuItem;
	private ActionController actionController;
	private ButtonGroup backgroundButtonGroup;
	private JMenu backgroundMenu;
	private JRadioButtonMenuItem blankRadioButtonMenuItem;
	private JMenu clockpulseMenu;
	private JMenuItem closeAllMenuItem;
	private JMenuItem closeMenuItem;
	private Action closeWorkspace = new AbstractAction("", new ImageIcon(
			"resources/icons/cross-icon.png")) {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
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
	private JMenuItem copyMenuItem;
	private CenterStage cs;
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
	private transient ActionListener listener;
	private transient IMasterController mc;
	private JMenuBar menuBar;
	private JMenuItem newWorkspaceMenuItem;
	private JMenuItem openFileMenuItem;
	private transient IPalette palette;
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
	private ToolbarPanel toolbar = new ToolbarPanel();
	private JMenuItem undoMenuItem;
	private JRadioButtonMenuItem usStandardRadioButtonMenuItem;
	private JSplitPane verticalSplitPane;
	private JMenu viewMenu;
	private transient WindowAdapter windowAdapter;

	/** Creates new form View */
	public MainWindow() {
		listener = new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(final ActionEvent e) {
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

				else if (e.getSource() == aboutMenuItem) {
					Icon logo = new ImageIcon("resources/icons/cross-icon.png");
					new AboutBox(Constants.creditsText, logo);
				} else if (e.getSource() == exitMenuItem) {
					windowAdapter.windowClosing(new WindowEvent(_getWindow(), 0));
				}

			}

		};

		windowAdapter = new WindowAdapter() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void windowClosing(final WindowEvent arg0) {
				if (mc.closeAllWorkspaces()) {
					dispose();
				}
			}
		};
		ModelManager mm = new ModelManager();
		mm.addPropertyChangeListener(this);
		mc = new MasterController(mm);
		actionController = new ActionController(mc);
		cs = new CenterStage(closeWorkspace, mc);
		palette = new Palette(mc);

		this.addWindowListener(windowAdapter);
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
		setSize(new Dimension(800, 500));
		setIconImage(new ImageIcon("resources/icons/frameIcon.png").getImage());
	}

	@Override
	public synchronized void propertyChange(final PropertyChangeEvent evt) {
		if (evt.getSource() instanceof ModelManager) {
			cs.update((ModelManager) evt.getSource());
		}
	}

	private MainWindow _getWindow() {
		return this;
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
		toolbar.getCutButton().setToolTipText("Cut (Ctrl+X");
		toolbar.getCopyButton().setToolTipText("Copy (Ctrl+C)");
		toolbar.getNewWorkspaceButton()
				.setToolTipText("New Workspace (Ctrl+N)");
		toolbar.getOpenFileButton().setToolTipText("Open File (Ctrl+O)");
		toolbar.getPasteButton().setToolTipText("Paste (Ctrl+V)");
		toolbar.getPauseClockButton().setToolTipText("Pause Clock Signal (P)");
		toolbar.getRedoButton().setToolTipText("Redo (Ctrl+Y)");
		toolbar.getSaveAsComponentButton().setToolTipText(
				"Import to Workspace (Ctrl+I)");
		toolbar.getSaveAllButton().setToolTipText("Save All (Ctrl+Shift+S)");
		toolbar.getSaveButton().setToolTipText("Save (Ctrl+S)");
		toolbar.getStartClockButton().setToolTipText("Start Clock Signal (S)");
		toolbar.getUndoButton().setToolTipText("Undo (Ctrl+Z)");

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

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		verticalSplitPane.setDividerLocation(30);
		verticalSplitPane.setDividerSize(0);
		verticalSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		horizontalSplitPane.setDividerLocation(120);
		horizontalSplitPane.setDividerSize(0);
		horizontalSplitPane.setEnabled(false);

		GroupLayout paletteContainerPanelLayout = new GroupLayout(
				paletteContainerPanel);
		paletteContainerPanel.setLayout(paletteContainerPanelLayout);
		paletteContainerPanelLayout
				.setHorizontalGroup(paletteContainerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGap(0, 0, Short.MAX_VALUE));
		paletteContainerPanelLayout
				.setVerticalGroup(paletteContainerPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
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
		editMenu.add(new JSeparator());
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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(verticalSplitPane,
				GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(verticalSplitPane,
				GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void initMenu() {
		// init file menu
		newWorkspaceMenuItem
				.setAction(actionController.getNewWorkspaceAction());
		importToWorkspaceMenuItem.setAction(actionController
				.getImportWorkspaceAction());
		openFileMenuItem.setAction(actionController.getOpenWorkspaceAction());
		closeMenuItem.setAction(actionController
				.getCloseActiveWorkspaceAction());
		closeAllMenuItem.setAction(actionController
				.getCloseAllWorkspacesAction());
		saveMenuItem.setAction(actionController.getSaveActiveWorkspaceAction());
		saveAsMenuItem.setAction(actionController.getSaveAsAction());
		saveAllMenuItem
				.setAction(actionController.getSaveAllWorkspacesAction());
		exitMenuItem.addActionListener(listener);

		// init edit menu
		undoMenuItem.setAction(actionController.getUndoAction());
		undoMenuItem.setEnabled(false);
		redoMenuItem.setAction(actionController.getRedoAction());
		redoMenuItem.setEnabled(false);
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

		// init help menu
		helpMenuItem.setAction(actionController.getShowHelpAction());
		aboutMenuItem.addActionListener(listener);

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

		// Clock pulse menu
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
		closeAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_W,
				java.awt.event.InputEvent.CTRL_DOWN_MASK
						| java.awt.event.InputEvent.SHIFT_DOWN_MASK));
		startClockMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S, 0));
		pauseClockMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_P, 0));
		helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_F1, 0));
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
		saveAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_DOWN_MASK
						| java.awt.event.InputEvent.SHIFT_DOWN_MASK));
		importToWorkspaceMenuItem.setAccelerator(javax.swing.KeyStroke
				.getKeyStroke(java.awt.event.KeyEvent.VK_I,
						java.awt.event.InputEvent.CTRL_MASK));

	}

	private void initTabbedPane() {
		cs.getTabManager().getTabbedPane().addChangeListener(actionController);
	}

	private void initToolbar() {
		verticalSplitPane.setTopComponent(toolbar);
		verticalSplitPane.setEnabled(false);
		verticalSplitPane.setDividerSize(0);
	}

	// End of variables declaration//GEN-END:variables

}