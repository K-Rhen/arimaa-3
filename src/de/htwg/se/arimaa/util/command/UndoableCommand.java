package de.htwg.se.arimaa.util.command;


public interface UndoableCommand {
    public void doCommand();
    public void undoCommand();
    public void redoCommand();
}
