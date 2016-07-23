package de.htwg.se.arimaa.model;

import java.util.List;

public interface IPitch {
	/**
	 * return the gold player
	 * 
	 * @return gold player
	 */
	IPlayer getGoldPlayer();

	/**
	 * return the silver player
	 * 
	 * @return silver player
	 */
	IPlayer getSilverPlayer();

	/**
	 * set the whole figure of gold player
	 * 
	 * @param figures
	 *            list
	 */
	void setGoldPlayerFigures(List<IFigure> figures);

	/**
	 * set the whole figure of silver player
	 * 
	 * @param figures
	 *            list
	 */
	void setSilverPlayerFigures(List<IFigure> figures);

	/**
	 * Get all figures form both players
	 * @return all figures on the Pitch
	 */
	List<IFigure> getAllFiguresOnPitch();
}
