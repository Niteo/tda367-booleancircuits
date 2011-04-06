package edu.chl.tda367.booleancircuits.view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import edu.chl.tda367.booleancircuits.model.ModelManager;

/**
 * A class where the components are drawn.
 * 
 * @author Boel
 * 
 */
public class Canvas {

	private JPanel panel = new JPanel() {
		@Override
		public void paint(Graphics g) {
			// TODO implement paint
		}
	};



	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}

}
