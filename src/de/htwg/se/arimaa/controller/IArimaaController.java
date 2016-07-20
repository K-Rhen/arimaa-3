package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.IObservable;
import de.htwg.se.arimaa.util.position.Position;



public interface IArimaaController extends IObservable {
	IPlayer getPlayer1();
	IPlayer getPlayer2();
	boolean moveFigureByString(int i, String line);
	boolean moveFigureByPosition(int actualPlayer, Position from, Position to);
	boolean pushFigurs(int player1, int player2, String line);
	void ShowPitch();
	GameStatus getGameStatus();
	void arimaaExit();
	void changePlayer();
	int getMoveCounter();
	int getActualPlayer();
	int getNextPlayer();
}
