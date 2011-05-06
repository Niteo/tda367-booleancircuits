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
	private ActionListener listener;

	/**
	 * Creates a pop-up menu.
	 * 
	 * @param l
	 *            ActionListener
	 */
	public CanvasPopup(ActionListener l) {
		super();
		listener = l;

		remove.addActionListener(listener);

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
			JMenuItem temp = new JMenuItem("" + i);
			temp.addActionListener(listener);
			connect.add(temp);
		}

		connect.setEnabled(ports > 0);
	}

	/**
	 * Returns true if the JMenuItem is a remove button.
	 * 
	 * @param item
	 *            JMenuItem
	 * @return boolean
	 */
	public boolean isRemoveButton(JMenuItem item) {
		return remove == item;
	}
	
	public int getPortIndex(JMenuItem item){
		for(int i=0; i<connect.getItemCount();i++){
			if(item==connect.getItem(i)){
				return i;
			}
		}
		throw new IllegalArgumentException("Component does not match menu");
	}
}