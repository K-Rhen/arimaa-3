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
		if(p1.getX() - p2.getX() > 1 || p2.getX() - p1.getX() > 1)
			return false;
		if(p1.getY() - p2.getY() > 1 || p2.getY() - p1.getY() > 1 )
			return false;
		return true;
	}
}
