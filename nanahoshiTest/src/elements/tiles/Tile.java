package elements.tiles;

import elements.Coordinates;
import elements.directions.Direction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static elements.tiles.ShipState.*;

public class Tile implements Serializable {

    public Ship ship;
    public ShipActivity activity;

    Tile(Coordinates position){
        activity = new ShipActivity(position);
        ship = Ship.UNKNOWN;
    }

    public Tile(Tile tile){
        this.ship = tile.ship;
        this.activity = new ShipActivity(tile.activity);
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public ShipActivity getActivity() {
        return activity;
    }

    public Coordinates getPosition(){
        return activity.getLastSeen();
    }

    public boolean isOnBoard(){ return activity.isOnBoard(); }

    public boolean isVisible(){ return activity.isVisible(); }

    public boolean isHidden(){
        return activity.isHidden();
    }

    public boolean isReady() { return activity.isReady(); }

    public void sink() { activity.state = SUNK; }

    public void setCourse(Direction direction){ activity.heading = direction; }

    public List<Coordinates> getMoveOptions(){
        int [] relativeMoveOptions = ship.shipClass.getMoveOptions();
        List<Coordinates> potentialDestinations = new ArrayList<>();
        for(int rotation : relativeMoveOptions){
            Direction direction = getActivity().heading.rotate(rotation);
            Optional<Coordinates> newPosition = direction.applyOn(getPosition());
            newPosition.ifPresent(potentialDestinations::add);
        }
        return potentialDestinations;
    }

    @Override
    public String toString() {
        return ship.toString() +" "+activity.toString();
    }
}
