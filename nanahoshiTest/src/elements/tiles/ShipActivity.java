package elements.tiles;

import elements.board.Coordinates;
import elements.directions.Direction;

import static elements.directions.Direction.*;
import static elements.tiles.ShipState.*;

public class ShipActivity {

    public ShipState state;
    public Direction heading;
    public Coordinates lastSeen;

    public ShipActivity(Coordinates position) {
        this(HIDDEN, IMMOBILE, position);
    }

    public ShipActivity(ShipState state, Direction heading, Coordinates position) {
        this.state = state;
        this.heading = heading;
        this.lastSeen = position;
    }

    public ShipActivity(ShipActivity shipActivity){
        this.state = shipActivity.state;
        this.heading = shipActivity.heading;
        this.lastSeen = shipActivity.lastSeen;
    }

    @Override
    public String toString() {
        return lastSeen.toString() + (!isHidden() ? " "+ state.toString() + " (" + heading.toString()+")":"");
    }

    public ShipState getState() {
        return state;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    public Direction getHeading() {
        return heading;
    }

    public void setHeading(Direction heading) {
        this.heading = heading;
    }

    public Coordinates getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Coordinates lastSeen) {
        this.lastSeen = lastSeen;
    }

    public boolean isOnBoard(){ return !state.equals(SUNK); }

    public boolean isVisible(){ return !state.equals(HIDDEN); }

    public boolean isHidden(){ return state.equals(HIDDEN); }

    public boolean isReady() { return state.equals(READY); }
}
