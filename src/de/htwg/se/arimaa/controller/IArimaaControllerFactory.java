package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.controller.impl.ArimaaController;


public interface IArimaaControllerFactory {

	
	static ArimaaController getInstance(){
		return new ArimaaController();
	}
}
