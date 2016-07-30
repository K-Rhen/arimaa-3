package de.htwg.se.arimaa.util.command;

import java.util.Deque;
import java.util.LinkedList;


public class UndoManager {
	
	private static Deque<UndoableCommand> undoStack = new LinkedList<UndoableCommand>();
	private static Deque<UndoableCommand> redoStack = new LinkedList<UndoableCommand>();

	private static UndoableCommand topCommand;


	public void doCommand(UndoableCommand newCommand) {
		newCommand.doCommand();
		undoStack.push(newCommand);
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
			undoStack.push(topCommand); //switch forth
		}
	}

	public void reset() {
		undoStack.clear();
	}
	
	@Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
		
        Deque<UndoableCommand> tmpStack = undoStack;
        
        int i = tmpStack.size();
        for (UndoableCommand uC : tmpStack) {
        	sb.append(i-- + " ");
        	sb.append(uC.toString() + "\n");
		}
		
		return sb.toString();
	}
	
}
