package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface IPlayer {

	IFIGURE_NAME getFigur(Position from);

	String getPlayerName();

	boolean setFigureChangePositon(Position start, Position end);

	List<IFigure> getFigures();

	boolean deleteFigure(Position to);
	
}
