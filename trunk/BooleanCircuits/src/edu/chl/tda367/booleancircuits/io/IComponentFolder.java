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
	 * returns a list of all components if folder;
	 *
	 * @return list of AbstractCircuitGate
	 */
	public List<ICircuitGate> getAllComponents();

	/**
	 * returns name of componentfolder
	 *
	 * @return
	 */
	public String getName();

}
