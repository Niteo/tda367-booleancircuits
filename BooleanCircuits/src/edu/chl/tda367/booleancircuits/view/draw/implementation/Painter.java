package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collection;

import edu.chl.tda367.booleancircuits.model.components.IGateWrapper;
import edu.chl.tda367.booleancircuits.model.components.implementation.AndGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Clock;
import edu.chl.tda367.booleancircuits.model.components.implementation.ConstantGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.Equal;
import edu.chl.tda367.booleancircuits.model.components.implementation.NandGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.NotGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.OrGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XnorGate;
import edu.chl.tda367.booleancircuits.model.components.implementation.XorGate;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.*;

public class Painter implements IPainter {
	private IBackground background;
	private boolean isUsStandard;
	private Color color;

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
			g.setColor(Color.RED);
			int x = gate.getPosition().x - offset.x - Constants.componentSize;
			int y = gate.getPosition().y - offset.y - Constants.componentSize;
			int r = Constants.componentSize * 2;
			g.drawRect(x, y, r, r);
			g.setColor(Constants.componentWarningFill);
			g.fillRect(x, y, r, r);
		}

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

		for (int i = 0; i < gate.getNoOfOutputs(); i++) {
			if (gate.getOutputValue(i)) {
				g.setColor(Constants.connectionHigh);
			} else {
				g.setColor(Constants.connectionLow);
			}

			int x = gate.getPosition().x - offset.x + Constants.componentSize
					/ 2;
			int y = gate.getPosition().y - offset.y - Constants.componentSize
					/ 2
					+ (Constants.componentSize / (gate.getNoOfOutputs() * 2))
					* (i + gate.getNoOfOutputs());
			g.fillRect(x - 3, y - 3, 3, 6);
		}

		g.setColor(color);
		drawPins(gate, g, offset);
	}

	@Override
	public void drawGateConnections(final Graphics2D g,
			Collection<IConnection> coll, final Point offset) {
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

			if (xStart > xEnd + 20) {
				int[] xPoints = new int[4];
				xPoints[0] = xStart;
				xPoints[1] = xPoints[0] + (xEnd - xStart) / 2;
				xPoints[2] = xPoints[1];
				xPoints[3] = xEnd;

				int[] yPoints = { yStart, yStart, yEnd, yEnd };
				g.drawPolyline(xPoints, yPoints, 4);
			} else if (Math.abs(yEnd - yStart) <= Constants.componentSize
					&& xStart > xEnd) {
				g.drawLine(xStart, yStart, xEnd, yEnd);
			} else {
				int[] xPoints = new int[6];
				xPoints[0] = xStart;
				xPoints[1] = xStart - 10;
				xPoints[2] = xPoints[1];
				xPoints[3] = xStart + xEnd - xStart + 10;
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

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g.fillRect(
					(gate.getPosition().x - offset.x - Constants.componentSize / 2),
					(gate.getPosition().y - offset.y - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize);

			g.setColor(color);
			g.drawRect(
					(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
					(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize);
			g.setColor(Color.WHITE);
			g.fillOval(gate.getPosition().x - Constants.componentSize / 2
					- (offset.x), gate.getPosition().y
					- Constants.componentSize / 2 - (offset.y),
					Constants.componentSize, Constants.componentSize);
			g.setColor(color);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize / 2, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2,
					Constants.componentSize, Constants.componentSize, -90, 180);

		} else {
			drawRectangle(gate, g, offset, "&");
		}
	}

	private void drawCircle(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y - 5,
				Constants.componentCircleRadius,
				Constants.componentCircleRadius);
		g.setColor(color);
		g.drawOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y - 5, 10, 10);
	}

	private void drawClock(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawRectangle(gate, g, offset, "C");
	}

	private void drawEqual(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawRectangle(gate, g, offset, "");
	}

	private void drawConstantGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {
		drawRectangle(gate, g, offset, gate.toString());

	}

	private void drawNandGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g.fillRect(
					(gate.getPosition().x - offset.x - Constants.componentSize / 2),
					(gate.getPosition().y - offset.y - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize);

			g.setColor(color);
			g.drawRect(
					(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
					(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize);
			g.setColor(Color.WHITE);
			g.fillOval(gate.getPosition().x - (offset.x)
					- Constants.componentSize / 2, gate.getPosition().y
					- Constants.componentSize / 2 - (offset.y),
					Constants.componentSize, Constants.componentSize);
			g.setColor(color);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize / 2, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2,
					Constants.componentSize, Constants.componentSize, -90, 180);
			drawCircle(gate, g, offset);
		} else {
			drawRectangle(gate, g, offset, "&");
			drawCircle(gate, g, offset);
		}
	}

	private void drawNorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);
			drawCircle(gate, g, offset);
		} else {
			drawRectangle(gate, g, offset, "\u2265" + "1");
			drawCircle(gate, g, offset);
		}
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
							/ 2,

					gate.getPosition().y - (offset.y), };

			g.fillPolygon(x, y, 3);
			g.setColor(color);
			g.drawPolygon(x, y, 3);

			drawCircle(gate, g, offset);

		} else {
			drawRectangle(gate, g, offset, "1");
			drawCircle(gate, g, offset);

		}
	}

	private void drawOrGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);

		} else {
			drawRectangle(gate, g, offset, "\u2265" + "1");
		}
	}

	private void drawRectangle(final IGateWrapper gate, final Graphics2D g,
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

	private void drawUSGateBody(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		g.setColor(Color.WHITE);
		g.fillRect(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - Constants.componentSize / 3
						- 3, Constants.componentSize,
				5 * Constants.componentSize / 6);
		g.fillArc(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - 5 * Constants.componentSize
						/ 8, (int) (Constants.componentSize * 1.5),
				Constants.componentSize, -90, 73);
		g.fillArc(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - 3 * Constants.componentSize
						/ 8, (int) (Constants.componentSize * 1.5),
				Constants.componentSize, 90, -73);
		g.setColor(color);
		g.drawArc(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - 5 * Constants.componentSize
						/ 8, (int) (Constants.componentSize * 1.5),
				Constants.componentSize, -90, 73);
		g.drawArc(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - 3 * Constants.componentSize
						/ 8, (int) (Constants.componentSize * 1.5),
				Constants.componentSize, 90, -73);
		g.drawLine(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) - Constants.componentSize / 3
						- 3, gate.getPosition().x - (offset.x) - 5,
				gate.getPosition().y - (offset.y) - Constants.componentSize / 3
						- 3);
		g.drawLine(gate.getPosition().x - (offset.x) - Constants.componentSize,
				gate.getPosition().y - (offset.y) + Constants.componentSize / 3
						+ 3, gate.getPosition().x - (offset.x) - 5,
				gate.getPosition().y - (offset.y) + Constants.componentSize / 3
						+ 3);
		g.drawArc(gate.getPosition().x - (offset.x) - 4
				* Constants.componentSize / 3, gate.getPosition().y
				- (offset.y) - Constants.componentSize / 2 + 3,
				Constants.componentSize / 2, Constants.componentSize - 6, -90,
				180);

	}

	private void drawXnorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);
			drawCircle(gate, g, offset);
			g.drawArc(gate.getPosition().x - (offset.x) - 5
					* Constants.componentSize / 3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2 + 3,
					Constants.componentSize / 2, Constants.componentSize - 6,
					-90, 180);
		} else {
			drawRectangle(gate, g, offset, "=1");
			drawCircle(gate, g, offset);
		}
	}

	private void drawXorGate(final IGateWrapper gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);
			g.drawArc(gate.getPosition().x - (offset.x) - 5
					* Constants.componentSize / 3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2 + 3,
					Constants.componentSize / 2, Constants.componentSize - 6,
					-90, 180);

		} else {
			drawRectangle(gate, g, offset, "=1");
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
			g.drawLine(xStart, yStart, xStart - 10, yStart);
		}

		// Draw output pins
		for (int i = 0; i < gate.getNoOfOutputs(); i++) {
			int yOffset = (Constants.componentSize / (gate.getNoOfOutputs() * 2))
					* (i + gate.getNoOfOutputs());
			int xStart = (int) (gate.getPosition().x - offset.x + Constants.componentSize * 0.5);
			int yStart = (int) (gate.getPosition().y - offset.y
					- Constants.componentSize * 0.5 + yOffset);
			g.drawLine(xStart, yStart, xStart + 10, yStart);
		}
	}

}
