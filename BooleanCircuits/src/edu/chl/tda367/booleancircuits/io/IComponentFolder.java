package edu.chl.tda367.booleancircuits.io;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;

/**
 * this interface describes a folder containing components
 * 
 * @author antonlin
 * 
 */
public interface IComponentFolder {

	/**
	 * Returns a list of all components in folder;
	 * 
	 * @return list of ICircuitGate
	 */
	public List<ICircuitGate> getAllComponents();

	/**
	 * Returns name of the folder.
	 * 
	 * @return
	 */
	public String getName();

}
