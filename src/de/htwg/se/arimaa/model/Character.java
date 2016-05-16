package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.character.Position;

public class Character {

	private Position position;
	private CHARAKTER_NAME name;

	public Character(Position positon, CHARAKTER_NAME name) {
		this.position = positon;
		this.name = name;
	}

	public void setPosition(int x, int y) {
		position = new Position(x, y);
	}

	public void setPosition(Position newpos) {
		position = new Position(newpos.getX(), newpos.getY());
	}

	public Position getPosition() {
		return position;
	}

	public CHARAKTER_NAME getName() {
		return name;
	}
}
