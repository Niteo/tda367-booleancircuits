package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.chl.tda367.booleancircuits.utilities.Constants;

/**
 * A class to draw a squared background.
 * 
 * @author Boel
 * 
 */
public class GridBackground implements IBackground {

	@Override
	public void draw(Graphics g, Point offset, Dimension canvasSize) {
		g.setColor(Color.LIGHT_GRAY);
		//TODO: fix offset!
		for (int x = 0; x < canvasSize.width
				* Constants.canvasBackgroundLineDistance; x += Constants.canvasBackgroundLineDistance) {
			g.drawLine(x, 0, x, canvasSize.height);
		}
		for(int y=0;y<canvasSize.height* Constants.canvasBackgroundLineDistance; y+=Constants.canvasBackgroundLineDistance){
			g.drawLine(0, y, canvasSize.width, y);
		}
	}

}
