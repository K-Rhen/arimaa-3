package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IArimaaController controller;
	private GameStatus status;
	private String statusText = "";

	public Rules(IArimaaController controller) {
		this.controller = controller;
	}

	public GameStatus getStatus() {
		return status;
	}

	public String getStatusText() {
		return statusText;
	}

	// TODO precondition RULES
	public boolean precondition(IPlayer player, Position from, Position to) {
		// no moves remain
		if (controller.getRemainingMoves() == 0) {
			statusText = "No remain moves";
			status = GameStatus.PRECONDITIONRULES_VIOLATED;
			return false;
		}

		// TODO figure not trapped

		return true;
	}

	// TODO postcondition RULELS
	public boolean postcondition(IPlayer player, Position from, Position to) {
		//TODO move steps
		controller.reduceRemainingMoves(0);
		
		// TODO is finish rule
		// TODO TRAPP rule
		
		return true;
	}
}
