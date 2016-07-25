package de.htwg.se.arimaa.controller;

import java.util.List;

import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.observer.IObservable;
import de.htwg.se.arimaa.util.position.Position;

public interface IArimaaController extends IObservable {

	/**
	 * get current remain moves
	 * 
	 * @return remain moves
	 */
	int getRemainingMoves();

	/**
	 * get game status
	 * 
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
	void quitGame();

	/**
	 * change the player
	 */
	void changePlayer();

	/**
	 * get the name of the current player
	 * 
	 * @return player name
	 */
	PLAYER_NAME getCurrentPlayer();

	/**
	 * get the name oft the next player
	 * 
	 * @return next player name
	 */
	PLAYER_NAME getNextPlayer();

	/**
	 * get the current pitch view
	 */
	String currentPitchView();

	/**
	 * move a specific figure to a given position
	 * 
	 * @param from
	 *            position
	 * @param to
	 *            position
	 * @return true on success
	 */
	boolean moveFigure(Position from, Position to);

	/**
	 * init a new game
	 */
	void createNewGame();

	/**
	 * get all gold figures
	 * 
	 * @return gold figures
	 */
	List<IFigure> getGoldFigures();

	/**
	 * get all silver figures
	 * 
	 * @return silver figures
	 */
	List<IFigure> getSilverFigures();
	
	/**
	 * reduce the remainingMoves 
	 * @param count
	 * @return false if count > remainingMoves
	 */
	boolean reduceRemainingMoves(int count);


}
