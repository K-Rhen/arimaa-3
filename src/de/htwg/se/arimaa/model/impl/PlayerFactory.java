package de.htwg.se.arimaa.model.impl;

import java.util.List;

import de.htwg.se.arimaa.model.IPlayerFactory;

public class PlayerFactory implements IPlayerFactory{
	
	public static Player getInstance(String playerName, List<Character> figures){
		return new Player(playerName, figures);
	}

}
