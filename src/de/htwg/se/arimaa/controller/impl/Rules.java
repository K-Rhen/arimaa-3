package de.htwg.se.arimaa.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IPitch pitch;
	private GameStatus status;
	private String statusText = "";

	public Rules(IPitch pitch) {
		this.pitch = pitch;
	}

	public GameStatus getStatus() {
		return status;
	}

	public String getStatusText() {
		return statusText;
	}

	// TODO precondition RULES
	public boolean precondition(Position from, Position to) {
		IPlayer player = pitch.getPlayer(from);

		// figure position is empty from given player
		if (player == null) {
			statusText = "No figure on " + Coordinate.convert(from);
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// no moves remain
		if (pitch.getRemainingMoves() == 0) {
			statusText = "No remain moves";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		//
		List<Position> possibleMoves = getPossibleMoves(from);
		if(!possibleMoves.contains(to)){
			statusText = Coordinate.convert(to) + " is not a permitted position";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}
		
		// TODO figure not trapped

	
		return true;
	}

	// TODO postcondition RULELS
	public boolean postcondition(Position from, Position to) {
		// TODO move steps
		pitch.reduceRemainingMoves(1);

		
		// TODO is finish rule
		// TODO TRAPP rule

		return true;
	}

	public List<Position> getPossibleMoves(Position pos) {
		List<Position> canditates = new ArrayList<>();
		canditates = Position.getSurroundPosition(pos);
		removeOccupiedPositons(canditates);
		return canditates;
	}

	private void removeOccupiedPositons(List<Position> positions) {
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (pitch.getPlayer(positions.get(i)) != null)
				positions.remove(positions.get(i));
		}
	}
}
