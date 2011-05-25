package edu.chl.tda367.booleancircuits.model;

import java.io.File;

/**
 * An interface for a wrapper around IModel, containing file information.
 * 
 * @author Kaufmann
 * 
 */
public interface ICircuitWrapper extends ICircuit {

	/**
	 * Returns an instance of the model's corresponding file.
	 * 
	 * @return the file of the model
	 */
	public File getFile();

	/**
	 * Returns if true if the model has changed since last save
	 * 
	 * @return
	 */
	public boolean hasChanged();

	/**
	 * Returns true if this class has a save file.
	 * 
	 * @return boolean to indicate if class has save file
	 */
	public boolean hasFile();

	/**
	 * Set model to be changed or not based on input parameter.
	 * 
	 * @param isChanged
	 */
	public void setChanged(boolean isChanged);

	/**
	 * Sets the model's associated file.
	 * 
	 * @param file
	 *            the file to set.
	 */
	public void setFile(File file);
}
