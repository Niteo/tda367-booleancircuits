/*
 * View.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.chl.tda367.booleancircuits.view;

/**
 *
 * @author  __USER__
 */
public class View extends javax.swing.JFrame {

	/** Creates new form View */
	public View() {
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	//GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		verticalSplitPane = new javax.swing.JSplitPane();
		horizontalSplitPane = new javax.swing.JSplitPane();
		paletteContainerPanel = new javax.swing.JPanel();
		canvasContainerPanel = new javax.swing.JPanel();
		toolbarContainerPanel = new javax.swing.JPanel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		editMenu = new javax.swing.JMenu();
		viewMenu = new javax.swing.JMenu();
		insertMenu = new javax.swing.JMenu();
		helpMenu = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		verticalSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

		javax.swing.GroupLayout paletteContainerPanelLayout = new javax.swing.GroupLayout(
				paletteContainerPanel);
		paletteContainerPanel.setLayout(paletteContainerPanelLayout);
		paletteContainerPanelLayout
				.setHorizontalGroup(paletteContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 100, Short.MAX_VALUE));
		paletteContainerPanelLayout
				.setVerticalGroup(paletteContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 264, Short.MAX_VALUE));

		horizontalSplitPane.setLeftComponent(paletteContainerPanel);

		javax.swing.GroupLayout canvasContainerPanelLayout = new javax.swing.GroupLayout(
				canvasContainerPanel);
		canvasContainerPanel.setLayout(canvasContainerPanelLayout);
		canvasContainerPanelLayout
				.setHorizontalGroup(canvasContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 563, Short.MAX_VALUE));
		canvasContainerPanelLayout.setVerticalGroup(canvasContainerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 264, Short.MAX_VALUE));

		horizontalSplitPane.setRightComponent(canvasContainerPanel);

		verticalSplitPane.setBottomComponent(horizontalSplitPane);

		javax.swing.GroupLayout toolbarContainerPanelLayout = new javax.swing.GroupLayout(
				toolbarContainerPanel);
		toolbarContainerPanel.setLayout(toolbarContainerPanelLayout);
		toolbarContainerPanelLayout
				.setHorizontalGroup(toolbarContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 670, Short.MAX_VALUE));
		toolbarContainerPanelLayout
				.setVerticalGroup(toolbarContainerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 100, Short.MAX_VALUE));

		verticalSplitPane.setLeftComponent(toolbarContainerPanel);

		fileMenu.setText("File");
		menuBar.add(fileMenu);

		editMenu.setText("Edit");
		menuBar.add(editMenu);

		viewMenu.setText("View");
		menuBar.add(viewMenu);

		insertMenu.setText("Insert");
		menuBar.add(insertMenu);

		helpMenu.setText("Help");
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				verticalSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 672,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				verticalSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 373,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
	//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new View().setVisible(true);
			}
		});
	}

	//GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JPanel canvasContainerPanel;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JSplitPane horizontalSplitPane;
	private javax.swing.JMenu insertMenu;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JPanel paletteContainerPanel;
	private javax.swing.JPanel toolbarContainerPanel;
	private javax.swing.JSplitPane verticalSplitPane;
	private javax.swing.JMenu viewMenu;
	// End of variables declaration//GEN-END:variables

}