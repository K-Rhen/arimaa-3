package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.model.IPitch;
import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.command.UndoableCommand;
import de.htwg.se.arimaa.util.position.Coordinate;
import de.htwg.se.arimaa.util.position.Position;

public class MoveFigureCommand implements UndoableCommand {

	private Position from;
	private Position to;
	private IPlayer player;

	private IPitch pitch;
	private PLAYER_NAME currentPlayer;
	private int remainingMoves;

	private String moveNotation;

	public MoveFigureCommand(IPitch pitch, Position from, Position to) {
		this.pitch = pitch;
		this.currentPlayer = pitch.getCurrentPlayer();
		this.remainingMoves = pitch.getRemainingMoves();

		this.player = pitch.getPlayer(from);
		this.from = from;
		this.to = to;
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
		player.moveFigure(from, to);

		pitch.setCurrentPlayer(currentPlayer);
		pitch.setRemainingMoves(remainingMoves);
	}

	private void genMoveNotation() {
		moveNotation = currentPlayer + "#"+pitch.getFigureNameForPitch(from) + "" + Coordinate.convert(from) + ""
				+ Position.getDirection(from, to);
	}

	@Override
	public String toString() {
		return moveNotation;
	}
}
