package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.GateWrapper;
import edu.chl.tda367.booleancircuits.model.implementation.CircuitManager;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.ClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

public final class MasterController implements IMasterController {

	private IGateWrapper chosenGate;
	private IClipboardManager clipboardManager = new ClipboardManager();
	private Timer clockTimer;
	private IGateWrapper connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private final ICircuitManager modelManager;

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
			modelManager = mm;
			fileManager = new FileManager();
			clockTimer = new Timer(Constants.clockFrequency,
					new ActionListener() {

						@SuppressWarnings("synthetic-access")
						@Override
						public void actionPerformed(final ActionEvent e) {
							modelManager.clockActiveCircuit();
						}

					});
		}
	}

	@Override
	public void addComponent(final Point position) {
		if (chosenGate != null) {
			modelManager.addComponent(
					new GateWrapper(chosenGate.getGateClone()), position);
		}
	}

	@Override
	public void closeActiveWorkspace() {
		closeWorkspace(modelManager.getActiveCircuitIndex());
	}

	@Override
	public boolean closeAllWorkspaces() {
		int size = modelManager.getCircuits().size();
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (!closeWorkspace(counter)) {
				counter++;
			}
		}
		return modelManager.getCircuits().size() == 0;
	}

	@Override
	public boolean closeWorkspace(final int i) {
		ICircuitWrapper m = modelManager.getCircuit(i);
		boolean close = false;
		if(m != null){
			int answer = saveMessage(m);
			if (answer == 0) {
				if (_saveWorkspace(m)) {
					close = true;
				}
			} else if (answer == 2) {
				close =  true;
			}
		}
		if(close) {
			modelManager.closeCircuit(i);
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
			modelManager.connectComponents(connectComponent, g, connectPort,
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
		modelManager.removeSelectedComponents();

	}

	@Override
	public void importWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			List<IGateWrapper> importedComponents = fileManager.importFile(fc
					.getSelectedFile());
			modelManager.addComponents(importedComponents);
			modelManager.selectComponents(importedComponents);
		}
	}

	@Override
	public void newWorkspace() {
		modelManager.newCircuit();
	}

	@Override
	public void openWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			ICircuitWrapper workspace = fileManager
					.openFile(fc.getSelectedFile());
			workspace.setChanged(false);
			modelManager.addCircuit(workspace);
		}
	}

	@Override
	public void pasteSelectedComponents() {
		List<IGateWrapper> col = clipboardManager.paste();
		for(IGateWrapper gw : col){
			gw.move(-Constants.componentSize, -Constants.componentSize);
		}
		modelManager.addComponents(col);
		modelManager.selectComponents(col);
		copySelectedComponents();
	}

	@Override
	public void pasteSelectedComponents(final Point position) {
		modelManager.addComponents(clipboardManager.paste(), position);
		modelManager.selectComponents(clipboardManager
				.getLastPastedComponents());
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeComponent(final IGateWrapper g) {
		modelManager.removeComponent(g);
	}

	@Override
	public void removeSelectedComponents() {
		modelManager.removeSelectedComponents();
	}

	@Override
	public void saveActiveWorkspace(final boolean saveAs) {
		if (saveAs) {
			_saveWorkspaceAs(modelManager.getActiveCircuit());
		} else {
			_saveWorkspace(modelManager.getActiveCircuit());
		}
	}

	@Override
	public void saveAllWorkspaces() {
		for (ICircuitWrapper imw : modelManager.getCircuits()) {
			_saveWorkspace(imw);
		}
	}

	@Override
	public void selectAllComponents() {
		modelManager.selectAllComponents();
	}

	@Override
	public void selectComponent(final Point position, final boolean multiSelect) {
		modelManager.selectComponent(position, multiSelect);
	}

	@Override
	public void selectComponents(final Point pos1, final Point pos2) {
		modelManager.selectComponents(pos1, pos2);

	}

	@Override
	public void setActiveWorkspace(final int i) {
		modelManager.setActiveCircuit(i);
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
		ISelectionModel s = modelManager.getActiveSelectionModel();
		ICircuitWrapper m = modelManager.getActiveCircuit();
		if (s != null && m != null){
			if(m.getNumberOfComponents() != 0){
				clipboardManager.copy(modelManager.getActiveSelectionModel()
						.getSelectedComponents());
			}
		}
	}

	private boolean _saveWorkspace(final ICircuitWrapper imw) {
		if (modelManager.getActiveCircuit() != null) {
			if (imw.getFile() != null) {
				fileManager.saveFile(imw.getComponents(), imw.getFile());
				imw.setChanged(false);
				modelManager.manualPropertyChanged();
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
			modelManager.manualPropertyChanged();
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