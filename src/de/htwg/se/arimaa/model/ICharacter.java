package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.ICHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface ICharacter {

	Position getPosition();

	void setPosition(Position to);

	ICHARAKTER_NAME getName();
	
	

}
