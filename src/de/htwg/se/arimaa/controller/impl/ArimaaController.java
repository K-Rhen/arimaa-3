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

	private GameStatus status;
	private String statusText;

	@Inject
	public ArimaaController() {
		initArimaaController();
	}

	private void initArimaaController() {
		pitch = new Pitch();
		rules = new Rules(pitch);

		status = GameStatus.IDEL;
		statusText = "";
	}

	@Override
	public int getRemainingMoves() {
		return pitch.getRemainingMoves();
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
		pitch.changePlayer();

		status = GameStatus.CHANGEPLAYER;
		notifyObservers();
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
		// status = GameStatus.REDO;
		notifyObservers();
	}

	@Override
	public PLAYER_NAME getCurrentPlayerName() {
		return pitch.getCurrentPlayer();
	}

	@Override
	public String currentPitchView() {
		return pitch.toString();
	}

	// TODO refactro
	@Override
	public boolean moveFigure(Position from, Position to) {
		// Preconditions
		if (!rules.precondition(from, to)) {
			statusText = rules.getStatusText();
			status = rules.getStatus();
			notifyObservers();
			return false;
		}

		// Move the figure
		undoManager.doCommand(new MoveFigureCommand(pitch, from, to));

		// Postconditions
		if (!rules.postcondition(from, to)) {
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
	public String getMoveHistoryText() {
		return undoManager.toString();
	}

	


	@Override
	public PLAYER_NAME getPlayerNamebyPosition(Position pos) {
		IPlayer player = pitch.getPlayer(pos);
		
		if (player == null)
			return null;
		
		return player.getPlayerName();
	}
	
	@Override
	public FIGURE_NAME getFigureNamebyPosition(Position pos) {
		IPlayer player = pitch.getPlayer(pos);
			
		if (player == null)
			return null;

		return player.getFigure(pos);
	}
}
