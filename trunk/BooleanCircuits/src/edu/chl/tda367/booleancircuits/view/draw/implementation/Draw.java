package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.ICircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.*;

public class Draw implements IDraw {
	private IBackground background;
	private boolean isUsStandard;
	private String symbol;
	private Color color;

	@Override
	public void setBackground(IBackground background) {
		this.background = background;
	}

	@Override
	public void drawBackground(Graphics2D g, Point offset, Dimension canvasSize) {
		background.draw(g, offset, canvasSize);
	}

	public void setUsStandard(boolean standard) {
		isUsStandard = standard;
	}

	@Override
	public void drawGate(Graphics2D g, ICircuitGate gate, Point offset) {
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
		} else if (gate instanceof Clock){
			drawClock(gate, g, offset);
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
			g.drawLine(x, y, x + 10, y);
		}

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
				int x1 = (int) (gate.getPosition().x - offset.x - Constants.componentSize * 0.5);
				int y1 = (int) (gate.getPosition().y - offset.y + y1Offset - Constants.componentSize * 0.5);

				int y2Offset = (Constants.componentSize / (i
						.getInputComponent().getNoOfOutputs() * 2))
						* (i.getInputPort() + i.getInputComponent()
								.getNoOfOutputs());
				int x2 = (int) (i.getInputComponent().getPosition().x
						- offset.x + Constants.componentSize * 0.5);
				int y2 = (int) (i.getInputComponent().getPosition().y
						- offset.y - Constants.componentSize * 0.5 + y2Offset);

				int[] xPoints = { x1, x1 + (x2 - x1) / 2, x1 + (x2 - x1) / 2,
						x2 };
				int[] yPoints = { y1, y1, y2, y2 };
				g.drawPolyline(xPoints, yPoints, 4);
			}
			loopCount++;
		}

		g.setColor(color);
	}
	
	private void drawClock(ICircuitGate gate, Graphics2D g, Point offset){
		symbol = "C";
		drawRectangle(gate, g, offset);
	}

	private void drawAndGate(ICircuitGate gate, Graphics2D g,
			Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g
					.fill3DRect(
							(gate.getPosition().x - offset.x - Constants.componentSize / 2),
							(gate.getPosition().y - offset.y - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize, true);

			g.setColor(color);
			g
					.draw3DRect(
							(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
							(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize, true);
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

	private void drawNandGate(ICircuitGate gate, Graphics2D g,
			Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g
					.fill3DRect(
							(gate.getPosition().x - offset.x - Constants.componentSize / 2),
							(gate.getPosition().y - offset.y - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize, true);

			g.setColor(color);
			g
					.draw3DRect(
							(gate.getPosition().x - (offset.x) - Constants.componentSize / 2),
							(gate.getPosition().y - (offset.y) - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize, true);
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

	private void drawOrGate(ICircuitGate gate, Graphics2D g,
			Point offset) {

		if (isUsStandard) {
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 5 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, -90, 73);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 3 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, 90, -73);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - Constants.componentSize /3 -3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					- Constants.componentSize /3-3);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) + Constants.componentSize /3 +3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					+ Constants.componentSize /3+3);
			g.drawArc(gate.getPosition().x - (offset.x)
					-4*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
		} else {
			symbol = "\u2265" + "1";
			drawRectangle(gate, g, offset);
		}
	}

	private void drawNorGate(ICircuitGate gate, Graphics2D g,
			Point offset) {
		
		if (isUsStandard) {
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 5 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, -90, 73);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 3 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, 90, -73);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - Constants.componentSize /3 -3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					- Constants.componentSize /3-3);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) + Constants.componentSize /3 +3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					+ Constants.componentSize /3+3);
			g.drawArc(gate.getPosition().x - (offset.x)
					-4*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
			drawCircle(gate,g,offset);
		} else {

			symbol = "\u2265" + "1";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);
		}
	}

	private void drawXorGate(ICircuitGate gate, Graphics2D g,
			Point offset) {
		
		if (isUsStandard) {
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 5 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, -90, 73);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 3 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, 90, -73);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - Constants.componentSize /3 -3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					- Constants.componentSize /3-3);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) + Constants.componentSize /3 +3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					+ Constants.componentSize /3+3);
			g.drawArc(gate.getPosition().x - (offset.x)
					-4*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
			g.drawArc(gate.getPosition().x - (offset.x)
					-5*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
			
		} else {
			symbol = "=1";
			drawRectangle(gate, g, offset);

		}
	}

	private void drawXnorGate(ICircuitGate gate, Graphics2D g,
			Point offset) {
		
		if (isUsStandard) {
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 5 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, -90, 73);
			g.drawArc(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - 3 * Constants.componentSize / 8,
					(int) (Constants.componentSize * 1.5),
					Constants.componentSize, 90, -73);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) - Constants.componentSize /3 -3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					- Constants.componentSize /3-3);
			g.drawLine(gate.getPosition().x - (offset.x)
					- Constants.componentSize, gate.getPosition().y
					- (offset.y) + Constants.componentSize /3 +3, gate
					.getPosition().x
					- (offset.x)-5, gate.getPosition().y - (offset.y)
					+ Constants.componentSize /3+3);
			g.drawArc(gate.getPosition().x - (offset.x)
					-4*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
			drawCircle(gate,g,offset);
			g.drawArc(gate.getPosition().x - (offset.x)
					-5*Constants.componentSize/3, gate.getPosition().y
					- (offset.y) - Constants.componentSize / 2+3,
					Constants.componentSize/2, Constants.componentSize-6, -90, 180);
		} else {
			symbol = "=1";
			drawRectangle(gate, g, offset);
			drawCircle(gate, g, offset);
		}
	}

	private void drawNotGate(ICircuitGate gate, Graphics2D g,
			Point offset) {

		if (isUsStandard) {
			g.setColor(color.white);

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

	private void drawConstantGate(ICircuitGate gate, Graphics2D g,
			Point offset) {

		symbol = gate.toString();
		drawRectangle(gate, g, offset);

	}

	private void drawRectangle(ICircuitGate gate, Graphics2D g,
			Point offset) {

		g.setColor(Color.WHITE);
		g
				.fill3DRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize, true);
		g.setColor(color);

		g
				.draw3DRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize, true);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString(symbol, gate.getPosition().x - offset.x
				- Constants.componentSize / 4, gate.getPosition().y - offset.y
				+ Constants.componentSize / 4);
	}

	private void drawCircle(ICircuitGate gate, Graphics g, Point offset) {
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y - 5,
				Constants.componentCircleRadius,
				Constants.componentCircleRadius);
		g.setColor(color);
		g.drawOval(gate.getPosition().x - offset.x + Constants.componentSize
				/ 2, gate.getPosition().y - offset.y - 5, 10, 10);
	}

}