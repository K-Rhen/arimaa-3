package de.htwg.se.arimaa.model.impl;

import de.htwg.se.arimaa.model.ICHARAKTER_NAME;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.util.character.Position;

public class Character implements ICharacter{

	private Position position;
	private ICHARAKTER_NAME name;

	public Character(Position positon, ICHARAKTER_NAME name) {
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

	public ICHARAKTER_NAME getName() {
		return name;
	}
}
