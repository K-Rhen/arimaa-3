package de.htwg.se.arimaa.util.character;

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

}
