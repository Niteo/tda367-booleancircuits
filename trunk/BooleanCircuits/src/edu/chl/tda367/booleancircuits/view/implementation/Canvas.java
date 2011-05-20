package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.*;
import java.awt.event.*;
import java.security.InvalidParameterException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.controller.implementation.MasterController;
import edu.chl.tda367.booleancircuits.model.*;
import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.*;
import edu.chl.tda367.booleancircuits.view.draw.implementation.Draw;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas {

	private static IDraw drawer = new Draw();

	/**
	 * Sets the background of the canvas.
	 * 
	 * @param background
	 *            IBackground
	 */
	public static void setBackground(final IBackground background) {
		drawer.setBackground(background);
	}

	/**
	 * Sets US standard. False is international.
	 * 
	 * @param bool
	 */
	public static void setUSStandard(final boolean bool) {
		drawer.setUsStandard(bool);
	}

	private final IMasterController _masterController;
	private ICircuitGate connectBufferGate = null;
	private int connectBufferPort = 0;
	private boolean connectingInput;
	private boolean connectingOutput;
	private boolean draggingMode;
	private Point drawSelect;
	private final ActionListener listener = new ActionListener() {

		@SuppressWarnings("synthetic-access")
		@Override
		public void actionPerformed(final ActionEvent e) {
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
	private final CanvasPopup menu;
	private final IModel model;
	private final MouseAdapter mouseAdapter = new MouseInputAdapter() {
		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseClicked(final MouseEvent evt) {
			mouseClickedActions(evt);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseDragged(final MouseEvent evt) {
			Point dragPosition = new Point(evt.getX() + posX, evt.getY() + posY);
			if (oldDragPosition != null) {
				int dx = (int) (evt.getPoint().getX() - oldDragPosition.getX());
				int dy = (int) (evt.getPoint().getY() - oldDragPosition.getY());

				if (evt.isShiftDown()) {
					if (drawSelect == null) {
						drawSelect = evt.getPoint();
					}
				} else if (draggingMode) {
					// Move all selected components.
					for (ICircuitGate selected : selectModel
							.getSelectedComponents()) {
						selected.move(dx, dy);
					}
				} else {
					ICircuitGate gate = model.getComponent(dragPosition);
					if (gate != null && !panning) {
						draggingMode = true;
						if (!selectModel.isSelectedComponent(gate)) {
							selectModel.selectComponent(gate, false);
						}
					} else {
						panCanvas(-dx, -dy);
						panning = true;
					}
				}
			}
			oldDragPosition = evt.getPoint();
			panel.repaint();
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseReleased(final MouseEvent evt) {
			final Point releasePoint = evt.getPoint();
			oldDragPosition = null;
			draggingMode = false;
			panning = false;
			if (drawSelect != null) {
				_masterController.selectComponents(new Point(drawSelect.x
						+ posX, drawSelect.y + posY), new Point(releasePoint.x
						+ posX, releasePoint.y + posY));
				drawSelect = null;
				panel.repaint();
			}
		}
	};
	private Point oldDragPosition;
	private final JPanel panel = new JPanel() {

		private static final long serialVersionUID = 1L;

		@SuppressWarnings("synthetic-access")
		@Override
		public void paint(final Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;

			drawer.drawBackground(g2d, new Point(posX, posY), panel.getSize());
			// Draw connections
			for (ICircuitGate circuitGate : model.getComponents()) {
				drawer.drawGateConnections(g2d, circuitGate, new Point(posX,
						posY));
			}
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
			// Draw position
			g2d.setColor(Color.BLACK);
			g2d.setFont(UIManager.getFont("TabbedPane.font"));
			g2d.drawString("[" + posX + ", " + posY + "]", 5, 15);
			if (model.hasInfiniteRecursion()) {
				g2d.setColor(Color.RED);
				g2d.drawString("Infinite recursion!", 5, 35);
			}
			// Draw selection box
			if (drawSelect != null) {
				Point mousePos = panel.getMousePosition();
				if (mousePos != null) {
					g.setColor(Constants.drawSelectFill);

					int startX = drawSelect.x < mousePos.x ? drawSelect.x
							: mousePos.x;
					int sizeX = drawSelect.x < mousePos.x ? mousePos.x
							- drawSelect.x : drawSelect.x - mousePos.x;
					int startY = drawSelect.y < mousePos.y ? drawSelect.y
							: mousePos.y;
					int sizeY = drawSelect.y < mousePos.y ? mousePos.y
							- drawSelect.y : drawSelect.y - mousePos.y;

					g2d.fillRect(startX, startY, sizeX, sizeY);

					g.setColor(Constants.drawSelectBorder);
					int[] xArray = { drawSelect.x, mousePos.x, mousePos.x,
							drawSelect.x };
					int[] yArray = { drawSelect.y, drawSelect.y, mousePos.y,
							mousePos.y };
					g2d.drawPolygon(xArray, yArray, 4);
				}
			}
		}
	};

	private boolean panning;
	private int posX, posY;
	private ICircuitGate rightClickedGate = null;
	private final ISelectionModel selectModel;

	public Canvas(final IModel canvasModel,
			final ISelectionModel selectionModel,
			final IMasterController masterController) {
		if (masterController == null) {
			throw new InvalidParameterException(
					"masterController must not be null");
		} else if (selectionModel == null) {
			throw new InvalidParameterException(
					"selectionModel must not be null");
		} else if (canvasModel == null) {
			throw new InvalidParameterException("canvasModel must not be null");
		}
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

	private void mouseClickedActions(final MouseEvent evt) {
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
					@SuppressWarnings("synthetic-access")
					@Override
					public void actionPerformed(final ActionEvent arg0) {
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
				selectModel.selectComponent(rightClickedGate, false);
			}

			jpm.show(evt.getComponent(), (int) evt.getPoint().getX(), (int) evt
					.getPoint().getY());
		}
	}

	private void panCanvas(final int dx, final int dy) {
		posX += dx;
		posY += dy;
	}

	private void resetConnecting() {
		connectBufferGate = null;
		connectingInput = false;
		connectingOutput = false;
		_masterController.connectComponent(null, -1);
	}
}