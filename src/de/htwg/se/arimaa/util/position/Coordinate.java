package de.htwg.se.arimaa.util.position;

public final class Coordinate {

	public static Position convert(String input) {
		if (!input.matches("[a-h][1-8]"))
			throw new IllegalArgumentException(input + " wrong coordinate formate");

		char[] parts = input.toCharArray();

		int xPos = readCharX(parts[0]);
		int yPos = readCharY(parts[1]);
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
		return (char) (i + 97);
	}

	private static char readPosY(int i) {
		return (char) (56 - i);
	}

	private static int readCharX(char c) {
		return c - 97;
	}

	private static int readCharY(char c) {
		return 56 - c;
	}

}
