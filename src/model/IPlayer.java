package model;

import util.position.Position;

import java.util.List;

public interface IPlayer {

	/**
	 * get name of the player
	 * 
	 * @return the name of the player
	 */
	PLAYER_NAME getPlayerName();

	/**
	 * get the figure name form a position
	 *
	 * @param pos
	 *            the position of the figure
	 * @return the name of the figure, null if not exist
	 */
	FIGURE_NAME getFigure(Position pos);

	/**
	 * Set the position from figure with end position
	 * 
	 * @param start
	 *            position
	 * @param end
	 *            position
	 * @throws IllegalArgumentException
	 *             if figure on start position not there
	 */
	void moveFigure(Position start, Position end);

	/**
	 * disable figure
	 * 
	 * @param pos
	 *            position of the figure
	 * @return true if done, else no figure from player on this position
	 */
	boolean disableFigure(Position pos);

	/**
	 * gives the list of all figures from player
	 * 
	 * @return list of figures
	 */
	List<IFigure> getFigures();

}
