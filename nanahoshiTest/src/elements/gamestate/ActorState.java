package elements.gamestate;

import elements.tiles.Ship;
import elements.tiles.Tile;

import java.util.List;

public class ActorState {


    private int smallPoints;

    public ActorState(){
        this.smallPoints = 0;
    }

    public ActorState(ActorState state){
        this.smallPoints = state.smallPoints;
    }

    public int getSmallPoints() {
        return smallPoints;
    }

    public void capture(Tile tile) {
        smallPoints += tile.ship.getShipClass().getValue();
    }

}
