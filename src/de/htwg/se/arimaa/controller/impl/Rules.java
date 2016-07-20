package de.htwg.se.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

public class Rules extends Observable {

	private IPlayer p1;
	private IPlayer p2;

	

	

	private List<IPitch> pitchList = new ArrayList<>();

	public Rules(IPitch pitch) {
		this.p1 = pitch.getPlayer1();
		this.p2 = pitch.getPlayer2();
	}
	


	public boolean occupiedCell(Position pos) {
		if (p1.getFigure(pos) != null || p2.getFigure(pos) != null)
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
