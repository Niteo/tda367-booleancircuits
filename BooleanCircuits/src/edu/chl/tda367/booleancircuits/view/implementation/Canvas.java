package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.controller.implementation.MasterController;
import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.IObservable;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;
import edu.chl.tda367.booleancircuits.view.draw.IDraw;
import edu.chl.tda367.booleancircuits.view.draw.implementation.Draw;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas {

	private IModel model;
	private ISelectionModel selectModel;
	private IAbstractCircuitGate rightClickedGate = null;
	private static IDraw drawer = new Draw();
	private int posX, posY;
	private int zoomFactor;
	private Point oldDragPosition;
	private boolean connectMode;
	private boolean draggingMode;
	private MasterController mc;
	private CanvasPopup menu;
	private ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem menuItem = (JMenuItem) e.getSource();

			if (menu.isRemoveButton(menuItem)) {
				mc.removeComponent(rightClickedGate);
			} else {
				mc.connectComponent(rightClickedGate, menu.getPortIndex(menuItem));
				connectMode = !connectMode;
			}
		}

	};

	private JPanel panel = new JPanel() {
		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paint(g2d);
			// Draw background
			drawer.drawBackground(g2d, new Point(posX, posY), panel.getSize());
			// Draw components
			if (model != null) {
				for (IAbstractCircuitGate circuitGate : model.getComponents()) {
					// Set color
					if (selectModel.isSelectedComponent(circuitGate)) {
						g2d.setColor(Color.BLUE);
					} else {
						g2d.setColor(Color.BLACK);
					}
					// Draw component
					drawer.drawGate(g2d, circuitGate, new Point(posX, posY));
				}
			}
			// Draw position
			g2d.setColor(Color.BLACK);
			g2d.setFont(UIManager.getFont("TabbedPane.font"));
			g2d.drawString("[" + posX + ", " + posY + "]", 5, 15);
		}
	};

	private MouseAdapter mouseAdapter = new MouseInputAdapter() {
		
		@Override
		public void mouseDragged(MouseEvent evt) {
			if (oldDragPosition != null) {
				int dx = (int) (evt.getPoint().getX() - oldDragPosition.getX());
				int dy = (int) (evt.getPoint().getY() - oldDragPosition.getY());
				
				if(draggingMode){
					for(IAbstractCircuitGate selected : selectModel.getSelectedComponents()){
						selected.move(dx, dy);
					}
					panel.repaint();
				} else {
					IAbstractCircuitGate gate = model.getComponent(new Point(evt.getX() + posX, evt.getY() + posY));
					if(gate != null){
						if(selectModel.isSelectedComponent(gate)){
							draggingMode = true;
						}
					} else {
						panCanvas(-dx, -dy);
					}
				}
			}
			oldDragPosition = evt.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent evt) {
			oldDragPosition = null;
			draggingMode = false;
		}

		@Override
		public void mouseClicked(MouseEvent evt) {
			final Point pointClicked = new Point(evt.getX() + posX, evt.getY() + posY);

			if (evt.getButton() == MouseEvent.BUTTON1) { // LeftMouseButton
				connectMode = false;
				mc.connectComponent(null, -1);
				
				if (evt.getClickCount() == 1) {
					mc.selectComponent(pointClicked, evt.isControlDown());
				} else if (evt.getClickCount() == 2) {
					if(model.getComponent(pointClicked) == null){
						mc.addComponent(pointClicked);
					}
				}
			} else if (evt.getButton() == MouseEvent.BUTTON3) { // RightMouseButton

				rightClickedGate = model.getComponent(pointClicked);
				JPopupMenu jpm = new JPopupMenu();

				if (rightClickedGate == null) {
					JMenuItem tmpItem = new JMenuItem("Add component");
					tmpItem.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							mc.addComponent(pointClicked);
						}
						
					});
					jpm.add(tmpItem);
				} else {
					jpm = menu;
					menu.updateMenu(connectMode ? rightClickedGate.getNoOfOutputs()
							: rightClickedGate.getNoOfInputs(), !connectMode);
				}

				jpm.show(evt.getComponent(), (int) evt.getPoint().getX(),
						(int) evt.getPoint().getY());
			}
		}
	};

	public Canvas(IModel canvasModel, ISelectionModel selectionModel,
			MasterController masterController) {
		mc = masterController;
		posX = 0;
		posY = 0;
		zoomFactor = 0;
		model = canvasModel;
		selectModel = selectionModel;
		menu = new CanvasPopup(listener);

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
	 * 
	 * @param bool
	 */
	public static void setUSStandard(boolean bool) {
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

	private void panCanvas(int dx, int dy) {
		posX += dx;
		posY += dy;
		panel.repaint();
	}
}
