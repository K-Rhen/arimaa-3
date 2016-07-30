package de.htwg.se.arimaa.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.command.UndoManager;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;



public class ArimaaController extends Observable implements IArimaaController {
	private static final Logger LOGGER = LogManager.getLogger(ArimaaController.class.getName());

	private UndoManager undoManager = new UndoManager();
	
	private IPitch pitch;
	private Rules rules;
	private int remainingMoves;
	private PLAYER_NAME currentPlayer;
	private GameStatus status;
	private String statusText;

	@Inject
	public ArimaaController() {
		initArimaaController();
	}

	private void initArimaaController() {
		pitch = new Pitch();
		rules = new Rules(this);
		remainingMoves = 4;
		currentPlayer = PLAYER_NAME.GOLD;
		status = GameStatus.IDEL;
		statusText = "";
	}

	@Override
	public int getRemainingMoves() {
		return remainingMoves;
	}

	@Override
	public GameStatus getGameStatus() {
		return status;
	}

	@Override
	public String getStatusText() {
		return statusText;
	}

	@Override
	public void quitGame() {
		status = GameStatus.EXIT;
		notifyObservers();
	}

	@Override
	public void changePlayer() {
		currentPlayer = getNextPlayerName();

		remainingMoves = 4;

		status = GameStatus.CHANGEPLAYER;
		notifyObservers();
	}

	private PLAYER_NAME getNextPlayerName() {
		if (currentPlayer.equals(PLAYER_NAME.GOLD))
			return PLAYER_NAME.SILVER;
		else
			return PLAYER_NAME.GOLD;
	}

	@Override
	public void createNewGame() {
		initArimaaController();		
		status = GameStatus.CREATE;
		notifyObservers();
	}
	
	@Override
	public void undo() {
		undoManager.undoCommand();
		notifyObservers();
	}

	@Override
	public void redo() {
		undoManager.redoCommand();
		//status = GameStatus.REDO;
		notifyObservers();
	}
	

	@Override
	public PLAYER_NAME getCurrentPlayerName() {
		return currentPlayer;
	}

	@Override
	public String currentPitchView() {
		return pitch.toString();
	}

	@Override
	public boolean reduceRemainingMoves(int count) {
		if (remainingMoves == 0) {
			LOGGER.error("reduceMove are 0");
			return false;
		}

		remainingMoves--;
		status = GameStatus.REMAINMOVE_CHANGE;
		notifyObservers();
		return true;
	}

	// TODO refactro
	@Override
	public boolean moveFigure(Position from, Position to) {
		PLAYER_NAME playerName = getPlayerNamebyPosition(from);
		IPlayer player = pitch.getPlayer(playerName);

		// Preconditions
		if (!rules.precondition(player, from, to)) {
			statusText = rules.getStatusText();
			status = rules.getStatus();
			notifyObservers();
			return false;
		}

		//Move the figure
		undoManager.doCommand(new MoveFigureCommand(player,from, to));
		
		// Postconditions
		if (!rules.postcondition(player, from, to)) {
			statusText = rules.getStatusText();
			status = rules.getStatus();
			notifyObservers();
			return false;
		}

		statusText = "";
		status = GameStatus.MOVEFIGURE;
		notifyObservers();
		return true;
	}


	@Override
	public List<IFigure> getGoldFigures() {
		return pitch.getGoldPlayer().getFigures();
	}

	@Override
	public List<IFigure> getSilverFigures() {
		return pitch.getSilverPlayer().getFigures();
	}

	@Override
	public FIGURE_NAME getFigureNamebyPosition(Position pos) {
		PLAYER_NAME player = getPlayerNamebyPosition(pos);

		if (player == null)
			return null;
		
		if (player.equals(PLAYER_NAME.GOLD))
			return pitch.getGoldPlayer().getFigure(pos);

		return pitch.getSilverPlayer().getFigure(pos);

	}

	//TODO need ?
	@Override
	public PLAYER_NAME getPlayerNamebyPosition(Position pos) {
		if (pitch.getGoldPlayer().getFigure(pos) != null)
			return PLAYER_NAME.GOLD;
		if (pitch.getSilverPlayer().getFigure(pos) != null)
			return PLAYER_NAME.SILVER;

		return null;
	}

}
