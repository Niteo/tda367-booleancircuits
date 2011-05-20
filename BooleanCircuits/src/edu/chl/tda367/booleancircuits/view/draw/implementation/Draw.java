package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.*;

import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.*;

public class Draw implements IDraw {
	private IBackground background;
	private Color color;
	private boolean isUsStandard;
	private String symbol;

	@Override
	public void drawBackground(final Graphics2D g, final Point offset,
			final Dimension canvasSize) {
		background.draw(g, offset, canvasSize);
	}

	@Override
	public void drawGate(final Graphics2D g, final ICircuitGate gate,
			final Point offset) {
		color = g.getColor();

		if (gate instanceof AndGate) {
			drawAndGate(gate, g, offset);
		} else if (gate instanceof NandGate) {
			drawNandGate(gate, g, offset);
		} else if (gate instanceof OrGate) {
			drawOrGate(gate, g, offset);
		} else if (gate instanceof NorGate) {
			drawNorGate(gate, g, offset);
		} else if (gate instanceof XorGate) {
			drawXorGate(gate, g, offset);
		} else if (gate instanceof XnorGate) {
			drawXnorGate(gate, g, offset);
		} else if (gate instanceof NotGate) {
			drawNotGate(gate, g, offset);
		} else if (gate instanceof ConstantGate) {
			drawConstantGate(gate, g, offset);
		} else if (gate instanceof Clock) {
			drawClock(gate, g, offset);
		} else if (gate instanceof EqualGate){
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
	}

	@Override
	public void drawGateConnections(final Graphics2D g,
			final ICircuitGate gate, final Point offset) {
		g.setColor(color);
		int nInputs = gate.getInputs().size();
		int loopCount = 0;
		for (IGateInput i : gate.getInputs()) {
			if (i.getInputComponent() != null) {
				if (i.getInputValue()) {
					g.setColor(Constants.connectionHigh);
				} else {
					g.setColor(Constants.connectionLow);
				}

				int y1Offset = loopCount * Constants.componentSize / (nInputs)
						+ Constants.componentSize / (nInputs * 2);
				int xStart = (int) (gate.getPosition().x - offset.x - Constants.componentSize * 0.5);
				int yStart = (int) (gate.getPosition().y - offset.y + y1Offset - Constants.componentSize * 0.5);

				int y2Offset = (Constants.componentSize / (i
						.getInputComponent().getNoOfOutputs() * 2))
						* (i.getInputPort() + i.getInputComponent()
								.getNoOfOutputs());
				int xEnd = (int) (i.getInputComponent().getPosition().x
						- offset.x + Constants.componentSize * 0.5);
				int yEnd = (int) (i.getInputComponent().getPosition().y
						- offset.y - Constants.componentSize * 0.5 + y2Offset);

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
			loopCount++;
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

	private void drawAndGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g.fill3DRect(
					(gate.getPosition().x - offset.x - Constants.componentSize / 2),
					(gate.getPosition().y - offset.y - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize, true);

			g.setColor(color);
			g.draw3DRect(
					(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
					(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize, true);
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
			symbol = "&";
			drawRectangle(gate, g, offset);
		}
	}

	private void drawCircle(final ICircuitGate gate, final Graphics g,
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

	private void drawClock(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {
		symbol = "C";
		drawRectangle(gate, g, offset);
	}
	
	private void drawEqual(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {
		symbol = "";
		drawRectangle(gate, g, offset);
	}

	private void drawConstantGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		symbol = gate.toString();
		drawRectangle(gate, g, offset);

	}

	private void drawNandGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g.fill3DRect(
					(gate.getPosition().x - offset.x - Constants.componentSize / 2),
					(gate.getPosition().y - offset.y - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize, true);

			g.setColor(color);
			g.draw3DRect(
					(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
					(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
					Constants.componentSize / 2, Constants.componentSize, true);
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

			symbol = "&";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);
		}
	}

	private void drawNorGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);
			drawCircle(gate, g, offset);
		} else {

			symbol = "\u2265" + "1";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);
		}
	}

	private void drawNotGate(final ICircuitGate gate, final Graphics2D g,
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
			symbol = "1";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);

		}
	}

	private void drawOrGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);

		} else {
			symbol = "\u2265" + "1";
			drawRectangle(gate, g, offset);
		}
	}

	private void drawRectangle(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		g.setColor(Color.WHITE);
		g.fill3DRect(
				(gate.getPosition().x - offset.x - Constants.componentSize / 2),
				(gate.getPosition().y - offset.y - Constants.componentSize / 2),
				Constants.componentSize, Constants.componentSize, true);
		g.setColor(color);

		g.draw3DRect(
				(gate.getPosition().x - offset.x - Constants.componentSize / 2),
				(gate.getPosition().y - offset.y - Constants.componentSize / 2),
				Constants.componentSize, Constants.componentSize, true);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString(symbol, gate.getPosition().x - offset.x
				- Constants.componentSize / 4, gate.getPosition().y - offset.y
				+ Constants.componentSize / 4);
	}

	private void drawUSGateBody(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {
		g.setColor(Color.white);
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

	private void drawXnorGate(final ICircuitGate gate, final Graphics2D g,
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
			symbol = "=1";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);
		}
	}

	private void drawXorGate(final ICircuitGate gate, final Graphics2D g,
			final Point offset) {

		if (isUsStandard) {
			drawUSGateBody(gate, g, offset);
			g.drawArc(gate.getPosition().x - (offset.x) - 5
					* Constants.componentSize / 3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2 + 3,
					Constants.componentSize / 2, Constants.componentSize - 6,
					-90, 180);

		} else {
			symbol = "=1";
			drawRectangle(gate, g, offset);

		}
	}

}
