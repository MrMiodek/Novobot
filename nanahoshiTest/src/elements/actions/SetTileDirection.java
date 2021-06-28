package elements.actions;

import elements.Alliance;
import elements.Coordinates;
import elements.directions.Direction;
import elements.gamestate.NanahoshiState;
import elements.tiles.ShipState;
import elements.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class SetTileDirection implements ActionType {

    private Coordinates position;
    private Alliance active;

    public SetTileDirection(Coordinates position, Alliance active) {
        this.active = active;
        this.position = position;
    }

    @Override
    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
        List<NanahoshiAction> actionList = new ArrayList<>();
        active = gameState.getCurrentActor();
        for (Direction direction : Direction.values())
            if (direction.isSimple())
                actionList.add(new SetTileDirectionAction(position, direction));
        return actionList;
    }

    public NanahoshiAction getAction(Direction direction){
        return new SetTileDirectionAction(position, direction);
    }

    public class SetTileDirectionAction extends NanahoshiAction {

        Direction direction;

        SetTileDirectionAction(Coordinates position, Direction direction) {
            super(position);
            this.direction = direction;
        }

        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            Tile tile = gameState.getBoard().getField(position).getFaceUp();
            tile.activity.heading = direction;
            tile.activity.state = ShipState.READY;
            gameState.swapActive();
            return gameState;
        }

        @Override
        public Alliance getActor() {
            return active;
        }

        @Override
        public String toString() {
            return position + " SET DIRECTION TO "+ direction;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return gameState.getBoard().getField(position).getFaceUp() + " SET DIRECTION TO "+ direction;
        }
    }

}
