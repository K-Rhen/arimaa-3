package de.htwg.se.arimaa.controller.impl;

import java.util.List;

import com.google.inject.Inject;

import de.htwg.se.arimaa.controller.GameStatus;
import de.htwg.se.arimaa.controller.IArimaaController;
import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.model.impl.Pitch;
import de.htwg.se.arimaa.util.command.UndoManager;
import de.htwg.se.arimaa.util.observer.Observable;
import de.htwg.se.arimaa.util.position.Position;

public class ArimaaController extends Observable implements IArimaaController {
	private UndoManager undoManager;

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
		rules = new Rules(this);
		undoManager = new UndoManager();

		status = GameStatus.CREATE;
		statusText = "New game started";
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
		statusText = "Have a nice day ;)";
		notifyObservers();
	}

	@Override
	public void changePlayer() {
		if (status.equals(GameStatus.PUSHFIGURE))
			return;

		pitch.changePlayer();

		status = GameStatus.CHANGEPLAYER;
		statusText = pitch.getCurrentPlayerName().toString() + " it’s your turn";
		notifyObservers();
	}

	@Override
	public void createNewGame() {
		initArimaaController();
		status = GameStatus.CREATE;
		statusText = "New game started";
		notifyObservers();
	}

	@Override
	public void undo() {
		undoManager.undoCommand();

		status = GameStatus.UNDO;
		statusText = "-";
		notifyObservers();
	}

	@Override
	public void redo() {
		undoManager.redoCommand();

		status = GameStatus.REDO;
		statusText = "-";
		notifyObservers();
	}

	@Override
	public PLAYER_NAME getCurrentPlayerName() {
		return pitch.getCurrentPlayerName();
	}

	@Override
	public String getPitchView() {
		return pitch.toString();
	}

	@Override
	public boolean moveFigure(Position from, Position to) {
		boolean moved = false;

		// Preconditions
		moved = rules.precondition(from, to);
		if (moved) {

			// Move the figure
			undoManager.doCommand(new MoveFigureCommand(pitch, from, to));

			// reduce remaining moves
			pitch.reduceRemainingMoves(1);
		}

		// Postconditions
		rules.postcondition(from, to);

		status = rules.getStatus();
		statusText = rules.getStatusText();

		notifyObservers();
		return moved;
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
	public PLAYER_NAME getPlayerName(Position pos) {
		return pitch.getPlayerName(pos);
	}

	@Override
	public FIGURE_NAME getFigureName(Position pos) {
		return pitch.getFigureName(pos);
	}

	@Override
	public boolean isUndoListEmpty() {
		return undoManager.isUndoListEmpty();
	}

	@Override
	public boolean isRedoListEmpty() {
		return undoManager.isRedoListEmpty();
	}

	@Override
	public List<Position> getPossibleMoves(Position from) {

		return rules.getPossibleMoves(from);
	}

	@Override
	public boolean isChangePlayerEnable() {
		return pitch.isChangePlayerEable();
	}

	@Override
	public FIGURE_NAME getLastMoveFigureName() {
		return undoManager.getLastMoveFigureName();
	}

	@Override
	public Position getLastMoveFromPosition() {
		return undoManager.getLastMoveFromPosition();
	}

	@Override
	public void disableFigure(Position pos) {
		pitch.disableFigure(pos);
	}
}
