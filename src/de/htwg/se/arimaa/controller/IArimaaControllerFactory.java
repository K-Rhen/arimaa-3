package de.htwg.se.arimaa.controller;

import de.htwg.arimaa.controller.impl.ArimaaController;


public interface IArimaaControllerFactory {

	
	static ArimaaController getInstance(){
		return new ArimaaController();
	}
}
