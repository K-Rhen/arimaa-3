package de.htwg.se.arimaa.model.impl;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public class Figure implements IFigure {
	private Position position;
	private FIGURE_NAME name;

	public Figure(Position positon, FIGURE_NAME name) {
		this.position = positon;
		this.name = name;
	}

	@Override
	public void setPosition(Position pos) {
		position = pos;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public FIGURE_NAME getName() {
		return name;
	}
	

	@Override
	public String toString() {
		return "{" + name + " " + position.toString() + "}";
	}


}
