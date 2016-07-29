package de.htwg.se.arimaa.util.position;

public final class Coordinate {

	public static Position convert(String input) {
		if (!input.matches("[a-h][1-8]"))
			throw new IllegalArgumentException(input + " wrong coordinate formate");

		char[] parts = input.toCharArray();

		int xPos = readPosX(parts[0]);
		int yPos = readPosY(parts[1]);
		return new Position(xPos, yPos);
	}

	public static String convert(Position pos) {
		StringBuilder sb = new StringBuilder();

		char xPos = readPosX(pos.getX());
		sb.append(xPos);
		char yPos = readPosY(pos.getY());
		sb.append(yPos);

		return sb.toString();
	}

	private static char readPosX(int i) {
		if (i < 0 || i > 7)
			throw new IllegalArgumentException(i + " wrong coordinate");

		return (char) (i + 97);
	}

	private static char readPosY(int i) {
		if (i < 0 || i > 7)
			throw new IllegalArgumentException(i + " wrong coordinate");

		return (char) (56 - i);
	}

	private static int readPosX(char c) {
		if (c < 'a' || c > 'h')
			throw new IllegalArgumentException(c + " wrong coordinate");

		return c - 97;
	}

	private static int readPosY(char c) {
		if (c < '1' || c > '8')
			throw new IllegalArgumentException(c + " wrong coordinate");

		return  56 - c;
	}

}
