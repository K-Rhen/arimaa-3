package model.impl;

import model.FIGURE_NAME;
import model.IFigure;
import util.position.Position;

public class Figure implements IFigure {
	private Position position;
	private final FIGURE_NAME name;
	private boolean disable;

    public Figure(Position position, FIGURE_NAME name) {
        this.position = position;
		this.name = name;
		this.disable = false;
	}

	@Override
	public void setPosition(Position pos) {
		position = pos;
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public FIGURE_NAME getName() {
		return name;
	}

	@Override
	public void setDisable(boolean state) {
		disable = state;
	}

	@Override
    public boolean isDisabled() {
		return disable;
	}

	@Override
	public String toString() {
		return "{" + name + " " + position.toString() + "}";
	}

}
