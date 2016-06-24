package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.ICharacter;
import de.htwg.se.arimaa.model.impl.Pitch;

public interface IPitchFactory {
	
	  static Pitch getInstance(String player1Name, String player2Name, List<ICharacter> figures1,
			List<ICharacter> figures2) {
		return new Pitch(player1Name, player2Name);
	}
	  
}
