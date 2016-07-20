package de.htwg.se.arimaa.model.impl;


import de.htwg.se.arimaa.model.IFIGURE_NAME;
import de.htwg.se.arimaa.model.IFigure;
import de.htwg.se.arimaa.util.character.Position;

public class Figure implements IFigure{
	static enum NAME {
		R, C, D, H, L, E, r, c, d, h, l, e
	}
	
	private Position position;
	private FIGURE_NAME name;

	public Figure(Position positon, FIGURE_NAME name) {
		this.position = positon;
		this.name = name;
	}

	public void setPosition(int x, int y) {
		setPosition(new Position(x, y));
	}

	public void setPosition(Position newpos) {
		position = newpos;
	}

	public Position getPosition() {
		return position;
	}

	public FIGURE_NAME getName() {
		return name;
	}
}
