package util.command;

import model.FIGURE_NAME;
import model.PLAYER_NAME;
import util.position.Position;

public interface UndoableMoveFigureCommand {
    void doCommand();

    void undoCommand();

    void redoCommand();

    PLAYER_NAME getPlayerName();

    FIGURE_NAME getFigureName();

    Position getFromPosition();
}
