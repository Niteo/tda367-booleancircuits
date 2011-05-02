package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.utilities.Constants;

/**
 * A class to draw a dotted background.
 * 
 * @author Boel
 * 
 */
public class DottedBackground implements IBackground {

	@Override
	public void draw(Graphics g, Point offset, Dimension canvasSize) {
		// TODO fix offset!

		int radius= (int)(Constants.canvasDotDiameter*0.5);
		for (int x = 0; x < canvasSize.width
				* Constants.canvasBackgroundLineDistance; x += Constants.canvasBackgroundLineDistance) {
			for (int y = 0; y < canvasSize.height
					* Constants.canvasBackgroundLineDistance; y += Constants.canvasBackgroundLineDistance) {
				g.drawRect(x-radius, y-radius, Constants.canvasDotDiameter,
						Constants.canvasDotDiameter);
			}
		}

	}

}
