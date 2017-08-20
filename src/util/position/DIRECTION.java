package util.position;

public enum DIRECTION {
    NORD, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORD:
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
