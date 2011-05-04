package edu.chl.tda367.booleancircuits.view;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.chl.tda367.booleancircuits.io.ComponentFolder;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

public final class Palette {

	private JScrollPane scrollPane;
	private List<ComponentFolder> folderList;
	// Just for Test
	private List<String> folderTestList;
	private JTree componentTree;
	private AbstractCircuitGate selectedComponent;

	// Testkonstruktor
	public Palette() {
		// Test
		folderList = new ArrayList<ComponentFolder>();
		folderList.add(new ComponentFolder());

		initPaletteTree();
		scrollPane = new JScrollPane(componentTree);
		componentTree.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK), "none");
	}

	public Palette(List<ComponentFolder> folderList) {
		this.folderList = folderList;
		initPaletteTree();
		scrollPane = new JScrollPane(componentTree);
	}

	private void initPaletteTree() {

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				"I will be invisible");

		for (ComponentFolder folder : folderList) {
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(
					folder.getName());
			initComponentNodes(temp, folder);
			rootNode.add(temp);
		}


		componentTree = new JTree(rootNode);
		componentTree.setRootVisible(false);

		TreeSelectionListener tsl = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) componentTree
						.getLastSelectedPathComponent();

				if (node.isLeaf()) {
					selectedComponent = ((AbstractCircuitGate) node
							.getUserObject()).clone();
				}
			}
		};

		componentTree.addTreeSelectionListener(tsl);
	}

	/**
	 * inserts component in to folder.
	 * 
	 * @param folderNodeList
	 * @param componentFolder
	 */
	private void initComponentNodes(DefaultMutableTreeNode folderNodeList,
			ComponentFolder componentFolder) {
		// TODO:
		for (AbstractCircuitGate acg : componentFolder.getAllComponents()) {
			folderNodeList.add(new DefaultMutableTreeNode(acg));
		}

	}

	/**
	 * Returns the scrollpane containing the panel and all visual objects of the
	 * palette.
	 * 
	 * @return
	 */
	public JScrollPane getView() {
		return scrollPane;
	}

	/**
	 * Returns the component currently selected in the palette.
	 * 
	 * @return
	 */
	public AbstractCircuitGate getSelectedComponent() {
		return selectedComponent;
	}
}
