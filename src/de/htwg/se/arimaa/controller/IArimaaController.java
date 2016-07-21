package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.IObservable;
import de.htwg.se.arimaa.util.position.Position;



public interface IArimaaController extends IObservable {

	/**
	 * get current remain moves
	 * @return remain moves
	 */
	int getRemainingMoves();

	/**
	 * get game status
	 * @return game status
	 */
	GameStatus getGameStatus();

	/**
	 * exit game handler
	 */
	void arimaaExit();

	/**
	 * change the player
	 */
	void changePlayer();

	/**
	 * get the number of the current player
	 * @return number of current player
	 */
	int getCurrentPlayer();

	/**
	 * number of the next player
	 * @return next player number
	 */
	int getNextPlayer();

	/**
	 * get the current pitch view
	 */
	String CurrentPitchView();

	/**
	 * move a specifig figure to a given position
	 * @param player number of the player
	 * @param from position
	 * @param to position
	 * @return true on success
	 */
	boolean moveFigure(int player, Position from, Position to);


	/**
	 * init a new game
	 */
	void create();

}
