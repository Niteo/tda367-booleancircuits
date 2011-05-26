package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

import edu.chl.tda367.booleancircuits.controller.IMasterController;
import edu.chl.tda367.booleancircuits.model.ICircuit;
import edu.chl.tda367.booleancircuits.model.ISelectionModel;
import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.ICanvas;
import edu.chl.tda367.booleancircuits.view.draw.*;
import edu.chl.tda367.booleancircuits.view.draw.implementation.*;

/**
 * A class where the components are drawn.
 * 
 * @author Boel, Anton
 * 
 */
public class Canvas implements ICanvas {

	private static IPainter drawer = new Painter();

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

	private final IMasterController mc;
	private IGateWrapper connectBufferGate = null;
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
				mc.removeComponent(rightClickedGate);
			} else if (menu.isInputItem(menuItem)) {
				mc.connectComponent(rightClickedGate,
						menu.getInputIndex(menuItem));
				if (connectingOutput) {
					mc.connectComponent(connectBufferGate, connectBufferPort);
					resetConnecting();
				} else {
					connectingInput = true;
				}
			} else if (menu.isOutputItem(menuItem)) {
				if (connectingInput) {
					mc.connectComponent(rightClickedGate,
							menu.getInputIndex(menuItem));
					resetConnecting();
				} else {
					connectingOutput = true;
					connectBufferGate = rightClickedGate;
				}
			} else if (menu.isCopyButton(menuItem)) {
				selectModel.selectComponent(rightClickedGate, false);
				mc.copySelectedComponents();
			} else if (menu.isCutButton(menuItem)) {
				selectModel.selectComponent(rightClickedGate, false);
				mc.cutSelectedComponents();
			}
		}
	};
	private final CanvasPopup menu;
	private final ICircuit model;
	private final MouseAdapter mouseAdapter = new MouseInputAdapter() {
		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseClicked(final MouseEvent evt) {
			mouseClickedActions(evt);
		}

		@SuppressWarnings("synthetic-access")
		@Override
		public void mouseDragged(final MouseEvent evt) {
			Point dragPosition = new Point(evt.getX() + position.x, evt.getY() + position.y);
			if (oldDragPosition != null) {
				int dx = (int) (evt.getPoint().getX() - oldDragPosition.getX());
				int dy = (int) (evt.getPoint().getY() - oldDragPosition.getY());

				if (evt.isShiftDown()) {
					if (drawSelect == null) {
						drawSelect = evt.getPoint();
					}
				} else if (draggingMode) {
					// Move all selected components.
					for (IGateWrapper selected : selectModel
							.getSelectedComponents()) {
						selected.move(dx, dy);
					}
				} else {
					IGateWrapper gate = model.getComponent(dragPosition);
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
				mc.selectComponents(new Point(drawSelect.x + position.x, drawSelect.y
						+ position.y), new Point(releasePoint.x + position.x,
						releasePoint.y + position.y));
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
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

			drawer.drawBackground(g2d, position, panel.getSize());
			
			Collection<IConnection> connections = new LinkedList<IConnection>();
			LinkedList<IGateWrapper> components = new LinkedList<IGateWrapper>();
			int nonSelectedComponents = 0;
			
			// Fill list of connections, gates and selected gates
			for (IGateWrapper gate : model.getComponents()) {
					// Add connections
					for (IGateInput gi : gate.getInputs()) {
						IGateWrapper gw = model.getGateWrapper(gi
								.getInputComponent());
						if (gw != null) {
							connections.add(new Connection(gate.getPosition(), gw
									.getPosition(), gi.getInputValue(), gate
									.getInputs().indexOf(gi), gate
									.getNoOfInputs(), gi.getInputPort(), gw
									.getNoOfOutputs()));
						}
					}
					// Only include gates in view!
					if (gate.getPosition().x >= position.x - Constants.componentSize
							&& gate.getPosition().x < position.x + panel.getSize().width + Constants.componentSize
							&& gate.getPosition().y >= position.y - Constants.componentSize
							&& gate.getPosition().y < position.y + panel.getSize().height + Constants.componentSize) {
						// Add to list
						if (selectModel.isSelectedComponent(gate)) {
							components.addLast(gate);
						} else {
							components.addFirst(gate);
							nonSelectedComponents++;
						}
					}
			}
			
			drawer.drawGateConnections(g2d, connections, position);
			g2d.setColor(Color.BLACK);
			int loops = 0;
			for (IGateWrapper gate : components){
				if(loops++ == nonSelectedComponents){
					g2d.setColor(Color.BLUE);
				}
				drawer.drawGate(g2d, gate, position);
			}
			
			// Draw Position
			g2d.setColor(Color.BLACK);
			g2d.setFont(UIManager.getFont("TabbedPane.font"));
			g2d.drawString("[" + position.x + ", " + position.y + "]", 5, 15);
			
			// Draw infinite recursion
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
	private Point position = new Point(0,0);
	private IGateWrapper rightClickedGate = null;
	private final ISelectionModel selectModel;

	public Canvas(final ICircuit canvasModel,
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
		mc = masterController;
		model = canvasModel;
		selectModel = selectionModel;
		menu = new CanvasPopup(listener);

		panel.setBackground(Color.WHITE);
		panel.addMouseListener(mouseAdapter);
		panel.addMouseMotionListener(mouseAdapter);
	}

	@Override
	public JPanel getCanvas() {
		return panel;
	}

	private void mouseClickedActions(final MouseEvent evt) {
		final Point pointClicked = new Point(evt.getX() + position.x, evt.getY()
				+ position.y);
		if (evt.getButton() == MouseEvent.BUTTON1) { // LeftMouseButton
			resetConnecting();

			if (evt.getClickCount() == 1) {
				mc.selectComponent(pointClicked, evt.isShiftDown());
			} else if (evt.getClickCount() == 2) {
				if (model.getComponent(pointClicked) == null) {
					mc.addComponent(pointClicked);
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
							mc.addComponent(pointClicked);
						} else if (arg0.getSource() == cutItem) {
							mc.cutSelectedComponents();
						} else if (arg0.getSource() == copyItem) {
							mc.copySelectedComponents();
						} else if (arg0.getSource() == pasteItem) {
							mc.pasteSelectedComponents(pointClicked);
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
		position.x += dx;
		position.y += dy;
	}

	private void resetConnecting() {
		connectBufferGate = null;
		connectingInput = false;
		connectingOutput = false;
		mc.connectComponent(null, -1);
	}
}