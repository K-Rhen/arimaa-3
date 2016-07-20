package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.Pitch;

public interface IPitchFactory {
	
	 Pitch getInstance(String player1Name, String player2Name);
	  
}
