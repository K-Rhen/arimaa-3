package util.command;

import model.FIGURE_NAME;
import model.PLAYER_NAME;
import util.position.Position;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class UndoManager {

    private Deque<UndoableMoveFigureCommand> undoStack;
    private Deque<UndoableMoveFigureCommand> redoStack;

    private UndoableMoveFigureCommand topCommand;

    public UndoManager() {
        undoStack = new LinkedList<>();
        redoStack = new LinkedList<>();
        topCommand = null;
    }

    public void doCommand(UndoableMoveFigureCommand newCommand) {
        newCommand.doCommand();
        undoStack.push(newCommand);
        redoStack = new LinkedList<>(); // delete old
        // branch
    }

    public void undoCommand() {
        if (!undoStack.isEmpty()) {
            topCommand = undoStack.pop();
            topCommand.undoCommand();
            redoStack.push(topCommand);
        }
    }

    public void redoCommand() {
        if (!redoStack.isEmpty()) {
            topCommand = redoStack.pop();
            topCommand.redoCommand();
            undoStack.push(topCommand); // switch forth
        }
    }

    public void reset() {
        undoStack.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<UndoableMoveFigureCommand> undoList = new ArrayList<>(undoStack);
        undoList = reverse(undoList);

        PLAYER_NAME currentPlayerName = PLAYER_NAME.SILVER;
        int row = 0;
        int column = 0;
        for (int i = 0; i < undoList.size(); i++) {
            PLAYER_NAME ulPlayerName = undoList.get(i).getPlayerName();
            if (!currentPlayerName.equals(ulPlayerName)) {
                currentPlayerName = ulPlayerName;

                if (currentPlayerName.equals(PLAYER_NAME.GOLD))
                    row++;

                if (i > 0 && column < 4) {
                    column = 0;
                    sb.append("pass ");
                }

                if (i > 0)
                    sb.append("\n");

                String playerNameNotation = currentPlayerName.toString().substring(0, 1).toLowerCase();
                sb.append(row + playerNameNotation + " ");
            }

            sb.append(undoList.get(i).toString() + " ");
            column++;
        }

        return sb.toString();
    }

    private List<UndoableMoveFigureCommand> reverse(List<UndoableMoveFigureCommand> undoList) {
        for (int i = 0, j = undoList.size() - 1; i < j; i++) {
            undoList.add(i, undoList.remove(j));
        }
        return undoList;
    }

    public boolean isUndoListEmpty() {
        return undoStack.isEmpty();
    }

    public boolean isRedoListEmpty() {
        return redoStack.isEmpty();
    }

    public FIGURE_NAME getLastMoveFigureName() {
        if (undoStack.isEmpty())
            return null;

        return undoStack.peek().getFigureName();
    }

    public Position getLastMoveFromPosition() {
        if (undoStack.isEmpty())
            return null;

        return undoStack.peek().getFromPosition();
    }

}
