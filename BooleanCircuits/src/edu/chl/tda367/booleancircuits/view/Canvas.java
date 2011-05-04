package edu.chl.tda367.booleancircuits.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.model.IObservable;
import edu.chl.tda367.booleancircuits.model.Model;
import edu.chl.tda367.booleancircuits.model.ModelManager;
import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.view.draw.Draw;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;
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
	private static IDraw drawer = new Draw();
	private int posX, posY;
	private int zoomFactor;
	private Point oldDragPosition;

	public Canvas(Model canvasModel) {
		posX = 0;
		posY = 0;
		zoomFactor = 0;
		model = canvasModel;
		propertyChangeSupport = new PropertyChangeSupport(this);
		mouseAdapter = new MouseInputAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent evt) {
				if(oldDragPosition != null){
					int dx = (int)(evt.getPoint().getX() - oldDragPosition.getX());
					int dy = (int)(evt.getPoint().getY() - oldDragPosition.getY());
					panCanvas(dx, dy);
				}
				oldDragPosition = evt.getPoint();
			}
			
			@Override
			public void mouseClicked(MouseEvent evt) {
				Point pointClicked = new Point(evt.getX() + posX, evt.getY()
						+ posY);

				if (evt.getButton() == MouseEvent.BUTTON1) { // LMB
					if (evt.getClickCount() == 1) {
						propertyChangeSupport.firePropertyChange("Apa", null,
								new CanvasEvent(pointClicked,
										CanvasAction.SELECT));
					} else if (evt.getClickCount() == 2) {
						propertyChangeSupport.firePropertyChange("Apa", null,
								new CanvasEvent(pointClicked,
										CanvasAction.PLACE));
					}
				} else if (evt.getButton() == MouseEvent.BUTTON3) { // RMB
					// TODO: ADd stuff
				}
			}
		};
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				// Draw background
				drawer.drawBackground(g, new Point(posX, posY), panel.getSize());
				// Draw components
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						// Set color
						if (model.isASelectedComponent(circuitGate)) {
							g.setColor(Color.BLUE);
						} else {
							g.setColor(Color.BLACK);
						}
						// Draw component
						drawer.drawGate(g, circuitGate, new Point(posX, posY));
					}
				}
			}
		};
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(mouseAdapter);
	}
	
	//public void 

	/**
	 * Returns the canvas.
	 * 
	 * @return JPanel
	 */
	public JPanel getCanvas() {
		return panel;
	}
	
	/**
	 * Sets US standard. False is international.
	 * @param bool 
	 */
	public static void setUSStandard(boolean bool){
		
	}

	/**
	 * Sets the background of the canvas.
	 * 
	 * @param background
	 *            IBackground
	 */
	public static void setBackground(IBackground background) {
		drawer.setBackground(background);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	
	private void panCanvas(int dx, int dy){
		posX += dx;
		posY += dy;
	}
}
