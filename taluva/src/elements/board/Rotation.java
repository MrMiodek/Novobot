package elements.board;

public enum Rotation {

    LEFT, RIGHT;

    public Position rotateOver(Position rotate, Position over){
        Position vector = over.getVectorTo(rotate);
        return over.move(rotateVector(vector));
    }

    public Position rotateVector(Position vector){
        return switch (this){
            case LEFT ->
                    new Position(-vector.y, -vector.z);
            case RIGHT ->
                    new Position(-vector.z, -vector.x);
        };
    }

}
