package de.htwg.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.model.impl.Player;
import de.htwg.se.arimaa.util.character.Position;
import de.htwg.se.arimaa.util.observer.Observable;

public class Rules extends Observable {

	private Player p1;
	private Player p2;

	

	

	private List<IPitch> pitchList = new ArrayList<>();

	public Rules(IPitch pitch) {
		this.p1 = pitch.getP1();
		this.p2 = pitch.getP2();
	}
	


	public boolean occupiedCell(Position pos) {
		if (p1.getFigur(pos) != null || p2.getFigur(pos) != null)
			return true;

		return false;
	}

	public boolean posDistance(Position p1, Position p2) {
		if (p1.getX() - p2.getX() > 1 || p2.getX() - p1.getX() > 1)
			return false;
		if (p1.getY() - p2.getY() > 1 || p2.getY() - p1.getY() > 1)
			return false;
		return true;
	}

	public boolean pitchAlreadyExisted(IPitch a) { // true if NOT already
													// existed

		boolean found = false;
		for (IPitch b : pitchList)
			if (a.toString().equals(b.toString())) {
				found = true;
			}

		if (!found) {
			pitchList.add(a);
		}
		return !found;
	}
	

}
