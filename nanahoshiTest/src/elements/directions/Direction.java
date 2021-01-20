package elements.directions;

import elements.board.Coordinates;

import java.util.Optional;

import static elements.directions.EastWest.*;
import static elements.directions.NorthSouth.*;

public enum Direction {

    IMMOBILE(NS_NEUTRAL, EW_NEUTRAL, -1),
    N(NORTH, EW_NEUTRAL,0),
    NE(NORTH, EAST, 1),
    E(NS_NEUTRAL, EAST,2),
    SE(SOUTH, EAST,3),
    S(SOUTH, EW_NEUTRAL,4),
    SW(SOUTH, WEST, 5),
    W(NS_NEUTRAL, WEST,6 ),
    NW(NORTH, WEST, 7);

    public final NorthSouth northSouth;
    public final EastWest eastWest;
    private final int idx;

    Direction(NorthSouth northSouth, EastWest eastWest, int idx){
        this.northSouth = northSouth;
        this.eastWest = eastWest;
        this.idx = idx;
    }

    public boolean isSimple(){
        return northSouth.isNeutral() ^ eastWest.isNeutral();
    }

    public Direction rotate(Rotation rotation){
        if(equals(IMMOBILE))
            return IMMOBILE;
        else
            return get((8+ this.idx + rotation.idxChange) % 8);
    }

    public Direction rotate(int rotation){
        if(equals(IMMOBILE))
            return IMMOBILE;
        else
            return get((8+ this.idx + rotation) % 8);
    }

    public static Direction get(NorthSouth yAxis, EastWest xAxis){
        for(Direction dir : values()){
            if(dir.northSouth == yAxis && dir.eastWest ==xAxis)
                return dir;
        }
        return IMMOBILE;
    }

    private Direction get(int idx){
        for(Direction dir : values()){
            if(dir.idx == idx)
                return dir;
        }
        return IMMOBILE;
    }

    public Optional<Coordinates> applyOn(Coordinates init){
        int _x = init.x + eastWest.x;
        int _y = init.y + northSouth.y;
        Coordinates newPosition = new Coordinates(_x,_y);
        if(newPosition.isLegal()){
            return Optional.of(newPosition);
        }else{
            return Optional.empty();
        }
    }

    public static void main(String[] args){
        System.out.println(N.rotate(Rotation.LEFT));
    }

}
