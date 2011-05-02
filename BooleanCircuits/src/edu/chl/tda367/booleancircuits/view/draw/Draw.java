package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.model.components.AbstractCircuitGate;

public class Draw implements IDraw {
	private IBackground background;

	@Override
	public void setBackground(IBackground background) {
		this.background = background;
	}

	@Override
	public void drawBackground(Graphics g, Point offset, Dimension canvasSize) {
		background.draw(g, offset, canvasSize);
	}

	@Override
	public void drawGate(Graphics g, AbstractCircuitGate gate, Point offset) {
		// TODO Auto-generated method stub

	}

}
