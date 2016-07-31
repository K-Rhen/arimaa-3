package de.htwg.se.arimaa.controller.impl;

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
	public boolean precondition( Position from, Position to) {
		IPlayer player = pitch.getPlayer(from);
		
		// figure position is empty from given player
		if(player == null){
			statusText = "No figure on "+ Coordinate.convert(from);
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}
		
		
		// no moves remain
		if (pitch.getRemainingMoves() == 0) {
			statusText = "No remain moves";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

	
		
		// TODO figure not trapped

		//TODO move steps
		pitch.reduceRemainingMoves(0);
		
		return true;
	}

	// TODO postcondition RULELS
	public boolean postcondition( Position from, Position to) {
	
		
		// TODO is finish rule
		// TODO TRAPP rule
		
		return true;
	}
}
