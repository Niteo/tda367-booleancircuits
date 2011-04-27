package edu.chl.tda367.booleancircuits.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface IObservable {

	/**
	 * Adds an observer to this observable class.
	 * @param listener the observer
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * Removes an observer from this observable class.
	 * @param listener to remove
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);
}