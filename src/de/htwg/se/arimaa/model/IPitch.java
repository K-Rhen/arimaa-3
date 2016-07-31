package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.position.Position;

public interface IPitch {
	/**
	 * return current player
	 */
	PLAYER_NAME getCurrentPlayer();

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
	 * Get the player object from the player name
	 * 
	 * @param playerName
	 *            of the player
	 * @return the player
	 */
	IPlayer getPlayer(PLAYER_NAME playerName);

	/**
	 * return the player of the figure on the position
	 * 
	 * @param pos postiton on ptich
	 * @return null, if no player have on this position a figure
	 */
	IPlayer getPlayer(Position pos);

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
	 * reduce the remainingMoves
	 * 
	 * @param count
	 * @return false if count > remainingMoves
	 */
	boolean reduceRemainingMoves(int count);

}
