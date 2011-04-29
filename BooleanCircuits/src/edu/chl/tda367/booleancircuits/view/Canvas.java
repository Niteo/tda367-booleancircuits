package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.model.IObservable;
import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas implements IObservable {

	public static enum CanvasAction {
		DRAG, PLACE
	}

	private JPanel panel;
	private Model model;
	private MouseAdapter mouseAdapter;
	private CanvasEvent canvasEvent;
	private PropertyChangeSupport propertyChangeSupport;

	public Canvas() {
		propertyChangeSupport = new PropertyChangeSupport(this);

		mouseAdapter = new MouseInputAdapter() {

			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					canvasEvent = new CanvasEvent(evt.getPoint(),
							CanvasAction.PLACE);
					propertyChangeSupport
					.firePropertyChange("Apa", null, canvasEvent);
				}
			}
		};

		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				// TODO implement paint
				super.paint(g);
				g.setColor(Color.black);
				
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						g.drawRect((int) circuitGate.getPosition().getX(),
								(int) circuitGate.getPosition().getY(), 80, 70);
						g.drawString(circuitGate.toString(), (int) circuitGate
								.getPosition().getX(), (int) circuitGate
								.getPosition().getY());
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

	public CanvasEvent getLastAction() {
		return canvasEvent;
	}

	/**
	 * sets the model which the canvas is currently representing
	 * 
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
}
