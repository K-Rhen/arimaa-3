package de.htwg.se.arimaa.aview.gui;

import java.awt.Point;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.PLAYER_NAME;

public class MouseFigure {

	Point position;
	FIGURE_NAME figureName;
	PLAYER_NAME player;
	
	public MouseFigure(	Point position,FIGURE_NAME figureName,PLAYER_NAME player) {
		this.position = position;
		this.figureName = figureName;
		this.player = player;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public FIGURE_NAME getFigureName() {
		return figureName;
	}

	public PLAYER_NAME getPlayer() {
		return player;
	}
	
}
