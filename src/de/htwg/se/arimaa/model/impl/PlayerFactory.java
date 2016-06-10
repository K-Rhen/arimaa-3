package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPlayerFactory;

public class PlayerFactory implements IPlayerFactory{
	
	public static Player getInstance(String playerName, List<ICharacter> figures){
		return new Player(playerName, figures);
	}

}
