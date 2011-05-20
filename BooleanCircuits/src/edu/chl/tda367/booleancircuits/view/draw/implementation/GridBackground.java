package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.*;

import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;

/**
 * A class to draw a squared background.
 * 
 * @author Boel
 * 
 */
public final class GridBackground implements IBackground {

	@Override
	public void draw(final Graphics g, final Point offset,
			final Dimension canvasSize) {
		g.setColor(Color.LIGHT_GRAY);
		for (int x = 0; x < canvasSize.width
				+ Constants.canvasBackgroundLineDistance; x += Constants.canvasBackgroundLineDistance) {
			int xp = x
					+ ((int) offset.getX() % Constants.canvasBackgroundLineDistance)
					* -1;
			g.drawLine(xp, 0, xp, canvasSize.height);
		}
		for (int y = 0; y < canvasSize.height
				+ Constants.canvasBackgroundLineDistance; y += Constants.canvasBackgroundLineDistance) {
			int yp = y
					+ ((int) offset.getY() % Constants.canvasBackgroundLineDistance)
					* -1;
			g.drawLine(0, yp, canvasSize.width, yp);
		}
	}

}
