package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.character.Position;

public class Character {

	public enum CHARAKTER_NAME {
		R, C, D, H, L, E
	};

	private Position positon;
	private CHARAKTER_NAME name;

	public Character(Position positon, CHARAKTER_NAME name) {
		this.positon = positon;
		this.name = name;
	}

	public void setPosition(int x, int y) {
		positon = new Position(x, y);
	}

	public Position getPosition() {
		return positon;
	}

	public CHARAKTER_NAME getName() {
		return name;
	}
}
