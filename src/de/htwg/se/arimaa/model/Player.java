package de.htwg.se.arimaa.model;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class Player {
	private List<Character> figures;
	private String playerName;

	public Player(String playerName, List<Character> figures) {
		this.playerName = playerName;
		this.figures = figures;
	}

	public void setFigur(CHARAKTER_NAME cname, Position pos) {
		this.playerName = playerName;
	}

	public CHARAKTER_NAME getFigur(Position pos) {
		for (Character cr : figures) {
			if (pos.equals(cr.getPosition()))
				return cr.getName();
		}
		return null;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean setFigureChangePositon(Position start, Position end) {
		for (Character cr : figures) {
			if (cr.getPosition().equals(start)) {
				cr.setPosition(end);
				return true;
			}
		}
		return false;
	}
}
