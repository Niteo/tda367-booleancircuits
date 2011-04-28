package edu.chl.tda367.booleancircuits.view;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.view.Canvas.CanvasAction;

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
