package de.htwg.se.arimaa.controller.impl;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.command.UndoableCommand;
import de.htwg.se.arimaa.util.position.Position;

public class MoveFigureCommand implements UndoableCommand {

	private Position from;
	private Position to;
	private IPlayer player;
    
    public MoveFigureCommand(IPlayer player,Position from, Position to) {
    	this.player = player;
        this.from = from;
        this.to = to;
    }

    @Override
    public void doCommand() {
    	boolean able = player.moveFigure(from, to);
    	if(!able)
    		throw new IllegalArgumentException("doCommand fail: " + to.toString()+ "-" + from.toString());
    }

    @Override
    public void undoCommand() {
    	boolean able = player.moveFigure(to, from);
    	if(!able)
    		throw new IllegalArgumentException("undoCommand fail: " + to.toString()+ "-" + from.toString());
    }
    
    @Override
    public void redoCommand() {
    	doCommand();
    }
}
