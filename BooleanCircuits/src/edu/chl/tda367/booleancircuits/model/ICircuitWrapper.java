package edu.chl.tda367.booleancircuits.model;

import java.io.File;

/**
 * An interface for a wrapper around ICircuit, containing file information.
 * 
 * @author Kaufmann
 * 
 */
public interface ICircuitWrapper extends ICircuit {

	/**
	 * Returns an instance of the circuit's corresponding file.
	 * 
	 * @return the file of the model
	 */
	public File getFile();

	/**
	 * Returns if true if the circuit has changed since it was set to unchanged.
	 * 
	 * @return
	 */
	public boolean hasChanged();

	/**
	 * Returns true if this circuit has a save file.
	 * 
	 * @return boolean to indicate if circuit has save file
	 */
	public boolean hasFile();

	/**
	 * Set circuit to be changed or not based on input parameter.
	 * 
	 * @param isChanged
	 */
	public void setChanged(boolean isChanged);

	/**
	 * Sets the circuit's associated file.
	 * 
	 * @param file
	 *            the file to set.
	 */
	public void setFile(File file);
}
