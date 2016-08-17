package de.htwg.se.arimaa.util.position;

public enum DIRECTION {
	NORD, EAST, SOUTH, WEST;

	@Override
	public String toString() {
		return this.toString().substring(0, 1).toLowerCase();
	}
}
