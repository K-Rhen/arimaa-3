package de.htwg.se.arimaa.model;

import java.util.List;

import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.util.character.Position;

public interface IPlayer {
	/**
	 * get the name of the player
	 * 
	 * @return the name of the player
	 */
	String getPlayerName();

	/**
	 * get the figure name form a position
	 * 
	 * @param cell
	 *            the position of the figure
	 * @return the name of the figure, null if not exist
	 */
	IFIGURE_NAME getFigure(Position pos);

	/**
	 * Set the position from figure with end position
	 * 
	 * @param start
	 *            position
	 * @param end
	 *            position
	 * @return false if figure on start position not there, else true
	 */
	boolean moveFigure(Position start, Position end);

	/**
	 * remove figure from player figures list
	 * 
	 * @param pos
	 *            position of the figure
	 * @return true if done, else no figure from player on this position
	 */
	boolean deleteFigure(Position pos);

	/**
	 * gives the list of all figures from player
	 * @return list of figures
	 */
	List<IFigure> getFigures();

}
