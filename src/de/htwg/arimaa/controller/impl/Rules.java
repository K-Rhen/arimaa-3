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

	
	private GameStatus gamestatus;
	

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
	
	public boolean isfinish(){
		if(finishP1()){
			gamestatus = GameStatus.WinPLAYER1;
			notifyObservers();
			return true;
		}
		
		if(finishP2()){
			gamestatus = GameStatus.WinPLAYER2;
			notifyObservers();
			return true;
		}
		return false;
		
	}

	private boolean finishP1() {
		for (ICharacter figure : p1.getFigures()) {
			if (figure.getName() == CHARAKTER_NAME.R
					&& figure.getPosition().getY() == 7)
				return true;
		}
		return false;

	}

	private boolean finishP2() {
		for (ICharacter figure : p1.getFigures()) {
			if (figure.getName() == CHARAKTER_NAME.r
					&& figure.getPosition().getY() == 0)
				return true;
		}
		return false;
	}
}
