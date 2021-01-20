package elements.directions;

public enum NorthSouth implements Axis{

    NORTH(1), NS_NEUTRAL(0), SOUTH(-1);

    int y;

    NorthSouth(int y){
        this.y = y;
    }

    @Override
    public boolean isNeutral() {
        return equals(NS_NEUTRAL);
    }

    @Override
    public Axis Rotate(Rotation rotation) {
        return switch (rotation) {
            case LEFT -> EastWest.fromValue(-y);
            case RIGHT -> EastWest.fromValue(y);
        };
    }

    public static NorthSouth fromValue(int value) {
        for(NorthSouth axis : NorthSouth.values()){
            if(axis.y == value)
                return axis;
        }
        return null;
    }
}
