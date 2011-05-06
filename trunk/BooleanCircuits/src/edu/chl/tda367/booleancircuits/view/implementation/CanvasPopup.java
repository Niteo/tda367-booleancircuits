package edu.chl.tda367.booleancircuits.view.implementation;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Popup menu for canvas
 * 
 * @author Kaufmann
 * 
 */
public final class CanvasPopup extends JPopupMenu {
	private JMenuItem remove = new JMenuItem("Remove gate");
	private JMenu connect;

	/**
	 * Creates a pop-up menu with either inputs or outputs.
	 * 
	 * @param ports
	 *            int number of inputs or outputs
	 * @param isInput
	 *            boolean true if the menu is an input menu, false if output
	 *            menu
	 */
	public CanvasPopup(int ports, boolean isInput) {
		super();
		if (isInput) {
			connect = new JMenu("Connect input...");
		} else {
			connect = new JMenu("Connect output...");
		}
		for (int i = 1; i <= ports; i++) {
			connect.add(new JMenuItem("" + i));
		}
		add(connect);
		add(remove);
	}
}