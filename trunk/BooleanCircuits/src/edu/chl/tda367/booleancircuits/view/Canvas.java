package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.model.Model;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas {

	private JPanel panel;
	private Model model;

	public Canvas(Model model) {

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
			}
		};
		panel.setBackground(Color.WHITE);

		this.model = model;
	}

	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}
}
