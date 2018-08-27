package controller.impl;

import model.FIGURE_NAME;
import model.IPitch;
import model.PLAYER_NAME;
import util.command.UndoableMoveFigureCommand;
import util.position.Coordinate;
import util.position.Position;

public class MoveFigureCommand implements UndoableMoveFigureCommand {

	private final Position from;
	private final Position to;
	private final FIGURE_NAME figureName;

	private final IPitch pitch;
	private final PLAYER_NAME currentPlayerName;
	private final int remainingMoves;

	private String moveNotation;

	public MoveFigureCommand(IPitch pitch, Position from, Position to) {
		this.pitch = pitch;
		this.currentPlayerName = pitch.getCurrentPlayerName();
		this.remainingMoves = pitch.getRemainingMoves();

		this.from = from;
		this.to = to;
		this.figureName = pitch.getFigureName(from);
	}

	@Override
	public void doCommand() {
		move(from, to);
	}

	@Override
	public void undoCommand() {
		move(to, from);
	}

	@Override
	public void redoCommand() {
		move(from, to);
	}

	private void move(Position from, Position to) {
		genMoveNotation();
		pitch.moveFigure(from, to);

		pitch.setCurrentPlayer(currentPlayerName);
		pitch.setRemainingMoves(remainingMoves);
	}

	private void genMoveNotation() {
		moveNotation = pitch.getFigureNameForPitch(from) + "" + Coordinate.convert(from) + ""
				+ Position.getDirection(from, to);
	}

	@Override
	public PLAYER_NAME getPlayerName() {
		return currentPlayerName;
	}

	@Override
	public FIGURE_NAME getFigureName() {
		return figureName;
	}

	@Override
	public Position getFromPosition() {
		return from;
	}

	@Override
	public String toString() {
		return moveNotation;
	}
}
