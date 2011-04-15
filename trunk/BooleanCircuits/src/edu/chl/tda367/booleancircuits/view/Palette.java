package edu.chl.tda367.booleancircuits.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
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

	// Testkonstruktor
	public Palette() {
		// Test
		folderList = new ArrayList<ComponentFolder>();
		folderList.add(new ComponentFolder());

		initPaletteTree();
		scrollPane = new JScrollPane(componentTree);

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
		// Test
		DefaultMutableTreeNode hipHop = new DefaultMutableTreeNode("HipHop");
		DefaultMutableTreeNode artist = new DefaultMutableTreeNode("Q-tip");
		hipHop.add(artist);

		rootNode.add(hipHop);

		componentTree = new JTree(rootNode);
		componentTree.setRootVisible(false);

		TreeSelectionListener tsl = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) componentTree
						.getLastSelectedPathComponent();
				if (node.isLeaf()) {
					System.out.println(node.getUserObject());
				}
			}
		};
		componentTree.addTreeSelectionListener(tsl);

	}

	/**
	 * inserts component in to param folder.
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

	public JScrollPane getView() {
		return scrollPane;
	}
}
