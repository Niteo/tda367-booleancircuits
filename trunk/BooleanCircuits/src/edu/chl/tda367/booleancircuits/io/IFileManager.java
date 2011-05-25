package edu.chl.tda367.booleancircuits.io;

import java.io.File;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.ICircuitWrapper;
import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;

/**
 * Class for saving and loading circuits/workspaces.
 *
 * @author antonlin
 *
 */
public interface IFileManager {

	/**
	 * Returns a list of components from a given workspace.
	 *
	 * @param file
	 *            workspace to load
	 * @return list of components from given workspace
	 */
	public List<IGateWrapper> importFile(File file);

	/**
	 * Creates a circuit from a previously saved file
	 *
	 * @param file
	 *            file representing a previously saved model
	 * @return the circuit created from the given file
	 */
	public ICircuitWrapper openFile(File file);

	/**
	 * Saves a circuit to file
	 *
	 * @param components
	 *            the components in the circuit
	 * @param file
	 *            the file to save to
	 */
	public void saveFile(Collection<IGateWrapper> components, File file);
}
