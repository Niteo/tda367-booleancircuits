package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.controller.implementation.MasterController;
import edu.chl.tda367.booleancircuits.model.IModel;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
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

	private static IDraw drawer = new Draw();
	private IModel model;
	private ISelectionModel selectModel;
	private ICircuitGate rightClickedGate = null;
	private ICircuitGate connectBufferGate = null;
	private int connectBufferPort = 0;
	private int posX, posY;
	private Point oldDragPosition;
	private boolean connectingInput;
	private boolean connectingOutput;
	private boolean draggingMode;
	private MasterController _masterController;
	private CanvasPopup menu;
	private Point drawSelect;
	private ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem menuItem = (JMenuItem) e.getSource();

			if (menu.isRemoveButton(menuItem)) {
				_masterController.removeComponent(rightClickedGate);
			} else if (menu.isInputItem(menuItem)) {
				_masterController.connectComponent(rightClickedGate,
						menu.getInputIndex(menuItem));
				if (connectingOutput) {
					_masterController.connectComponent(connectBufferGate,
							connectBufferPort);
					resetConnecting();
				} else {
					connectingInput = true;
				}
			} else if (menu.isOutputItem(menuItem)) {
				if (connectingInput) {
					_masterController.connectComponent(rightClickedGate,
							menu.getInputIndex(menuItem));
					resetConnecting();
				} else {
					connectingOutput = true;
					connectBufferGate = rightClickedGate;
				}
			} else if (menu.isCopyButton(menuItem)) {
				selectModel.selectComponent(rightClickedGate, false);
				_masterController.copySelectedComponents();
			} else if (menu.isCutButton(menuItem)) {
				selectModel.selectComponent(rightClickedGate, false);
				_masterController.cutSelectedComponents();
			}
		}

	};

	private JPanel panel = new JPanel() {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			// Draw background
			drawer.drawBackground(g2d, new Point(posX, posY), panel.getSize());

			// Draw components
			if (model != null) {
				// Draw non-selected
				g2d.setColor(Color.BLACK);
				for (ICircuitGate circuitGate : model.getComponents()) {
					if (!selectModel.isSelectedComponent(circuitGate)) {
						drawer.drawGate(g2d, circuitGate, new Point(posX, posY));
					}
				}
				// Draw selected
				g2d.setColor(Color.BLUE);
				for (ICircuitGate circuitGate : model.getComponents()) {
					if (selectModel.isSelectedComponent(circuitGate)) {
						drawer.drawGate(g2d, circuitGate, new Point(posX, posY));
					}
				}
			}

			// Draw select
			if (drawSelect != null) {
				Point mousePos = panel.getMousePosition();
				if (mousePos != null) {
					g.setColor(new Color(100, 100, 240, 140));
					g2d.fillRect(drawSelect.x, drawSelect.y,
							panel.getMousePosition().x - drawSelect.x,
							panel.getMousePosition().y - drawSelect.y);
					g.setColor(new Color(20, 20, 240, 180));

					int[] xArray = { drawSelect.x, mousePos.x, mousePos.x,
							drawSelect.x };
					int[] yArray = { drawSelect.y, drawSelect.y, mousePos.y,
							mousePos.y };
					g2d.drawPolygon(xArray, yArray, 4);
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
			Point dragPosition = new Point(evt.getX() + posX, evt.getY() + posY);
			if (oldDragPosition != null) {
				int dx = (int) (evt.getPoint().getX() - oldDragPosition.getX());
				int dy = (int) (evt.getPoint().getY() - oldDragPosition.getY());

				if (evt.isShiftDown()) {
					if (drawSelect == null) {
						drawSelect = evt.getPoint();
					}
					panel.repaint();
				} else if (draggingMode) {
					// If not a selected component, select it.
					ICircuitGate clickedComponent = model
							.getComponent(dragPosition);
					if (clickedComponent != null) {
						if (!selectModel.isSelectedComponent(clickedComponent)) {
							selectModel
									.selectComponent(clickedComponent, false);
						}
					}
					// Move all selected components.
					for (ICircuitGate selected : selectModel
							.getSelectedComponents()) {
						selected.move(dx, dy);
					}
					panel.repaint();
				} else {
					ICircuitGate gate = model.getComponent(dragPosition);
					if (gate != null) {
						draggingMode = true;
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
			final Point releasePoint = evt.getPoint();
			draggingMode = false;
			if (drawSelect != null) {
				_masterController.selectComponents(new Point(drawSelect.x
						+ posX, drawSelect.y + posY), new Point(releasePoint.x
						+ posX, releasePoint.y + posY));
				drawSelect = null;
				panel.repaint();
			}
		}

		@Override
		public void mouseClicked(MouseEvent evt) {
			mouseClickedActions(evt);
		}
	};

	public Canvas(IModel canvasModel, ISelectionModel selectionModel,
			MasterController masterController) {
		_masterController = masterController;
		posX = 0;
		posY = 0;
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

	private void resetConnecting() {
		connectBufferGate = null;
		connectingInput = false;
		connectingOutput = false;
		_masterController.connectComponent(null, -1);
	}

	private void mouseClickedActions(MouseEvent evt) {
		final Point pointClicked = new Point(evt.getX() + posX, evt.getY()
				+ posY);
		if (evt.getButton() == MouseEvent.BUTTON1) { // LeftMouseButton
			resetConnecting();

			if (evt.getClickCount() == 1) {
				_masterController.selectComponent(pointClicked,
						evt.isShiftDown());
			} else if (evt.getClickCount() == 2) {
				if (model.getComponent(pointClicked) == null) {
					_masterController.addComponent(pointClicked);
				}
			}
		} else if (evt.getButton() == MouseEvent.BUTTON3) { // RightMouseButton

			rightClickedGate = model.getComponent(pointClicked);
			JPopupMenu jpm = new JPopupMenu();

			if (rightClickedGate == null) {
				final JMenuItem addItem = new JMenuItem("Add component");
				final JMenuItem copyItem = new JMenuItem("Copy selected");
				final JMenuItem cutItem = new JMenuItem("Cut selected");
				final JMenuItem pasteItem = new JMenuItem("Paste");
				ActionListener menuListener = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (arg0.getSource() == addItem) {
							_masterController.addComponent(pointClicked);
						} else if (arg0.getSource() == cutItem) {
							_masterController.cutSelectedComponents();
						} else if (arg0.getSource() == copyItem) {
							_masterController.copySelectedComponents();
						} else if (arg0.getSource() == pasteItem) {
							_masterController
									.pasteSelectedComponents(pointClicked);
						}
					}

				};
				addItem.addActionListener(menuListener);
				cutItem.addActionListener(menuListener);
				copyItem.addActionListener(menuListener);
				pasteItem.addActionListener(menuListener);
				jpm.add(addItem);
				jpm.addSeparator();
				jpm.add(copyItem);
				jpm.add(cutItem);
				jpm.add(pasteItem);
			} else {
				jpm = menu;
				menu.updateMenu(rightClickedGate.getNoOfInputs(),
						rightClickedGate.getNoOfOutputs(), !connectingInput,
						!connectingOutput);
			}

			jpm.show(evt.getComponent(), (int) evt.getPoint().getX(), (int) evt
					.getPoint().getY());
		}
	}
}