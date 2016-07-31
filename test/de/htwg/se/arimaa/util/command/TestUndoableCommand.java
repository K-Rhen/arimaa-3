package de.htwg.se.arimaa.util.command;

import de.htwg.se.arimaa.util.command.UndoableCommand;

public class TestUndoableCommand implements UndoableCommand {
	
	TestReceiver sum;
	
	public TestUndoableCommand(TestReceiver sum) {
		this.sum = sum;
	}

	@Override
	public void doCommand() {
		sum.add(1);
	}

	@Override
	public void undoCommand() {
		sum.add(-1);
	}

	@Override
	public void redoCommand() {
		sum.add(1);
	}

	
	@Override
	public String toString() {
		return sum.toString();
	}
}