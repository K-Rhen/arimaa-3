package de.htwg.se.arimaa.aview.gui;

import java.awt.Point;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public class MouseFigure {

	private Point mousePoint;
	private FIGURE_NAME figureName;
	private PLAYER_NAME playerName;
	private Position fromPosition;

	public MouseFigure(Point mousePoint, FIGURE_NAME figureName, PLAYER_NAME playerName, Position fromPosition) {
		this.mousePoint = mousePoint;
		this.figureName = figureName;
		this.playerName = playerName;
		this.fromPosition = fromPosition;
	}

	public Point getPosition() {
		return mousePoint;
	}

	public void setPoint(Point point) {
		this.mousePoint = point;
	}

	public FIGURE_NAME getFigureName() {
		return figureName;
	}

	public PLAYER_NAME getPlayer() {
		return playerName;
	}

	public Position getFromPosition() {
		return fromPosition;
	}

	public double getX() {
		return mousePoint.getX();
	}

	public double getY() {
		return mousePoint.getY();
	}

	public void setFromPosition(Position pos) {
		fromPosition = pos;
		
	}

}
