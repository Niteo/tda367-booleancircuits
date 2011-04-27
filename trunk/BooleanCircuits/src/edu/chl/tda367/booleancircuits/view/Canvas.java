package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas {

	private JPanel panel;
	private Model model;

	public Canvas() {

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				g.setColor(Color.black);
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						g.drawRect(circuitGate.getPosition().x,
								circuitGate.getPosition().y, 80, 70);
						g.drawString(circuitGate.toString(),
								circuitGate.getPosition().x,
								circuitGate.getPosition().y);
					}
				}
			}
		};
		panel.setBackground(Color.WHITE);
	}

	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
