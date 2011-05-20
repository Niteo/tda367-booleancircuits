package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.*;

import edu.chl.tda367.booleancircuits.utilities.implementation.Constants;
import edu.chl.tda367.booleancircuits.view.draw.IBackground;

/**
 * A class to draw a dotted background.
 *
 * @author Boel
 *
 */
public final class DottedBackground implements IBackground {

	@Override
	public void draw(final Graphics g, final Point offset,
			final Dimension canvasSize) {
		int radius = (int) (Constants.canvasDotDiameter * 0.5);
		for (int x = 0; x < canvasSize.width
				+ Constants.canvasBackgroundLineDistance; x += Constants.canvasBackgroundLineDistance) {
			for (int y = 0; y < canvasSize.height
					+ Constants.canvasBackgroundLineDistance; y += Constants.canvasBackgroundLineDistance) {
				g.drawRect(
						(x - radius)
								+ ((int) offset.getX() % Constants.canvasBackgroundLineDistance)
								* -1,
						y
								- radius
								+ ((int) offset.getY() % Constants.canvasBackgroundLineDistance)
								* -1, Constants.canvasDotDiameter,
						Constants.canvasDotDiameter);
			}
		}

	}

}
