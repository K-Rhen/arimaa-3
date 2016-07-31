package de.htwg.se.arimaa.model;

import de.htwg.se.arimaa.util.position.Position;

public interface IFigure {

	/**
	 * set the position of the figure
	 * 
	 * @param newpos
	 *            position
	 */
	void setPosition(Position pos);

	/**
	 * get the current position of the figure
	 * 
	 * @return position
	 */
	Position getPosition();

	/**
	 * get the name of the figure
	 * 
	 * @return name
	 */
	FIGURE_NAME getName();

}
