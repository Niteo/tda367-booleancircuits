package edu.chl.tda367.booleancircuits.utilities;

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
