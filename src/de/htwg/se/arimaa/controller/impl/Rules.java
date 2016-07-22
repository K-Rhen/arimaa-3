package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

//TOOD refactor
public class Rules extends Observable {

	private IArimaaController controller;

	public Rules(IArimaaController controller) {
		this.controller = controller;
	}

	// TODO precondition RULES
	public String precondition(PLAYER_NAME player, Position from, Position to) {
		// no moves remain
		if (controller.getRemainingMoves() == 0)
			return "No remain moves";

		// TODO figure not trapped

		return null;
	}

	// TODO postcondition RULELS
	public String postcondition(PLAYER_NAME player, Position from, Position to) {
		// TODO is finish rule
		// TODO TRAPP rule
		return null;
	}
}
