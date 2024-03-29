package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.implementation.ComponentFolder;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.view.IPalette;

/**
 * this class represents a palette containing folders with components
 * 
 * @author antonlin
 * 
 */

public final class Palette implements IPalette {

	private JTree componentTree;
	private List<ComponentFolder> folderList;
	private IMasterController masterController;
	private JScrollPane scrollPane;

	public Palette(final IMasterController masterController) {
		this.masterController = masterController;

		List<ICircuitGate> specialList = new ArrayList<ICircuitGate>();
		specialList.add(new ConstantGate(false));
		specialList.add(new ConstantGate(true));
		specialList.add(new Clock());
		specialList.add(new Equal());

		List<ICircuitGate> standardList = new ArrayList<ICircuitGate>();
		standardList.add(new AndGate(2));
		standardList.add(new NandGate(2));
		standardList.add(new NorGate(2));
		standardList.add(new NotGate());
		standardList.add(new OrGate(2));
		standardList.add(new XorGate(2));
		standardList.add(new XnorGate(2));

		folderList = new ArrayList<ComponentFolder>();
		folderList.add(new ComponentFolder(standardList, "Logic gates"));
		folderList.add(new ComponentFolder(specialList, "Special"));

		initPaletteTree();
		scrollPane = new JScrollPane(componentTree);
	}

	public Palette(final List<ComponentFolder> folderList) {
		this.folderList = folderList;
		initPaletteTree();
		scrollPane = new JScrollPane(componentTree);
	}

	@Override
	public JScrollPane getView() {
		return scrollPane;
	}

	/**
	 * inserts component in to folder.
	 * 
	 * @param folderNodeList
	 * @param componentFolder
	 */
	private void initComponentNodes(
			final DefaultMutableTreeNode folderNodeList,
			final ComponentFolder componentFolder) {
		// TODO:
		for (ICircuitGate acg : componentFolder.getAllComponents()) {
			folderNodeList.add(new DefaultMutableTreeNode(acg));
		}

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
			@SuppressWarnings("synthetic-access")
			@Override
			public void valueChanged(final TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) componentTree
						.getLastSelectedPathComponent();

				if (node.isLeaf()) {
					masterController.setChosenComponent(new GateWrapper(
							((AbstractCircuitGate) node.getUserObject())
									.clone()));
				}
			}
		};

		componentTree.addTreeSelectionListener(tsl);
		componentTree.expandRow(0);
		componentTree.expandRow(8);

		componentTree.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK),
				"none");
		componentTree.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK),
				"none");
		componentTree.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK),
				"none");
		componentTree.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK),
				"none");
	}

}
