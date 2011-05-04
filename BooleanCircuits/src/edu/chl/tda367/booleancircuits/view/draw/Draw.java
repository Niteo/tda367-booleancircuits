package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.*;
import edu.chl.tda367.booleancircuits.utilities.Constants;

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
	
	public void drawIEC(AbstractCircuitGate gate,Graphics g,Point offset){
		
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

	
	public void drawAndGate(AbstractCircuitGate gate, Graphics g, Point offset) {

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
		//TODO:circle 
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
		symbol = ">1";
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
		//TODO:circle 
		//g.drawOval(x, y, 5, 5);

	}
	private void drawNotGate(AbstractCircuitGate gate, Graphics g, Point offset) {
		
		if (isUsStandard) {
			// TODO US not-gate
		}
		symbol = "=1";
		drawIEC(gate, g, offset);
		//TODO:circle 

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
	 * Font font = new Font("Verdana", Font.BOLD, 12); if()){
	 * g.drawString(gate.toString(), gate.getPosition().x,
	 * gate.getPosition().y); }
	 */
}
