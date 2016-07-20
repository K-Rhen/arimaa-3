package de.htwg.se.arimaa.model;

import java.util.List;

public interface IPitch {
	/**
	 * return the instance of player1
	 * 
	 * @return player1
	 */
	IPlayer getPlayer1();

	/**
	 * return the instance of player2
	 * 
	 * @return player2
	 */
	IPlayer getPlayer2();

	/**
	 * set the whole figure of player1
	 * 
	 * @param figures
	 *            list
	 */
	void setPlayer1Figures(List<IFigure> figures);

	/**
	 * set the whole figure of player2
	 * 
	 * @param figures
	 *            list
	 */
	void setPlayer2Figures(List<IFigure> figures);

}
