package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.util.observer.Observable;

//TOOD refactor
public class Rules extends Observable {

	private IArimaaController controller;
	
	public Rules(IArimaaController controller) {
		this.controller = controller;
	}

}
