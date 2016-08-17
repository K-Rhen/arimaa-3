package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.position.Position;

public interface IPitch {
	/**
	 * return current player
	 */
	PLAYER_NAME getCurrentPlayerName();

	/**
	 * set current player
	 * 
	 * @param currentPlayer
	 */
	void setCurrentPlayer(PLAYER_NAME currentPlayer);

	/**
	 * return the gold player
	 * 
	 * @return gold player
	 */
	IPlayer getGoldPlayer();

	/**
	 * return the silver player
	 * 
	 * @return silver player
	 */
	IPlayer getSilverPlayer();

	/**
	 * return the player name of the figure on the position
	 * 
	 * @param pos
	 *            position on pitch
	 * @return null, if no player have on this position a figure
	 */
	PLAYER_NAME getPlayerName(Position pos);

	/**
	 * change the current player
	 */
	void changePlayer();

	/**
	 * get remaining moves
	 * 
	 * @return count of remaining moves
	 */
	int getRemainingMoves();

	/**
	 * set the remaining moves
	 * 
	 * @param remainingMoves
	 *            count of remaining moves
	 */
	void setRemainingMoves(int remainingMoves);

	/**
	 * Give the figure name as pitch notation
	 * 
	 * @param pos
	 *            position on pitch
	 * @return name of the figure for pitch
	 */
	String getFigureNameForPitch(Position pos);

	/**
	 * get the name of the figure
	 * 
	 * @param pos
	 *            a position on the pitch
	 * @return figure name, else null
	 */
	FIGURE_NAME getFigureName(Position pos);

	/**
	 * move a figure from a position to a another with no check of correctness
	 * 
	 * @param from
	 *            a position on the pitch, must be a figure on cell
	 * @param to
	 *            position on the pitch, must be a free cell
	 */
	void moveFigure(Position from, Position to);

	/**
	 * disable figure
	 * 
	 * @param pos
	 * @return false if no figure on the position
	 */
	boolean disableFigure(Position pos);

	/**
	 * tell if the given player has no rabbit figures on pitch
	 * 
	 * @param playerName
	 * @return true if player has no rabbits left, else false
	 */
	boolean noRabbits(PLAYER_NAME playerName);

	/**
	 * reduce remaining moves
	 * 
	 * @return true if remaining moves > 0
	 */
	boolean reduceRemainingMoves();
}
