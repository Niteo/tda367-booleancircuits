package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.view.draw.IConnection;

public class Connection implements IConnection {
	private final Point startPoint;
	private final Point endPoint;
	private final boolean value;
	private final int endPorts;
	private final int endPortIndex;

	public Connection(final Point start, final Point end, final boolean value,
			final int endPortIndex, final int endPorts) {
		startPoint = start;
		endPoint = end;
		this.value = value;
		this.endPorts = endPorts;
		this.endPortIndex = endPortIndex;
	}

	@Override
	public Point getStartPoint() {
		return new Point(startPoint);
	}

	@Override
	public Point getEndPoint() {
		return new Point(endPoint);
	}

	@Override
	public boolean getValue() {
		return value;
	}

	@Override
	public int getNoOfEndPorts() {
		return endPorts;
	}

	@Override
	public int getEndPortIndex() {
		return endPortIndex;
	}

}
