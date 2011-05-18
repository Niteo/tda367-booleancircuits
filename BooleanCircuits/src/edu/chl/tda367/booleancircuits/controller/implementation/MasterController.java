package edu.chl.tda367.booleancircuits.controller.implementation;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.io.IFileManager;
import edu.chl.tda367.booleancircuits.io.implementation.FileManager;
import edu.chl.tda367.booleancircuits.model.IModelManager;
import edu.chl.tda367.booleancircuits.model.IModelWrapper;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.utilities.IClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.ClipboardManager;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;

public final class MasterController implements IMasterController {

	private final IModelManager modelManager;
	private ICircuitGate connectComponent = null;
	private int connectPort = -1;
	private IFileManager fileManager;
	private ICircuitGate chosenGate;
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
	public boolean closeAllWorkspaces() {
		int size = modelManager.getWorkspaces().size();
		int counter = 0;
		for (int i = 0; i < size; i++) {
			if (!closeWorkspace(counter)) {
				counter++;
			}
		}
		return modelManager.getWorkspaces().size() == 0;
	}

	@Override
	public boolean closeWorkspace(int i) {
		if (i >= 0 && i < modelManager.getWorkspaces().size()) {
			int answer = saveMessage(modelManager.getWorkspace(i));
			boolean closed = true;
			if (answer == 0) {
				if (_saveWorkspace(modelManager.getWorkspace(i))) {
					modelManager.closeWorkspace(i);
				} else {
					closed = false;
				}
			} else if (answer == 1) {
				closed = false;
			} else {
				modelManager.closeWorkspace(i);
			}
			return closed;
		} else {
			return false;
		}
	}

	private int saveMessage(IModelWrapper model) {
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
			workspace.setChanged(false);
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

	private boolean _saveWorkspace(IModelWrapper imw) {
		if (modelManager.getActiveWorkspaceModel() != null) {
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

	private boolean _saveWorkspaceAs(IModelWrapper imw) {
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
		if (_copySelectedComponents()) {
			modelManager.removeSelectedComponents();
		}
	}

	@Override
	public void pasteSelectedComponents() {
		if (modelManager.getActiveSelectionModel() != null
				&& modelManager.getActiveSelectionModel()
						.getNumberOfComponents() != 0) {
			modelManager.addComponents(clipboardManager.paste());
			modelManager.getActiveSelectionModel().selectComponents(
					clipboardManager.getLastPastedComponents());
		}
	}

	@Override
	public void pasteSelectedComponents(Point position) {
		if (modelManager.getActiveSelectionModel() != null
				&& modelManager.getActiveSelectionModel()
						.getNumberOfComponents() != 0) {
			modelManager.addComponents(clipboardManager.paste(), position);
			modelManager.getActiveSelectionModel().selectComponents(
					clipboardManager.getLastPastedComponents());
		}
	}

	@Override
	public void setChosenComponent(ICircuitGate g) {
		chosenGate = g.clone();
	}

	@Override
	public void removeComponent(ICircuitGate g) {
		modelManager.removeComponent(g);
	}

	@Override
	public void connectComponent(ICircuitGate g, int port) {
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

	private boolean _copySelectedComponents() {
		if (modelManager.getActiveSelectionModel() == null
				|| modelManager.getActiveSelectionModel()
						.getNumberOfComponents() == 0) {
			return false;
		} else {
			clipboardManager.copy(modelManager.getActiveSelectionModel()
					.getSelectedComponents());
			return true;
		}
	}

	@Override
	public void importWorkspace() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			modelManager.addComponents(fileManager.importFile(fc
					.getSelectedFile()));
		}
	}

	@Override
	public void selectComponents(Point pos1, Point pos2) {
		modelManager.selectComponents(pos1, pos2);

	}
}