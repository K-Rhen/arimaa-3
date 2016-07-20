package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface IFigure {

	Position getPosition();

	void setPosition(Position to);

	IFIGURE_NAME getName();
	
	

}
