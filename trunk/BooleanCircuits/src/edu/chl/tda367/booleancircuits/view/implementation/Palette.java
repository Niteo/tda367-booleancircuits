package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.implementation.ComponentFolder;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;
import edu.chl.tda367.booleancircuits.view.IPalette;

/**
 * this class represents a palette containing folders with components
 *
 * @author antonlin
 *
 */

public final class Palette implements IPalette {

	private JScrollPane scrollPane;
	private List<ComponentFolder> folderList;
	private JTree componentTree;
	private IMasterController masterController;

	public Palette(IMasterController masterController) {
		this.masterController = masterController;

		List<ICircuitGate> specialList = new ArrayList<ICircuitGate>();
		specialList.add(new ConstantGate(false));
		specialList.add(new ConstantGate(true));
		specialList.add(new Clock());

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
			@SuppressWarnings("synthetic-access")
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) componentTree
						.getLastSelectedPathComponent();

				if (node.isLeaf()) {
					masterController
							.setChosenComponent(((AbstractCircuitGate) node
									.getUserObject()).clone());
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

	/**
	 * inserts component in to folder.
	 *
	 * @param folderNodeList
	 * @param componentFolder
	 */
	private void initComponentNodes(DefaultMutableTreeNode folderNodeList,
			ComponentFolder componentFolder) {
		// TODO:
		for (ICircuitGate acg : componentFolder.getAllComponents()) {
			folderNodeList.add(new DefaultMutableTreeNode(acg));
		}

	}

	@Override
	public JScrollPane getView() {
		return scrollPane;
	}

}
