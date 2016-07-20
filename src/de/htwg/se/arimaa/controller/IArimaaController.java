package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.IObservable;
import de.htwg.se.arimaa.util.position.Position;



public interface IArimaaController extends IObservable {

	/**
	 * get payer1
	 * @return player1
	 */
	IPlayer getPlayer1();

	/**
	 * get player2
	 * 
	 * @return player2
	 */
	IPlayer getPlayer2();

	/**
	 * get current remain moves
	 * @return remain moves
	 */
	int getMoveCounter();

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
	 * print the ptich on terminal
	 */
	void ShowPitch();

	/**
	 * move a specific figure with given string notation
	 * @param player number of the player
	 * @param inputLine position in string notation
	 * @return true on success
	 */
	boolean moveFigureByString(int player, String inputLine);

	/**
	 * move a specifig figure to a given position
	 * @param player number of the player
	 * @param from position
	 * @param to position
	 * @return true on success
	 */
	boolean moveFigureByPosition(int player, Position from, Position to);

	/**
	 * push a figure from another player
	 * @param firstPlayer player number 
	 * @param secondPlayer player number
	 * @param inputLine positions as string notation
	 * @return
	 */
	boolean pushFigurs(int firstPlayer, int secondPlayer, String inputLine);

}
