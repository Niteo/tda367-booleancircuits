package edu.chl.tda367.booleancircuits.io;

import java.util.List;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

public interface IComponentFolder {

	/**
	 * returns name of componentfolder
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * returns a list of all components if folder;
	 * 
	 * @return list of AbstractCircuitGate
	 */
	public List<ICircuitGate> getAllComponents();

}
