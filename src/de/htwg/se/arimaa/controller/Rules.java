package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.util.character.Position;

public class Rules {

	public boolean positionOnPitch(Position end) {
		if (end.getX() < 8 && end.getY() < 8 && end.getX() >= 0 && end.getY() >= 0)
			return true;
//		else if ()
//			return true;

		return false;
	}

}
