package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.impl.Player;

public interface IPlayerFactory {
	
	static IPlayer getInstance(String playerName, List<ICharacter> figures){
		return new Player(playerName, figures);
	}

}
