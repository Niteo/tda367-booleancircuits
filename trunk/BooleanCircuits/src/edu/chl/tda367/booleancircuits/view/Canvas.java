package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

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
	private MouseAdapter mouseAdapter;

	public Canvas() {

		mouseAdapter = new MouseInputAdapter() {
			public Point mouseLocation;

			@Override
			public void mouseClicked(MouseEvent evt) {
				mouseLocation = evt.getPoint();
			}
		};

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				g.setColor(Color.black);
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						g.drawRect(circuitGate.getPosition().getX(),
								circuitGate.getPosition().getY(), 80, 70);
						g.drawString(circuitGate.toString(),
								circuitGate.getPosition().getX(),
								circuitGate.getPosition().getY());
					}
				}
			}
		};
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(mouseAdapter);
	}

	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}

	/**
	 * sets the model which the canvas is currently representing
	 * 
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}
}
