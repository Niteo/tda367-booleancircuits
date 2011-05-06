package edu.chl.tda367.booleancircuits.io;

import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.Model;

public interface IFileManager {

	/**
	 * Saves a circuit in to a .txt file
	 * 
	 * @param components
	 *            the components in the circuit
	 * @param name
	 *            name of the save file
	 */
	public void saveFile(Collection<IAbstractCircuitGate> components, String name);

	/**
	 * Creates a circuit from a previously saved .txt file
	 * 
	 * @param file
	 *            txt. file representing a previously saved model
	 */
	public Model openFile(String path);
}
