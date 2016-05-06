package de.htwg.se.arimaa.model;

import java.util.ArrayList;

import de.htwg.se.arimaa.model.Character.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public class Player {
	private ArrayList<Character> figures;
	private String playerName;

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public void setFigur(CHARAKTER_NAME cname, Position pos) {
		this.playerName = playerName;

		// TODO POSITON FIGURS VARIABLE
		figures = new ArrayList<Character>();
		figures.add(new Character(new Position(0, 0), CHARAKTER_NAME.RABBIT));
		figures.add(new Character(new Position(0, 1), CHARAKTER_NAME.ELEPHANT));
		// TODO INIT ALL FIGURES
		// TODO TEST CLEAN
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

}
