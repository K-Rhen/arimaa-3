package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.controller.impl.PLAYER_NAME;
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
     * @return the textual representation of the extended status.
     */
    String getStatusText();
	
	/**
	 * exit game handler
	 */
	void arimaaExit();

	/**
	 * change the player
	 */
	void changePlayer();

	/**
	 * get the name of the current player
	 * @return player name
	 */
	PLAYER_NAME getCurrentPlayer();

	/**
	 * get the name oft the next player
	 * @return next player name
	 */
	PLAYER_NAME getNextPlayer();

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
	boolean moveFigure(PLAYER_NAME player, Position from, Position to);


	/**
	 * init a new game
	 */
	void create();

}
