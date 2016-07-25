package de.htwg.se.arimaa.model.impl;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public class Figure implements IFigure {
	private Position position;
	private FIGURE_NAME name;
	private PLAYER_NAME player;

	public Figure(Position positon, FIGURE_NAME name, PLAYER_NAME player) {
		this.position = positon;
		this.name = name;
		this.player = player;
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
	public PLAYER_NAME getPlayer() {
			return player;
	}

	@Override
	public String toString() {
		return "{" +player.name() +" "+ name + " " + position.toString() + "}";
	}


}
