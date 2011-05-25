package edu.chl.tda367.booleancircuits.view;

import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.model.implementation.CircuitManager;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;

/**
 * An interface to describe a center stage.
 * 
 * @author Boel
 * 
 */
public interface ICenterStage {

	/**
	 * Returns the panel of the center stage.
	 * 
	 * @return center stage JPanel
	 */
	public JPanel getPanel();

	/**
	 * Returns the tabManager.
	 * 
	 * @return ITabManager
	 */
	public ITabManager getTabManager();

	/**
	 * Sets the background strategy of the canvas.
	 * 
	 * @param background
	 *            the stategy to use
	 */
	public void setBackground(IBackground background);

	/**
	 * Sets the representation to US standard. False is international.
	 * 
	 * @param bool
	 *            boolean
	 */
	public void setUSStandard(boolean bool);

	/**
	 * Updates the view by repainting the workspace.
	 * 
	 * @param a
	 *            reference to the modelmanager to pull data from
	 */
	public void update(CircuitManager modelManager);

}
