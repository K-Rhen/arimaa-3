package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.model.Pitch;
import de.htwg.se.arimaa.model.Player;
import de.htwg.se.arimaa.util.character.Position;

public class Rules {

	Player p1;
	Player p2;

	public Rules(Pitch pitch) {
		this.p1 = pitch.getP1();
		this.p2 = pitch.getP2();
	}

	public boolean occupiedCell(Position pos) {
		if (p1.getFigur(pos) != null || p2.getFigur(pos) != null)
			return true;

		return false;
	}
	public boolean posDistance(Position p1, Position p2){
		return false;
	}
}
