package edu.chl.tda367.booleancircuits.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A listener class to controll action events.
 * @author Boel
 *
 */
public class ActionController implements ActionListener {

	private MasterController mc;

	/**
	 * Returns an instance of ActionController.
	 * @param MasterController
	 */
	public ActionController(MasterController masterController) {
		mc = masterController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Add action performed for all buttons
		System.out.println(e.getSource().toString());
	}

}
