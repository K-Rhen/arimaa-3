package de.htwg.se.arimaa.controller;

import java.util.List;

import de.htwg.se.arimaa.model.FIGURE_NAME;
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
	PLAYER_NAME getCurrentPlayerName();

	/**
	 * get the current pitch view
	 */
	String getPitchView();

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
	 * undo the last move
	 */
	void undo();

	/**
	 * redo the last move
	 */
	void redo();

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
	 * get the figure name from a position of the pitch
	 * 
	 * @param pos
	 *            on the pitch
	 * @return figure name, else null
	 */
	FIGURE_NAME getFigureName(Position pos);

	/**
	 * get the player name of the figure
	 * 
	 * @param pos
	 *            on the pitch
	 * @return player name, else null
	 */
	PLAYER_NAME getPlayerName(Position pos);

	/**
	 * get the History of moves as String
	 * 
	 * @return history of moves
	 */
	String getMoveHistoryText();
	
	/**
	 * tells if the undo list is empty
	 * @return true if empty, else false
	 */
	boolean isUndoListEmpty();
	
	/**
	 * tells if the redo list is empty
	 * @return true if empty, else false
	 */
	boolean isRedoListEmpty();
	
	/**
	 * get all possible moves from given position an move remains
	 * @param from the given position
	 * @return list of possible positions
	 */
	List<Position> getPossibleMoves(Position from);

	/**
	 * get state if change player is able
	 * @return true if able, else false
	 */
	boolean isChangePlayerEnable();

	/**
	 * get the last figure name if a figure was moved before
	 * @return last figure name, else null
	 */
	FIGURE_NAME getLastMoveFigureName();

	/**
	 * get the last moved figure position 
	 * @return position if exist, else null
	 */
	Position getLastMoveFromPosition();

	/**
	 * disable this figure
	 * @param pos
	 */
	void disableFigure(Position pos);

	/**
	 * tell if the given player has no rabbit figures on pitch
	 * @param playerName
	 * @return true if player has no rabbits left, else false
	 */
	boolean noRabbits(PLAYER_NAME playerName);

}
