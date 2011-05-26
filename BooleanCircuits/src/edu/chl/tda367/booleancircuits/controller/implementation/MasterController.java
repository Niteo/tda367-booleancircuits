package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.implementation.CircuitManager;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.*;

public final class MasterController implements IMasterController {

	private IGateWrapper chosenGate;
	private IClipboardManager clipboardManager = new ClipboardManager();
	private Timer clockTimer;
	private IGateWrapper connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private final ICircuitManager circuitManager;

	/**
	 * Returns an instance of a MasterController
	 * 
	 * @param mm
	 *            the ModelManager to control
	 * @throws NullPointerException
	 *             if mm is null
	 */
	public MasterController(final CircuitManager mm) {
		if (mm == null) {
			throw new NullPointerException("mm must not be null!");
		} else {
			circuitManager = mm;
			fileManager = new FileManager();
			clockTimer = new Timer(Constants.clockFrequency,
					new ActionListener() {

						@SuppressWarnings("synthetic-access")
						@Override
						public void actionPerformed(final ActionEvent e) {
							circuitManager.clockActiveCircuit();
						}

					});
		}
	}

	@Override
	public void addComponent(final Point position) {
		if (chosenGate != null) {
			circuitManager.addComponent(
					new GateWrapper(chosenGate.getGateClone()), position);
		}
	}

	@Override
	public void closeActiveWorkspace() {
		closeWorkspace(circuitManager.getActiveCircuitIndex());
	}

	@Override
	public boolean closeAllWorkspaces() {
		int size = circuitManager.getCircuits().size();
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (!closeWorkspace(counter)) {
				counter++;
			}
		}
		return circuitManager.getCircuits().size() == 0;
	}

	@Override
	public boolean closeWorkspace(final int i) {
		ICircuitWrapper m = circuitManager.getCircuit(i);
		boolean close = false;
		if (m != null) {
			int answer = saveMessage(m);
			if (answer == 0) {
				if (_saveWorkspace(m)) {
					close = true;
				}
			} else if (answer == 2) {
				close = true;
			}
		}
		if (close) {
			circuitManager.closeCircuit(i);
		}
		return close;
	}

	@Override
	public void connectComponent(final IGateWrapper g, final int port) {
		if (g == null) {
			connectComponent = null;
			return;
		} else if (connectComponent == null) {
			connectComponent = g;
			connectPort = port;
		} else {
			circuitManager.connectComponents(connectComponent, g, connectPort,
					port);
			connectComponent = null;
		}
	}

	@Override
	public void copySelectedComponents() {
		_copySelectedComponents();
	}

	@Override
	public void cutSelectedComponents() {
		_copySelectedComponents();
		circuitManager.removeSelectedComponents();
	}

	@Override
	public void importWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			List<IGateWrapper> importedComponents = fileManager.importFile(fc
					.getSelectedFile());
			circuitManager.addComponents(importedComponents);
			circuitManager.selectComponents(importedComponents);
		}
	}

	@Override
	public void newWorkspace() {
		circuitManager.newCircuit();
	}

	@Override
	public void openWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			ICircuitWrapper workspace = fileManager.openFile(fc
					.getSelectedFile());
			workspace.setChanged(false);
			circuitManager.addCircuit(workspace);
		}
	}

	@Override
	public void pasteSelectedComponents() {
		List<IGateWrapper> col = clipboardManager.paste();
		for (IGateWrapper gw : col) {
			gw.move(-Constants.componentSize, -Constants.componentSize);
		}
		circuitManager.addComponents(col);
		circuitManager.selectComponents(col);
		copySelectedComponents();
	}

	@Override
	public void pasteSelectedComponents(final Point position) {
		circuitManager.addComponents(clipboardManager.paste(), position);
		circuitManager.selectComponents(clipboardManager
				.getLastPastedComponents());
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeComponent(final IGateWrapper g) {
		circuitManager.removeComponent(g);
	}

	@Override
	public void removeSelectedComponents() {
		circuitManager.removeSelectedComponents();
	}

	@Override
	public void saveActiveWorkspace(final boolean saveAs) {
		if (saveAs) {
			_saveWorkspaceAs(circuitManager.getActiveCircuit());
		} else {
			_saveWorkspace(circuitManager.getActiveCircuit());
		}
	}

	@Override
	public void saveAllWorkspaces() {
		for (ICircuitWrapper imw : circuitManager.getCircuits()) {
			_saveWorkspace(imw);
		}
	}

	@Override
	public void selectAllComponents() {
		circuitManager.selectAllComponents();
	}

	@Override
	public void selectComponent(final Point position, final boolean multiSelect) {
		circuitManager.selectComponent(position, multiSelect);
	}

	@Override
	public void selectComponents(final Point pos1, final Point pos2) {
		circuitManager.selectComponents(pos1, pos2);

	}

	@Override
	public void setActiveWorkspace(final int i) {
		circuitManager.setActiveCircuit(i);
	}

	@Override
	public void setChosenComponent(final IGateWrapper g) {
		chosenGate = new GateWrapper(g.getGateClone());
	}

	@Override
	public void toggleClockTimer() {
		if (clockTimer.isRunning()) {
			clockTimer.stop();
		} else {
			clockTimer.start();
		}
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException();
	}

	private void _copySelectedComponents() {
		ISelectionModel s = circuitManager.getActiveSelectionModel();
		ICircuitWrapper m = circuitManager.getActiveCircuit();
		if (s != null && m != null) {
			if (s.getNumberOfComponents() != 0) {
				clipboardManager.copy(s.getSelectedComponents());
			}
		}
	}

	private boolean _saveWorkspace(final ICircuitWrapper imw) {
		if (circuitManager.getActiveCircuit() != null) {
			if (imw.getFile() != null) {
				fileManager.saveFile(imw.getComponents(), imw.getFile());
				imw.setChanged(false);
				circuitManager.manualPropertyChanged();
				return true;
			} else {
				return _saveWorkspaceAs(imw);
			}
		} else {
			return false;
		}
	}

	private boolean _saveWorkspaceAs(final ICircuitWrapper imw) {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			fileManager.saveFile(imw.getComponents(), fc.getSelectedFile());
			imw.setFile(fc.getSelectedFile());
			imw.setChanged(false);
			circuitManager.manualPropertyChanged();
			return true;
		} else {
			return false;
		}
	}

	private int saveMessage(final ICircuitWrapper model) {
		if (model.hasChanged()) {
			// Custom button text
			Object[] options = { "Yes", "Cancel", "No" };
			int answer = JOptionPane.showOptionDialog(null,
					"Would you like to save changes?", model.toString(),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			return answer;
		}
		return 2;
	}
}