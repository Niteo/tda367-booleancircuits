package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

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
	public void drawBackground(Graphics g, Point offset, Dimension canvasSize) {
		background.draw(g, offset, canvasSize);
	}

	/*
	 * sets the standard to the US standard
	 */
	public void setUsStandard() {
		isUsStandard = true;
	}

	@Override
	public void drawGate(Graphics g, AbstractCircuitGate gate, Point offset) {
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
		}else if(gate instanceof ConstantGate){
			drawConstantGate(gate,g,offset);
		}

	}

	private void drawIEC(AbstractCircuitGate gate,Graphics g,Point offset){
		
		g.setColor(Color.WHITE);
		g
				.fillRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize);
		g.setColor(color);

		g
				.drawRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize);
		g.drawString(symbol, gate.getPosition().x - offset.x, gate
				.getPosition().y
				- offset.y);
	}

	
	private void drawAndGate(AbstractCircuitGate gate, Graphics g, Point offset) {

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g
					.fillRect(
							(gate.getPosition().x + offset.x - Constants.componentSize / 2),
							(gate.getPosition().y + offset.y - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize);

			g.setColor(color);
			g
					.drawRect(
							(gate.getPosition().x + (offset.x) - Constants.componentSize / 2),
							(gate.getPosition().y + (offset.y) - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize);
			g.setColor(Color.WHITE);
			g.fillOval(gate.getPosition().x - Constants.componentSize / 2, gate
					.getPosition().y
					- Constants.componentSize / 2, Constants.componentSize,
					Constants.componentSize);
		}
		symbol= "&";
		drawIEC(gate, g, offset);

	}
	private void drawNandGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		}
		symbol= "&";
		drawIEC(gate, g, offset);
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);
		g.setColor(color);
		g.drawOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);
	}
	private void drawOrGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		}
		symbol = ">1";
		drawIEC(gate, g, offset);
	}
	private void drawNorGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		}
		
		symbol = ">=1";
		drawIEC(gate, g, offset);
	}


	private void drawXorGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO Auto-generated method stub
		if (isUsStandard) {

		}
		symbol = "=1";
		drawIEC(gate, g, offset);

	}
	private void drawXnorGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		// TODO US Xnor 
		if (isUsStandard) {

		}
		symbol= "=1";
		drawIEC(gate, g, offset);
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);
		g.setColor(color);
		g.drawOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);
	}
	private void drawNotGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		
		if (isUsStandard) {
			// TODO US not-gate
		}
		symbol = "1";
		drawIEC(gate, g, offset);
		g.setColor(Color.white);
		g.fillOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);
		g.setColor(color);
		g.drawOval(gate.getPosition().x-offset.x+Constants.componentSize/2, gate.getPosition().y-offset.y-5, 16, 16);

	}
	private void drawConstantGate(AbstractCircuitGate gate, Graphics g,
			Point offset) {
		// TODO US
		if(isUsStandard){
			
		}
		symbol=gate.toString();
		drawIEC(gate, g, offset);
	
	}

	/*
	 * Font font = new Font("Verdana", Font.BOLD, 12);
	 *  
	 * g.drawString(gate.toString(), gate.getPosition().x,
	 * gate.getPosition().y); 
	 */
}
