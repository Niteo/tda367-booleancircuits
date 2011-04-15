package edu.chl.tda367.booleancircuits.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.chl.tda367.booleancircuits.io.ComponentFolder;

public final class Palette {

	private JScrollPane scrollPane;
	private List<ComponentFolder> folderList;
	// Just for Test
	private List<String> folderTestList;
	private JTree componentTree;

	// Testkonstruktor
	public Palette() {
		// Test
		folderTestList = new ArrayList<String>();
		folderTestList.add("hej");
		folderTestList.add("hej");

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

		for (String s : folderTestList) {
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(s);
			// initComponentNodes(temp, componentFolder);
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
					System.out.println("isLeaf maddafacka");
				}
			}
		};
		componentTree.addTreeSelectionListener(tsl);

		/*
		 * paletteView.add(componentTree, BorderLayout.CENTER);
		 * componentTree.setPreferredSize(new java.awt.Dimension(326, 300));
		 * paletteView.revalidate();
		 */
	}

	private void initComponentNodes(DefaultMutableTreeNode folderNodeList,
			ComponentFolder componentFolder) {
		// TODO:

	}

	public JScrollPane getView() {
		return scrollPane;
	}
}
