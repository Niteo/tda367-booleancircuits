package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.implementation.Model;
import edu.chl.tda367.booleancircuits.utilities.IObservable;
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
	private IModel model;
	private ISelectionModel selectModel;
	private MouseAdapter mouseAdapter;
	private PropertyChangeSupport propertyChangeSupport;
	private static IDraw drawer = new Draw();
	private int posX, posY;
	private int zoomFactor;
	private Point oldDragPosition;
	
	public Canvas(IModel canvasModel, ISelectionModel selectionModel) {
		posX = 0;
		posY = 0;
		zoomFactor = 0;
		model = canvasModel;
		selectModel = selectionModel;
		propertyChangeSupport = new PropertyChangeSupport(this);
		mouseAdapter = new MouseInputAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent evt) {
				if(oldDragPosition != null){
					int dx = (int)(evt.getPoint().getX() - oldDragPosition.getX());
					int dy = (int)(evt.getPoint().getY() - oldDragPosition.getY());
					panCanvas(-dx, -dy);
				}
				oldDragPosition = evt.getPoint();
			}
			
			@Override
			public void mouseReleased(MouseEvent evt){
				oldDragPosition = null;
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
					
					AbstractCircuitGate gate = model.getComponent(evt.getPoint());
					JPopupMenu jpm = new JPopupMenu();
					
					if(gate == null){
						jpm.add(new JMenuItem("LOLFAG. NO COMPONENTS THERE! >:"));
					} else {
						jpm = new CanvasPopup(gate.getNoOfInputs());
					}
					
					jpm.show(evt.getComponent(),
							(int)evt.getPoint().getX(),
							(int)evt.getPoint().getY());
				}
			}
		};
		
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				super.paint(g2d);
				// Draw background
				drawer.drawBackground(g2d, new Point(posX, posY), panel.getSize());
				// Draw components
				if (model != null) {
					for (AbstractCircuitGate circuitGate : model
							.getComponents()) {
						// Set color
						if (selectModel.isSelectedComponent(circuitGate)) { // TODO: <----FIX THIS SHIT
							g2d.setColor(Color.BLUE);
						} else {
							g2d.setColor(Color.BLACK);
						}
						// Draw component
						drawer.drawGate(g2d, circuitGate, new Point(posX, posY));
					}
				}
				//Draw position
				g2d.setColor(Color.BLACK);
				g2d.drawString("[" + posX + ", " + posY + "]", 5, 15);
			}
		};
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(mouseAdapter);
		panel.addMouseMotionListener(mouseAdapter);
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
	 * Sets US standard. False is international.
	 * @param bool 
	 */
	public static void setUSStandard(boolean bool){
		drawer.setUsStandard(bool);
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
		panel.repaint();
	}
}
