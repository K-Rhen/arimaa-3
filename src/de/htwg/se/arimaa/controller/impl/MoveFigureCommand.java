package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.command.UndoableMoveFigureCommand;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

public class MoveFigureCommand implements UndoableMoveFigureCommand {

	private Position from;
	private Position to;
	private FIGURE_NAME figureName;

	private IPitch pitch;
	private PLAYER_NAME currentPlayerName;
	private int remainingMoves;

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

	// TODO remove split operation
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
