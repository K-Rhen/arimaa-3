package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface ICharacter {

	Position getPosition();

	void setPosition(Position to);

	CHARAKTER_NAME getName();
	
	

}
