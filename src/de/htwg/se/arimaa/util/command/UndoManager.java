package de.htwg.se.arimaa.util.command;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

import de.htwg.se.arimaa.model.PLAYER_NAME;

public class UndoManager {

	private Deque<UndoableCommand> undoStack;
	private Deque<UndoableCommand> redoStack;

	private UndoableCommand topCommand;

	public UndoManager() {
		undoStack = new LinkedList<UndoableCommand>();
		redoStack = new LinkedList<UndoableCommand>();
		topCommand = null;
	}

	public void doCommand(UndoableCommand newCommand) {
		newCommand.doCommand();
		undoStack.push(newCommand);
		redoStack = new LinkedList<UndoableCommand>(); // delete old branch
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

		ArrayList<UndoableCommand> undoList = new ArrayList<>(undoStack);
		undoList = reverse(undoList);

		String currentPlayerName = "Silver";
		int row = 0;
		int collumn = 0;
		for(int i = 0; i< undoList.size();i++){
			String line[] = undoList.get(i).toString().split("#");
			if (!currentPlayerName.equals(line[0])) {
				currentPlayerName = line[0];

				if (currentPlayerName.equals(PLAYER_NAME.GOLD.toString()))
					row++;

				if(i> 0 && collumn < 4){
					collumn = 0;
					sb.append("pass ");
				}
				
				if(i>0)
				sb.append("\n");

				String playerNameNotation = currentPlayerName.substring(0, 1).toLowerCase();
				sb.append(row + playerNameNotation + " ");
			}

			sb.append(line[1] + " ");
			collumn ++;
		}

		return sb.toString();
	}

	private ArrayList<UndoableCommand> reverse(ArrayList<UndoableCommand> undoList) {
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

}
