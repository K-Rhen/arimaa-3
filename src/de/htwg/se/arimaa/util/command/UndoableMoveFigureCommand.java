package de.htwg.se.arimaa.util.command;

import de.htwg.se.arimaa.model.FIGURE_NAME;
import de.htwg.se.arimaa.model.PLAYER_NAME;
import de.htwg.se.arimaa.util.position.Position;

public interface UndoableMoveFigureCommand {
	public void doCommand();

	public void undoCommand();

	public void redoCommand();
	
	public PLAYER_NAME getPlayerName();
	
	public FIGURE_NAME getFigureName();
	
	public Position getFromPosition();
}
