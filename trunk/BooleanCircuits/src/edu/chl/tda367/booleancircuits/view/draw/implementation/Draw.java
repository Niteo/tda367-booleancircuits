package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.IAbstractCircuitGate;
import edu.chl.tda367.booleancircuits.model.components.IGateInput;
import edu.chl.tda367.booleancircuits.model.components.implementation.*;
import edu.chl.tda367.booleancircuits.utilities.Constants;
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
	public void drawGate(Graphics2D g, IAbstractCircuitGate gate, Point offset) {
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
		}

		int nInputs = gate.getInputs().size();
		int loopCount = 0;
		for (IGateInput i : gate.getInputs()) {
			if (i.getInputComponent() != null) {
				if(i.getInputValue()){
					g.setColor(Color.green);
				} else {
					g.setColor(Color.red);
				}
				
				int y1Offset = loopCount*Constants.componentSize/(nInputs) + Constants.componentSize/(nInputs*2);
				int x1 = (int) (gate.getPosition().x - offset.x - Constants.componentSize*0.5);
				int y1 = (int) (gate.getPosition().y - offset.y + y1Offset - Constants.componentSize*0.5);
				
				int y2Offset = (Constants.componentSize/(i.getInputComponent().getNoOfOutputs()* 2)) * (i.getInputPort()+i.getInputComponent().getNoOfOutputs());
				int x2 = (int) (i.getInputComponent().getPosition().x - offset.x + Constants.componentSize*0.5);
				int y2 = (int) (i.getInputComponent().getPosition().y - offset.y - Constants.componentSize*0.5 + y2Offset);
				
				int []xPoints = {x1, x1 + (x2-x1)/2, x1 + (x2-x1)/2, x2};
				int []yPoints = {y1, y1, y2, y2};
				g.drawPolyline(xPoints, yPoints, 4);
			}
			loopCount++;
		}

	}

	private void drawIEC(IAbstractCircuitGate gate, Graphics g, Point offset) {

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
		g.setFont(new Font("Sans serif", Font.BOLD, 16));
		g.drawString(symbol, gate.getPosition().x - offset.x
				- Constants.componentSize / 4, gate.getPosition().y - offset.y
				+ Constants.componentSize / 4);
	}

	private void drawAndGate(IAbstractCircuitGate gate, Graphics g, Point offset) {

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
			drawIEC(gate, g, offset);
		}
	}

	private void drawNandGate(IAbstractCircuitGate gate, Graphics g,
			Point offset) {

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
			g.setColor(Color.white);
			g.fillOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, Constants.componentCircleRadius,
					Constants.componentCircleRadius);
			g.setColor(color);
			g.drawOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 10, 10);

		} else {

			symbol = "&";
			drawIEC(gate, g, offset);
			g.setColor(Color.white);
			g.fillOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);
			g.setColor(color);
			g.drawOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);
		}
	}

	private void drawOrGate(IAbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		} else {
			symbol = ">1";
			drawIEC(gate, g, offset);
		}
	}

	private void drawNorGate(IAbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		} else {

			symbol = "\u2265" + "1";
			drawIEC(gate, g, offset);
		}
	}

	private void drawXorGate(IAbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		} else {
			symbol = "=1";
			drawIEC(gate, g, offset);

		}
	}

	private void drawXnorGate(IAbstractCircuitGate gate, Graphics g,
			Point offset) {
		// TODO US Xnor
		if (isUsStandard) {

		} else {
			symbol = "=1";
			drawIEC(gate, g, offset);
			g.setColor(Color.white);
			g.fillOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);
			g.setColor(color);
			g.drawOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);
		}
	}

	private void drawNotGate(IAbstractCircuitGate gate, Graphics g, Point offset) {

		if (isUsStandard) {
			// TODO US not-gate
		} else {
			symbol = "1";
			drawIEC(gate, g, offset);
			g.setColor(Color.white);
			g.fillOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);
			g.setColor(color);
			g.drawOval(gate.getPosition().x - offset.x
					+ Constants.componentSize / 2, gate.getPosition().y
					- offset.y - 5, 16, 16);

		}
	}

	private void drawConstantGate(IAbstractCircuitGate gate, Graphics g,
			Point offset) {
		// TODO US
		if (isUsStandard) {

		} else {
			symbol = gate.toString();
			drawIEC(gate, g, offset);

		}
	}

}
