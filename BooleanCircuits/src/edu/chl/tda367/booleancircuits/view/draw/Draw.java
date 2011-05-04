package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;
import edu.chl.tda367.booleancircuits.utilities.Constants;

public class Draw implements IDraw {
	private IBackground background;
	private boolean isUsStandard;

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

		if (isUsStandard) {
			g.setColor(Color.WHITE);
			g
					.fillRect(
							(gate.getPosition().x + offset.x - Constants.componentSize / 2),
							(gate.getPosition().y + offset.y - Constants.componentSize / 2),
							Constants.componentSize / 2,
							Constants.componentSize);

			g.setColor(Color.black);
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
		g.setColor(Color.WHITE);
		g
				.fillRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize);
		g.setColor(Color.black);
		g
				.drawRect(
						(gate.getPosition().x - offset.x - Constants.componentSize / 2),
						(gate.getPosition().y - offset.y - Constants.componentSize / 2),
						Constants.componentSize, Constants.componentSize);
		g.drawString("&", gate.getPosition().x-offset.x, gate.getPosition().y-offset.y);
	}

	/*public void drawAndGate(Graphics g, Compo gate, Point offset) {

		//Font font = new Font("Verdana", Font.BOLD, 12);
		if()){
		g.drawString(gate.toString(), gate.getPosition().x, gate.getPosition().y);
		}
	}*/
	
	
}
