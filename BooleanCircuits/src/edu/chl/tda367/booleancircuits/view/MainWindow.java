/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

import edu.chl.tda367.booleancircuits.controller.ActionController;
import edu.chl.tda367.booleancircuits.controller.MasterController;
import edu.chl.tda367.booleancircuits.model.ModelManager;
import edu.chl.tda367.booleancircuits.view.Canvas.CanvasAction;
import edu.chl.tda367.booleancircuits.view.draw.BlankBackground;
import edu.chl.tda367.booleancircuits.view.draw.DottedBackground;
import edu.chl.tda367.booleancircuits.view.draw.SquaredBackground;

/**
 * The Main window
 * 
 * @author Boel
 */
public class MainWindow extends javax.swing.JFrame implements
		PropertyChangeListener {

	private MasterController mc;
	private Toolbar toolbar = new Toolbar();
	private CenterStage cs;
	private ActionController actionController;
	private Palette palette = new Palette();
	
	
	private ActionListener listener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==dotsRadioButtonMenuItem){
				cs.setBackground(new DottedBackground());
			}
			else if(e.getSource()==squaresRadioButtonMenuItem){
				cs.setBackground(new SquaredBackground());
			}
			else if(e.getSource()==blankRadioButtonMenuItem){
				cs.setBackground(new BlankBackground());
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

	/** Creates new form View */
	public MainWindow() {
		ModelManager mm = new ModelManager();
		mm.addPropertyChangeListener(this);
		mc = new MasterController(mm);
		actionController = new ActionController(mc);
		cs = new CenterStage(closeWorkspace, this);

		cs.setBackground(new SquaredBackground());
		
		initComponents();
		initToolbar();
		initMenu();
		initButtons();
		initMenuItemText();
		initShortcuts();
		toolbar.getToolbarPanel().initIcons();

		horizontalSplitPane.setRightComponent(cs.getPanel());
		horizontalSplitPane.setLeftComponent(palette.getView());
		mc.newWorkspace();
		initTabbedPane();
		setTitle("Boolean Circuits");
	}

	private void initToolbar() {
		verticalSplitPane.setTopComponent(toolbar.getToolbarPanel());
		verticalSplitPane.setEnabled(false);
		verticalSplitPane.setDividerSize(0);
	}

	private void initMenu() {
		// init file menu
		newWorkspaceMenuItem
				.setAction(actionController.getNewWorkspaceAction());
		openFileMenuItem.setAction(actionController.getOpenWorkspaceAction());
		closeMenuItem.setAction(actionController
				.getCloseActiveWorkspaceAction());
		closeAllMenuItem.setAction(actionController
				.getCloseAllWorkspacesAction());
		saveMenuItem.setAction(actionController.getSaveActiveWorkspaceAction());
		saveAsMenuItem.setAction(actionController
				.getSaveActiveWorskpaceAsComponentAction());
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

		/*
		 * TODO setActions for representation and help menu // init
		 * representation menu
		 * iecStandardRadioButtonMenuItem.setAction(actionController);
		 * usStandardRadioButtonMenuItem.setAction(actionController);
		 * 
		 */
		
		 // init background menu
		 dotsRadioButtonMenuItem.addActionListener(listener);
		 squaresRadioButtonMenuItem.addActionListener(listener);
		 blankRadioButtonMenuItem.addActionListener(listener);
		 squaresRadioButtonMenuItem.setSelected(true);


		// init insert menu
		componentMenuItem.setAction(actionController.getAddComponentAction());

		/*
		 * // init help menu helpMenuItem.setAction(actionController);
		 * aboutMenuItem.setAction(actionController);
		 */
	}

	private void initButtons() {

		toolbar.getToolbarPanel().getCutButton()
				.setAction(actionController.getCutSelectedComponentsAction());
		toolbar.getToolbarPanel().getCopyButton()
				.setAction(actionController.getCopySelectedComponentsAction());
		toolbar.getToolbarPanel().getNewWorkspaceButton()
				.setAction(actionController.getNewWorkspaceAction());
		toolbar.getToolbarPanel().getOpenFileButton()
				.setAction(actionController.getOpenWorkspaceAction());
		toolbar.getToolbarPanel().getPasteButton()
				.setAction(actionController.getPasteSelectedComponentAction());
		toolbar.getToolbarPanel().getRedoButton()
				.setAction(actionController.getRedoAction());
		toolbar.getToolbarPanel()
				.getSaveAsComponentButton()
				.setAction(
						actionController
								.getSaveActiveWorskpaceAsComponentAction());
		toolbar.getToolbarPanel().getSaveAllButton()
				.setAction(actionController.getSaveAllWorkspacesAction());
		toolbar.getToolbarPanel().getSaveButton()
				.setAction(actionController.getSaveActiveWorkspaceAction());
		toolbar.getToolbarPanel().getUndoButton()
				.setAction(actionController.getUndoAction());
	}

	private void initMenuItemText() {
		// File menu
		newWorkspaceMenuItem.setText("New Workspace");
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

		// View menu
		representationMenu.setText("Representation");
		iecStandardRadioButtonMenuItem.setText("IEC Standard");
		usStandardRadioButtonMenuItem.setText("US Standard");

		backgroundMenu.setText("Background");
		blankRadioButtonMenuItem.setText("Blank");
		dotsRadioButtonMenuItem.setText("Dotted");
		squaresRadioButtonMenuItem.setText("Squared");

		// Insert menu
		componentMenuItem.setText("Component...");

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
		closeAllMenuItem = new javax.swing.JMenuItem();
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

		verticalSplitPane.setDividerLocation(30);
		verticalSplitPane.setDividerSize(0);
		verticalSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

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

		backgroundButtonGroup.add(squaresRadioButtonMenuItem);
		squaresRadioButtonMenuItem.setSelected(true);
		backgroundMenu.add(squaresRadioButtonMenuItem);

		viewMenu.add(backgroundMenu);

		menuBar.add(viewMenu);

		insertMenu.setText("Insert");
		insertMenu.add(componentMenuItem);

		menuBar.add(insertMenu);

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

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.ButtonGroup backgroundButtonGroup;
	private javax.swing.JMenu backgroundMenu;
	private javax.swing.JRadioButtonMenuItem blankRadioButtonMenuItem;
	private javax.swing.JMenuItem closeAllMenuItem;
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
	private javax.swing.JRadioButtonMenuItem squaresRadioButtonMenuItem;
	private javax.swing.JMenuItem undoMenuItem;
	private javax.swing.JRadioButtonMenuItem usStandardRadioButtonMenuItem;
	private javax.swing.JSplitPane verticalSplitPane;
	private javax.swing.JMenu viewMenu;

	// End of variables declaration//GEN-END:variables

	@Override
	public synchronized void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof ModelManager) {
			cs.update((ModelManager) evt.getSource());
		} else if (evt.getSource() instanceof Canvas) {
			CanvasEvent canvasEvt = (CanvasEvent) evt.getNewValue();
			if (canvasEvt.getAction() == CanvasAction.PLACE
					&& palette.getSelectedComponent() != null) {
				mc.addComponent(palette.getSelectedComponent(),
						canvasEvt.getPoint());
			} else if (canvasEvt.getAction() == CanvasAction.SELECT) {
				mc.selectComponent(canvasEvt.getPoint());
			}
		}

	}

}