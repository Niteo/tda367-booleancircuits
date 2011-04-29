package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.model.IObservable;
import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.view.draw.Draw;
import edu.chl.tda367.booleancircuits.view.draw.IDraw;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas implements IObservable {

	public static enum CanvasAction {
		DRAG, PLACE, SELECT
	}

	private JPanel panel;
	private Model model;
	private MouseAdapter mouseAdapter;
	private PropertyChangeSupport propertyChangeSupport;
	private static IDraw drawer;
	private int posX, posY;
	private int zoomFactor;

	public Canvas() {
		posX = 0;
		posY = 0;
		zoomFactor = 0;
		drawer = new Draw();
		propertyChangeSupport = new PropertyChangeSupport(this);
		mouseAdapter = new MouseInputAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				Point pointClicked = new Point(evt.getX() + posX, evt.getY() + posY);
				
				if(evt.getClickCount() == 1){
					propertyChangeSupport.firePropertyChange("Apa",
							null,
							new CanvasEvent(pointClicked,
									CanvasAction.SELECT));
				} else if (evt.getClickCount() == 2) {
					propertyChangeSupport.firePropertyChange("Apa",
							null,
							new CanvasEvent(pointClicked,
									CanvasAction.PLACE));
				}
			}
		};
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				// Draw background
				drawer.drawBackground(g, posX, posY);
				// Draw components
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						// Set color
						if(model.isASelectedComponent(circuitGate)){
							g.setColor(Color.BLUE);
						} else {
							g.setColor(Color.BLACK);
						}
						// Draw component
						drawer.drawGate(g, circuitGate, posX, posY);
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
	 * Sets the model which the canvas is currently representing
	 * 
	 * @param model the model to set
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
