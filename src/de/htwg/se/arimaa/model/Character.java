package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.character.Position;

public class Character {

	public enum CHARAKTER_NAME {
		R, C, D, H, L, E, // player1
		r, c, d, h, l, e // player2
	};

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
