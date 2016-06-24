package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.character.Position;

public class Player implements IPlayer {
	private List<ICharacter> figures;
	private String playerName;

	public Player(String playerName, List<ICharacter> figures) {
		this.playerName = playerName;
		this.figures = figures;
	}



	public CHARAKTER_NAME getFigur(Position pos) {
		for (ICharacter cr : figures) {
			if (pos.equals(cr.getPosition()))
				return cr.getName();
		}
		return null;
	}


	public String getPlayerName() {
		return playerName;
	}

	public boolean setFigureChangePositon(Position start, Position end) {
		for (ICharacter cr : figures) {
			if (cr.getPosition().equals(start)) {
				cr.setPosition(end);
				return true;
			}
		}
		return false;
	}
	
	
	//vor GUI
	public List<ICharacter> getFigures(){
		return figures;
	}
}
