package model;

import util.position.Position;

public interface IFigure {

	/**
	 * set the position of the figure
     *
     * @param pos
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

	/**
	 * set disable
     *
     * @param state new state
     */
	void setDisable(boolean state);

	/**
	 * is disable
     *
     * @return if disable
     */
    boolean isDisabled();

}
