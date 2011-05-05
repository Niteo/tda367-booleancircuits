package edu.chl.tda367.booleancircuits.view.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.view.implementation.Canvas.CanvasAction;

public class CanvasEvent {

	private Point point;
	private CanvasAction action;

	public CanvasEvent(Point point, CanvasAction action) {
		this.point = point;
		this.action = action;
	}

	public Point getPoint() {
		return point;
	}

	public CanvasAction getAction() {
		return action;
	}

}
