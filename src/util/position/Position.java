package util.position;

import java.util.ArrayList;
import java.util.List;

public final class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        setPosition(x, y);
    }

    public static boolean validXY(int x, int y) {
        return x < 8 && y < 8 && x >= 0 && y >= 0;

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

    public static DIRECTION getDirection(Position from, Position to) {
        int dx = to.getX() - from.getX();
        int dy = to.getY() - from.getY();

        if (dx == 0 && dy == -1)
            return DIRECTION.NORTH;
        else if (dx == 1 && dy == 0)
            return DIRECTION.EAST;
        else if (dx == 0 && dy == 1)
            return DIRECTION.SOUTH;
        else if (dx == -1 && dy == 0)
            return DIRECTION.WEST;

        return null;
    }

    public void setPosition(int x, int y) {
        if (!validXY(x, y))
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj == this)
            return true;

        if (obj instanceof Position) {
            Position position = (Position) obj;
            return position.x == x && position.y == y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
