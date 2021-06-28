package elements.actions;

import elements.Alliance;
import elements.Coordinates;
import elements.Field;
import elements.gamestate.NanahoshiState;
import elements.tiles.ShipState;
import elements.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class PickUpTile implements ActionType {

    private Coordinates position;
    private Alliance active;

    public PickUpTile(Coordinates position) {
        this.position = position;
    }

    @Override
    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
        List<NanahoshiAction> actionList = new ArrayList<>();
        active = gameState.getCurrentActor();
        Field field = gameState.getBoard().getField(position);
        if (field.getFaceUp() == null) {
            actionList.add(new PickUpTileAction(position));
        }
        return actionList;
    }

    public NanahoshiAction getAction(){
        return new PickUpTileAction(position);
    }

    public class PickUpTileAction extends NanahoshiAction {


        PickUpTileAction(Coordinates actionCoordinates) {
            super(actionCoordinates);
        }

        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            Field field = gameState.getBoard().getField(position);
            Tile tile = field.getFaceDown();
            field.setFaceUp(tile);
            field.setFaceDown(null);
            tile.activity.state = ShipState.APPEARING;
            return gameState;
        }

        @Override
        public Alliance getActor() {
            return active;
        }

        @Override
        public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
            RevealTile reveal = new RevealTile(position, active);
            return reveal.getPossibleActions(gameState);
        }

        @Override
        public String toString() {
            return "PICK UP TILE FROM "+position;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return "PICK UP TILE FROM "+position;
        }

    }

}
