package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.event.ActionListener;

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
	private JMenu connect = new JMenu();

	/**
	 * Creates a pop-up menu.
	 */
	public CanvasPopup() {
		super();

		add(connect);
		add(remove);
	}

	/**
	 * Updates the pop-up menu with either inputs or outputs.
	 * 
	 * @param ports
	 *            int number of inputs or outputs
	 * @param isInput
	 *            boolean true if the menu is an input menu, false if output
	 *            menu
	 */
	public void updateMenu(int ports, boolean isInput) {
		if (isInput) {
			connect.setText("Connect input...");
		} else {
			connect.setText("Connect output...");
		}
		connect.removeAll();

		for (int i = 1; i <= ports; i++) {
			connect.add(new JMenuItem("" + i));
		}
		
		connect.setEnabled(ports > 0);
	}
}