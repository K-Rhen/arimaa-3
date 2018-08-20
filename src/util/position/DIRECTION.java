package util.position;

public enum DIRECTION {
    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return "n";
            case EAST:
                return "e";
            case SOUTH:
                return "s";
            case WEST:
                return "w";
            default:
                throw new IllegalArgumentException();
        }
    }
}
