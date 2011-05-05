package edu.chl.tda367.booleancircuits.view;

import javax.swing.JScrollPane;

import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;

/**
 * An interface to describe a palette.
 * 
 * @author Boel
 * 
 */
public interface IPalette {

	/**
	 * Returns the scrollpane containing the panel and all visual objects of the
	 * palette.
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane getView();

	/**
	 * Returns the component currently selected in the palette.
	 * 
	 * @return AbstractCircuitGate
	 */
	public AbstractCircuitGate getSelectedComponent();

}
