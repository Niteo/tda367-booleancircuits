package edu.chl.tda367.booleancircuits.view.implementation;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * Popup menu for canvas
 * @author Kaufmann
 *
 */
public final class CanvasPopup extends JPopupMenu{
	JMenuItem remove = new JMenuItem("Remove gate");
	JMenu connect = new JMenu("Connect input...");
	
	public CanvasPopup(int inputs){
		super();
		for(int i = 1; i <= inputs; i++){
			connect.add(new JMenuItem("" + i));
		}
		add(connect);
		add(remove);
	}
}