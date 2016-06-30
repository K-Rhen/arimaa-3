package de.htwg.se.arimaa.controller;

import de.htwg.se.arimaa.model.IPlayer;
import de.htwg.se.arimaa.util.observer.IObservable;
import de.htwg.se.arimaa.view.TextUI;


public interface IArimaaController extends IObservable {

	IPlayer getPlayer1();
	IPlayer getPlayer2();
	void moveFigureByString(int i, String next);
	boolean pushFigurs(int i, String next);
	void ShowPitch();
	GameStatus getGameStatus();


	
}
