package de.htwg.se.arimaa.util.position;

import java.util.ArrayList;
import java.util.List;

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		setPositon(x, y);
	}

	public void setPositon(int x, int y) {
		if (!positionOnPitchXY(x, y))
			throw new IllegalArgumentException("position not on pitch");

		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private static boolean positionOnPitchXY(int x, int y) {
		if (x < 8 && y < 8 && x >= 0 && y >= 0)
			return true;

		return false;
	}

	public static boolean positionOnPitch(Position pos) {
		return positionOnPitchXY(pos.x, pos.y);
	}

	@Override
	public boolean equals(Object obj) {
		Position po = null;
		if (obj instanceof Position)
			po = (Position) obj;

		if (po == null) {
			return false;
		} else {
			return po.x == x && po.y == y;
		}
	}

	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getX());
		sb.append(this.getY());
		return sb.toString().hashCode();
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static List<Position> getSurroundPositionForPitch(Position pos) {
		List<Position> surroundPosition = new ArrayList<>();

		// up
		if (pos.getY() > 0)
			surroundPosition.add(new Position(pos.getX(), pos.getY() - 1));
		// down
		if (pos.getY() < 7)
			surroundPosition.add(new Position(pos.getX(), pos.getY() + 1));
		// left
		if (pos.getX() > 0)
			surroundPosition.add(new Position(pos.getX() - 1, pos.getY()));
		// right
		if (pos.getX() < 7)
			surroundPosition.add(new Position(pos.getX() + 1, pos.getY()));

		return surroundPosition;
	}
	

	public static String getDirection(Position from, Position to) {
		int dx = to.getX() - from.getX();
		int dy = to.getY() - from.getY();

		if (dx == 0 && dy == -1)
			return "n";
		else if (dx == 1 && dy == 0)
			return "e";
		else if (dx == 0 && dy == 1)
			return "s";
		else if (dx == -1 && dy == 0)
			return "w";

		return null;
	}

}
