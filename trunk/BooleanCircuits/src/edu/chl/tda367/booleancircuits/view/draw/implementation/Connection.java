package edu.chl.tda367.booleancircuits.view.draw.implementation;

import java.awt.Point;

import edu.chl.tda367.booleancircuits.view.draw.IConnection;

public final class Connection implements IConnection {
	private final Point endPoint;
	private final int endPortIndex;
	private final int endPorts;
	private final Point startPoint;
	private final int startPortIndex;
	private final int startPorts;
	private final boolean value;

	public Connection(final Point start, final Point end, final boolean value,
			final int startPortIndex, final int startPorts,
			final int endPortIndex, final int endPorts) {
		startPoint = start;
		endPoint = end;
		this.value = value;
		this.endPorts = endPorts;
		this.endPortIndex = endPortIndex;
		this.startPorts = startPorts;
		this.startPortIndex = startPortIndex;
	}

	@Override
	public Point getEndPoint() {
		return new Point(endPoint);
	}

	@Override
	public int getEndPortIndex() {
		return endPortIndex;
	}

	@Override
	public int getNoOfEndPorts() {
		return endPorts;
	}

	@Override
	public int getNoOfStartPorts() {
		return startPorts;
	}

	@Override
	public Point getStartPoint() {
		return new Point(startPoint);
	}

	@Override
	public int getStartPortIndex() {
		return startPortIndex;
	}

	@Override
	public boolean getValue() {
		return value;
	}

}
