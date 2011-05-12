package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.Timer;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.ClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

public final class MasterController implements IMasterController {

	private final IModelManager modelManager;
	private IAbstractCircuitGate connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private IAbstractCircuitGate chosenGate;
	private IClipboardManager clipboardManager = new ClipboardManager();
	private Timer clockTimer;

	/**
	 * Returns an instance of a MasterController
	 * 
	 * @param mm
	 *            the ModelManager to control
	 * @throws NullPointerException
	 *             if mm is null
	 */
	public MasterController(ModelManager mm) {
		if (mm == null) {
			throw new NullPointerException("mm must not be null!");
		} else {
			modelManager = mm;
			fileManager = new FileManager();
			clockTimer = new Timer(Constants.clockFrequency,
					new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							modelManager.clockActiveModel();
						}

					});
		}
	}

	@Override
	public void closeActiveWorkspace() {
		modelManager.closeActiveWorkspace();
	}

	@Override
	public void closeAllWorkspaces() {
		modelManager.closeAllWorkspaces();
	}

	@Override
	public void closeWorkspace(int i) {
		modelManager.closeWorkspace(i);
	}

	@Override
	public void newWorkspace() {
		modelManager.newWorkspace();
	}

	@Override
	public void openWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			IModelWrapper workspace = fileManager
					.openFile(fc.getSelectedFile());
			workspace.setChangedFalse();
			modelManager.addWorkspace(workspace);
		}
	}

	@Override
	public void saveActiveWorkspace(boolean saveAs) {
		if (saveAs) {
			_saveWorkspaceAs(modelManager.getActiveWorkspaceModel());
		} else {
			_saveWorkspace(modelManager.getActiveWorkspaceModel());
		}
	}

	@Override
	public void saveAllWorkspaces() {
		for (IModelWrapper imw : modelManager.getWorkspaces()) {
			_saveWorkspace(imw);
		}
	}

	private void _saveWorkspace(IModelWrapper imw) {
		if (imw.getFile() != null) {
			fileManager.saveFile(imw.getComponents(), imw.getFile());
			imw.setChangedFalse();
			modelManager.manualPropertyChanged();
		} else {
			_saveWorkspaceAs(imw);
		}
	}

	private void _saveWorkspaceAs(IModelWrapper imw) {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			fileManager.saveFile(imw.getComponents(), fc.getSelectedFile());
			imw.setFile(fc.getSelectedFile());
			imw.setChangedFalse();
			modelManager.manualPropertyChanged();
		}
	}

	@Override
	public void setActiveWorkspace(int i) {
		modelManager.setActiveWorkspace(i);
	}

	@Override
	public void undo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addComponent(Point position) {
		if (chosenGate != null) {
			modelManager.addComponent(chosenGate.clone(), position);
		}
	}

	@Override
	public void removeSelectedComponents() {
		modelManager.removeSelectedComponents();
	}

	@Override
	public void selectAllComponents() {
		modelManager.selectAllComponents();
	}

	@Override
	public void selectComponent(Point position, boolean multiSelect) {
		modelManager.selectComponent(position, multiSelect);
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
	public void pasteSelectedComponents() {
		modelManager.addComponents(clipboardManager.paste());
	}

	@Override
	public void pasteSelectedComponents(Point position) {
		modelManager.addComponents(clipboardManager.paste(), position);
		modelManager.getActiveSelectionModel().selectComponents(
				clipboardManager.getLastPastedComponents());
	}

	@Override
	public void setChosenComponent(IAbstractCircuitGate g) {
		chosenGate = g.clone();
	}

	@Override
	public void removeComponent(IAbstractCircuitGate g) {
		modelManager.removeComponent(g);
	}

	@Override
	public void connectComponent(IAbstractCircuitGate g, int port) {
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
	public void toggleClockTimer() {
		if (clockTimer.isRunning()) {
			clockTimer.stop();
		} else {
			clockTimer.start();
		}
	}

	private void _copySelectedComponents() {
		clipboardManager.copy(modelManager.getActiveSelectionModel()
				.getSelectedComponents());
	}

	@Override
	public void importWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			modelManager.addComponents(fileManager.importFile(fc
					.getSelectedFile()));
		}
	}
}