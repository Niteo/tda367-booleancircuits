package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.NandGate;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas {

	private JPanel panel;
	private Model model;
	// Just for test
	private List<AbstractCircuitGate> testList;

	// Testkonstruktor
	public Canvas() {

		testList = new ArrayList<AbstractCircuitGate>();
		testList.add(new NandGate(0));

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				g.setColor(Color.black);
				for (AbstractCircuitGate circuitGate : testList) {
					g.drawRect(50, 70, 80, 70);
					g.drawString(circuitGate.toString(), 70, 110);
				}
			}
		};
		panel.setBackground(Color.WHITE);
	}

	public Canvas(final Model model) {

		this.model = model;
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				g.setColor(Color.black);
				for (AbstractCircuitGate circuitGate : testList) {
					g.drawRect(50, 70, 80, 70);
					g.drawString(circuitGate.toString(), 50, 60);
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
}
