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
	
    public MoveFigureCommand(IPitch pitch,Position from, Position to) {
    	this.pitch = pitch;
    	this.currentPlayer = pitch.getCurrentPlayer();
    	this.remainingMoves = pitch.getRemainingMoves();
    	
    	this.player = pitch.getPlayer(from);
        this.from = from;
        this.to = to;
    }

    @Override
    public void doCommand() {
    	move(from,to,"doCommand");
    }

    @Override
    public void undoCommand() {
    	move(to,from,"undoCommand");
    }
    
    @Override
    public void redoCommand() {
    	move(from,to,"redoCommand");
    }
    
    private void move(Position from, Position to, String failMethodeText){
    	boolean able = player.moveFigure(from, to);
    	if(!able)
    		throw new IllegalArgumentException(failMethodeText+ " fail: " + to.toString()+ "-" + from.toString());
    	
    	pitch.setCurrentPlayer(currentPlayer);
    	pitch.setRemainingMoves(remainingMoves);
    }
    
    @Override
    public String toString() {
    	return currentPlayer + ": " + Coordinate.convert(from)+"-"+ Coordinate.convert(to);
    }
    
}
