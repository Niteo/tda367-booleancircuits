package edu.chl.tda367.booleancircuits.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public final class PaletteView {

	private final JScrollPane paletteView;
	private final List<ComponentFolder> folderList;
	private JTree componentTree;

	// kolla JTree...
	public PaletteView(List<ComponentFolder> folderList) {

		this.folderList = folderList;
		paletteView = new JScrollPane();

		initPaletteTree();

	}

	private void initPaletteTree() {

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				"I will be invisible");

		List<DefaultMutableTreeNode> folderNodeList = new ArrayList<DefaultMutableTreeNode>();
		for (ComponentFolder cf : folderList) {
			DefaultMutableTreeNode temp = new DefaultMutableTreeNode(
					cf.getName());

			folderNodeList.add(temp);
			rootNode.add(temp);
		}

		componentTree = new JTree(rootNode);
		componentTree.setRootVisible(false);
	}

	public JScrollPane getView() {
		return paletteView;
	}
}
