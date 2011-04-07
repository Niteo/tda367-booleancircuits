package edu.chl.tda367.booleancircuits.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public final class PaletteView {

	private final JPanel paletteView;
	private final JScrollPane scrollPane;
	// private final List<ComponentFolder> folderList;
	// Just for Test
	private final List<String> folderTestList;
	private JTree componentTree;

	// kolla JTree...
	public PaletteView() {
		// Test
		folderTestList = new ArrayList<String>();
		folderTestList.add("Yo");
		folderTestList.add("Nigga");
		folderTestList.add("Gonna");
		folderTestList.add("Fuck");
		folderTestList.add("You");
		folderTestList.add("Up");

		scrollPane = new JScrollPane();
		paletteView = new JPanel(new BorderLayout());
		scrollPane.add(paletteView);
		initPaletteTree();

	}

	private void initPaletteTree() {

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				"I will be invisible");

		for (String s : folderTestList) {
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(s);
			// initComponentNodes(temp, componentFolder);
			rootNode.add(temp);
		}
		System.out.println(folderTestList);
		componentTree = new JTree(rootNode);
		componentTree.setRootVisible(false);
		paletteView.add(componentTree, BorderLayout.CENTER);
		componentTree.setPreferredSize(new java.awt.Dimension(326, 300));
		paletteView.revalidate();
	}

	private void initComponentNodes(DefaultMutableTreeNode folderNodeList,
			ComponentFolder componentFolder) {
		// TODO:

	}

	public JScrollPane getView() {
		return scrollPane;
	}
}
