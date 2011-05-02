package edu.chl.tda367.booleancircuits.view.draw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface IBackground {
/**
 * Draws the background
 * @param g Graphics
 */
	public void draw(Graphics g, Point offset, Dimension canvasSize);
}
