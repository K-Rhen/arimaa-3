package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.impl.CHARAKTER_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface IPlayer {

	CHARAKTER_NAME getFigur(Position from);

	String getPlayerName();

	boolean setFigureChangePositon(Position start, Position end);

	List<ICharacter> getFigures();

}
