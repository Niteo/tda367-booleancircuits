package edu.chl.tda367.booleancircuits.io;

import java.io.File;
import java.util.Collection;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.ModelWrapper;

public interface IFileManager {

	/**
	 * Saves a circuit in to a .txt file
	 * 
	 * @param components
	 *            the components in the circuit
	 * @param name
	 *            name of the save file
	 */
	public void saveFile(Collection<ICircuitGate> components, File file);

	/**
	 * Creates a circuit from a previously saved .txt file
	 * 
	 * @param file
	 *            txt. file representing a previously saved model
	 */
	public ModelWrapper openFile(File file);

	/**
	 * Imports all the components in one workspace into another active
	 * workspace.
	 * 
	 * @param file
	 * @return
	 */
	public List<ICircuitGate> importFile(File file);
}
