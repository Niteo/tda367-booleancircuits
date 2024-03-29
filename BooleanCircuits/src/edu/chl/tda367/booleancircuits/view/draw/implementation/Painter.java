package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.*;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.*;

public class Painter implements IPainter {
	private IBackground background;
	private Color color;
	private boolean isUsStandard;

	@Override
	public void drawBackground(final Graphics2D g, final Point offset,
			final Dimension canvasSize) {
		background.draw(g, offset, canvasSize);
	}

	@Override
	public void drawGate(final Graphics2D g, final IGateWrapper gate,
			final Point offset) {
		color = g.getColor();

		if (!gate.isFullyConnected()) {
			g.setColor(Constants.componentWarningBorder);
			int x = gate.getPosition().x - offset.x - Constants.componentSize;
			int y = gate.getPosition().y - offset.y - Constants.componentSize;
			int r = Constants.componentSize * 2;
			g.drawRect(x, y, r, r);
			g.setColor(Constants.componentWarningFill);
			g.fillRect(x, y, r, r);
		}

		drawPins(gate, g, offset);

		if (gate.getGate() instanceof AndGate) {
			drawAndGate(gate, g, offset);
		} else if (gate.getGate() instanceof NandGate) {
			drawNandGate(gate, g, offset);
		} else if (gate.getGate() instanceof OrGate) {
			drawOrGate(gate, g, offset);
		} else if (gate.getGate() instanceof NorGate) {
			drawNorGate(gate, g, offset);
		} else if (gate.getGate() instanceof XorGate) {
			drawXorGate(gate, g, offset);
		} else if (gate.getGate() instanceof XnorGate) {
			drawXnorGate(gate, g, offset);
		} else if (gate.getGate() instanceof NotGate) {
			drawNotGate(gate, g, offset);
		} else if (gate.getGate() instanceof ConstantGate) {
			drawConstantGate(gate, g, offset);
		} else if (gate.getGate() instanceof Clock) {
			drawClock(gate, g, offset);
		} else if (gate.getGate() instanceof Equal) {
			drawEqual(gate, g, offset);
		}
	}

	@Override
	public void drawGateConnections(final Graphics2D g,
			final Collection<IConnection> coll, final Point offset) {
		for (IConnection i : coll) {
			if (i.getValue()) {
				g.setColor(Constants.connectionHigh);
			} else {
				g.setColor(Constants.connectionLow);
			}

			int nInputs = i.getNoOfStartPorts();
			int inputIndex = i.getStartPortIndex();

			int y1Offset = inputIndex * Constants.componentSize / (nInputs)
					+ Constants.componentSize / (nInputs * 2);
			int xStart = (int) (i.getStartPoint().x - offset.x - Constants.componentSize * 0.5);
			int yStart = (int) (i.getStartPoint().y - offset.y + y1Offset - Constants.componentSize * 0.5);

			int y2Offset = (Constants.componentSize / (i.getNoOfEndPorts() * 2))
					* (i.getEndPortIndex() + i.getNoOfEndPorts());
			int xEnd = (int) (i.getEndPoint().x - offset.x + Constants.componentSize * 0.5);
			int yEnd = (int) (i.getEndPoint().y - offset.y
					- Constants.componentSize * 0.5 + y2Offset);

			if (xStart > xEnd + 2 * Constants.connectorLength) {
				int[] xPoints = new int[4];
				xPoints[0] = xStart;
				xPoints[1] = xPoints[0] + (xEnd - xStart) / 2;
				xPoints[2] = xPoints[1];
				xPoints[3] = xEnd;

				int[] yPoints = { yStart, yStart, yEnd, yEnd };
				g.drawPolyline(xPoints, yPoints, 4);
			} else if (Math.abs(yEnd - yStart) <= Constants.componentSize
					&& xStart > xEnd) {
				g.drawLine(xStart - Constants.connectorLength, yStart, xEnd
						+ Constants.connectorLength, yEnd);
			} else {
				int[] xPoints = new int[6];
				xPoints[0] = xStart;
				xPoints[1] = xStart - Constants.connectorLength;
				xPoints[2] = xPoints[1];
				xPoints[3] = xStart + xEnd - xStart + Constants.connectorLength;
				xPoints[4] = xPoints[3];
				xPoints[5] = xEnd;

				int[] yPoints = new int[6];
				yPoints[0] = yStart;
				yPoints[1] = yPoints[0];
				yPoints[2] = yPoints[0]
						+ (yEnd > yStart ? Constants.componentSize
								: -Constants.componentSize);
				yPoints[3] = yPoints[2];
				yPoints[4] = yEnd;
				yPoints[5] = yPoints[4];

				g.drawPolyline(xPoints, yPoints, 6);
			}
		}
		g.setColor(color);
	}

	@Override
	public void setBackground(final IBackground background) {
		this.background = background;
	}

	@Override
	public void setUsStandard(final boolean standard) {
		isUsStandard = standard;
	}

	private void drawAndGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		g.setColor(color);
		if (isUsStandard) {
			g.drawRect(gate.getPosition().x - (offset.x)
					- Constants.componentSize / 2, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2,
					Constants.componentSize / 2, Constants.componentSize);

			g.drawOval(gate.getPosition().x - Constants.componentSize / 2
					- (offset.x), gate.getPosition().y
					- Constants.componentSize / 2 - (offset.y),
					Constants.componentSize, Constants.componentSize);

			g.setColor(Color.WHITE);
			g.fillRect(gate.getPosition().x - offset.x
					- Constants.componentSize / 2 + 1, gate.getPosition().y
					- offset.y - Constants.componentSize / 2 + 1,
					Constants.componentSize / 2 - 1,
					Constants.componentSize - 1);
			g.fillOval(gate.getPosition().x - Constants.componentSize / 2
					- (offset.x) + 1, gate.getPosition().y
					- Constants.componentSize / 2 - (offset.y) + 1,
					Constants.componentSize - 1, Constants.componentSize - 1);

			g.setColor(color);

		} else {
			drawIECRectangle(gate, g, offset, "&");
		}
	}

	private void drawClock(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawIECRectangle(gate, g, offset, "C");
	}

	private void drawConstantGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawIECRectangle(gate, g, offset, gate.toString());
	}

	private void drawEqual(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawIECRectangle(gate, g, offset, "");
	}

	private void drawIECRectangle(final IGateWrapper gate, final Graphics2D g,
			final Point offset, final String symbol) {
		g.setColor(Color.white);
		g.fillRect(
				(gate.getPosition().x - offset.x - Constants.componentSize / 2),
				(gate.getPosition().y - offset.y - Constants.componentSize / 2),
				Constants.componentSize, Constants.componentSize);
		g.setColor(color);

		g.drawRect(
				(gate.getPosition().x - offset.x - Constants.componentSize / 2),
				(gate.getPosition().y - offset.y - Constants.componentSize / 2),
				Constants.componentSize, Constants.componentSize);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString(symbol, gate.getPosition().x - offset.x
				- Constants.componentSize / 4, gate.getPosition().y - offset.y
				+ Constants.componentSize / 4);
	}

	private void drawInvOutput(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y
				- Constants.componentCircleRadius / 2,
				Constants.componentCircleRadius,
				Constants.componentCircleRadius);
		g.setColor(color);
		g.drawOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y
				- Constants.componentCircleRadius / 2,
				Constants.componentCircleRadius,
				Constants.componentCircleRadius);
	}

	private void drawNandGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			drawAndGate(gate, g, offset);
		} else {
			drawIECRectangle(gate, g, offset, "&");
		}
		drawInvOutput(gate, g, offset);
	}

	private void drawNorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			drawOrGate(gate, g, offset);
		} else {
			drawIECRectangle(gate, g, offset, "\u2265" + "1");
		}
		drawInvOutput(gate, g, offset);
	}

	private void drawNotGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			g.setColor(Color.white);

			int[] x = {
					gate.getPosition().x - (offset.x) - Constants.componentSize
							/ 2,
					gate.getPosition().x - (offset.x) - Constants.componentSize
							/ 2,
					gate.getPosition().x - offset.x + Constants.componentSize
							/ 2, };
			int[] y = {
					gate.getPosition().y - (offset.y) - Constants.componentSize
							/ 2,
					gate.getPosition().y - (offset.y) + Constants.componentSize
							/ 2, gate.getPosition().y - (offset.y), };

			g.fillPolygon(x, y, 3);
			g.setColor(color);
			g.drawPolygon(x, y, 3);
		} else {
			drawIECRectangle(gate, g, offset, "1");
		}
		drawInvOutput(gate, g, offset);
	}

	private void drawOrGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			g.setColor(Color.WHITE);

			int xPoints[] = new int[5];
			xPoints[0] = gate.getPosition().x - offset.x
					- Constants.componentSize / 2;
			xPoints[1] = gate.getPosition().x - offset.x - 2
					* Constants.componentSize / 5;
			xPoints[2] = xPoints[0];
			xPoints[3] = xPoints[1];
			xPoints[4] = xPoints[1];
			int yPoints[] = new int[5];
			yPoints[0] = gate.getPosition().y - offset.y
					+ Constants.componentSize / 2;
			yPoints[1] = gate.getPosition().y - offset.y;
			yPoints[2] = gate.getPosition().y - offset.y
					- Constants.componentSize / 2;
			yPoints[3] = yPoints[2];
			yPoints[4] = yPoints[0];

			g.fillPolygon(xPoints, yPoints, 5);

			g.fillArc(gate.getPosition().x - offset.x - 10
					* Constants.componentSize / 7, gate.getPosition().y
					- offset.y - Constants.componentSize / 2,
					100 * Constants.componentSize / 51,
					Constants.componentSize, -90, 180);

			g.setColor(color);
			g.drawArc(gate.getPosition().x - offset.x - 3
					* Constants.componentSize / 2, gate.getPosition().y
					- offset.y - Constants.componentSize / 2,
					2 * Constants.componentSize, Constants.componentSize, -90,
					180);

			g.drawLine(gate.getPosition().x - (offset.x) - 3
					* Constants.componentSize / 5, gate.getPosition().y
					- offset.y - Constants.componentSize / 2,
					gate.getPosition().x - offset.x - Constants.componentSize
							/ 2, gate.getPosition().y - offset.y
							- Constants.componentSize / 2);

			g.drawLine(gate.getPosition().x - (offset.x) - 3
					* Constants.componentSize / 5, gate.getPosition().y
					- offset.y + Constants.componentSize / 2,
					gate.getPosition().x - offset.x - Constants.componentSize
							/ 2, gate.getPosition().y - offset.y
							+ Constants.componentSize / 2);

			g.drawArc(gate.getPosition().x - offset.x - 3
					* Constants.componentSize / 4, gate.getPosition().y
					- offset.y - Constants.componentSize / 2,
					Constants.componentSize / 3, Constants.componentSize, -90,
					180);
		} else {
			drawIECRectangle(gate, g, offset, "\u2265" + "1");
		}
	}

	private void drawPins(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		// Draw input pins
		for (int i = 0; i < gate.getNoOfInputs(); i++) {
			int yOffset = i * Constants.componentSize / (gate.getNoOfInputs())
					+ Constants.componentSize / (gate.getNoOfInputs() * 2);
			int xStart = (int) (gate.getPosition().x - offset.x - Constants.componentSize * 0.5);
			int yStart = (int) (gate.getPosition().y - offset.y + yOffset - Constants.componentSize * 0.5);
			if (gate.getInputs().get(i).getInputValue()) {
				g.setColor(Constants.connectionHigh);
			} else {
				g.setColor(Constants.connectionLow);
			}
			g.drawLine(xStart, yStart, xStart - Constants.connectorLength,
					yStart);
		}

		// Draw output pins
		for (int i = 0; i < gate.getNoOfOutputs(); i++) {
			int yOffset = (Constants.componentSize / (gate.getNoOfOutputs() * 2))
					* (i + gate.getNoOfOutputs());
			int xStart = (int) (gate.getPosition().x - offset.x + Constants.componentSize * 0.5);
			int yStart = (int) (gate.getPosition().y - offset.y
					- Constants.componentSize * 0.5 + yOffset);
			if (gate.getOutputValue(i)) {
				g.setColor(Constants.connectionHigh);
			} else {
				g.setColor(Constants.connectionLow);
			}
			g.drawLine(xStart, yStart, xStart + Constants.connectorLength,
					yStart);
		}
	}

	private void drawXnorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			drawXorGate(gate, g, offset);
		} else {
			drawIECRectangle(gate, g, offset, "=1");
		}
		drawInvOutput(gate, g, offset);
	}

	private void drawXorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		if (isUsStandard) {
			drawOrGate(gate, g, offset);
			g.drawArc(gate.getPosition().x - offset.x - 100
					* Constants.componentSize / 110, gate.getPosition().y
					- offset.y - Constants.componentSize / 2,
					Constants.componentSize / 3, Constants.componentSize, -90,
					180);

		} else {
			drawIECRectangle(gate, g, offset, "=1");
		}
	}

}
