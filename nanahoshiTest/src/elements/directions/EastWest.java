package elements.directions;

public enum EastWest implements Axis{

    EAST(1), EW_NEUTRAL(0), WEST(-1);

    int x;

    EastWest(int x){
        this.x = x;
    }


    @Override
    public boolean isNeutral() {
        return equals(EW_NEUTRAL);
    }

    @Override
    public Axis Rotate(Rotation rotation) {
        return switch (rotation) {
            case LEFT -> NorthSouth.fromValue(x);
            case RIGHT -> NorthSouth.fromValue(-x);
        };
    }

    public static EastWest fromValue(int value) {
        for(EastWest axis : EastWest.values()){
            if(axis.x == value)
                return axis;
        }
        return null;
    }

}
