package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.impl.Character;
import de.htwg.se.arimaa.model.impl.Player;

public interface IPlayerFactory {
	
	static Player getInstance(String playerName, List<Character> figures){
		return null;
	}

}
