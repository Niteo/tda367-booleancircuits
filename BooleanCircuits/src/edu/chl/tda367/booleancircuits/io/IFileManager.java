package edu.chl.tda367.booleancircuits.io;

import java.io.File;
import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

public interface IFileManager {

	/**
	 * Saves a circuit in to a .txt file
	 * 
	 * @param components
	 *            the components in the circuit
	 * @param name
	 *            name of the save file
	 */
	public void saveFile(List<AbstractCircuitGate> components, String name);

	/**
	 * Creates a circuit from a previously saved .txt file
	 * 
	 * @param file
	 *            txt. file representing a previously saved model
	 */
	public void openFile(File file);
}
