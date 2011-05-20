package edu.chl.tda367.booleancircuits.view;

import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.model.implementation.ModelManager;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;
import edu.chl.tda367.booleancircuits.view.implementation.TabManager;

/**
 * An interface to describe a center stage.
 * 
 * @author Boel
 * 
 */
public interface ICenterStage {

	/**
	 * Returns the panel of the centerStage.
	 * 
	 * @return canvas JPanel
	 */
	public JPanel getPanel();

	/**
	 * Returns the tabManager.
	 * 
	 * @return TabManager
	 */
	public TabManager getTabManager();

	/**
	 * Sets the background of the canvas.
	 * 
	 * @param background
	 *            IBackground
	 */
	public void setBackground(IBackground background);

	/**
	 * Sets the representation to US standard. False is international.
	 * 
	 * @param bool
	 *            Boolean
	 */
	public void setUSStandard(boolean bool);

	/**
	 * Updates the view by repainting the workspace.
	 * 
	 * @param modelManager
	 */
	public void update(ModelManager modelManager);

}
