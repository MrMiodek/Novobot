package elements.actions;

import elements.Alliance;
import elements.Board;
import elements.Coordinates;
import elements.Field;
import elements.gamestate.NanahoshiState;
import elements.tiles.Tile;

import java.util.LinkedList;
import java.util.List;

public class MoveTile implements ActionType {

    private Coordinates initialPosition;
    private Alliance active;

    public MoveTile(Coordinates position, Alliance active) {
        this.initialPosition = position;
        this.active = active;
    }

    @Override
    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
        LinkedList<NanahoshiAction> actionList = new LinkedList<>();
        Board board = gameState.getBoard();
        Field field = board.getField(initialPosition);
        Tile tile = field.getFaceUp();
        for (Coordinates targetPosition : tile.getMoveOptions()) {
            Field targetField = board.getField(targetPosition);
            Tile targetTile = targetField.getFaceUp();
            if (targetTile == null || !targetTile.ship.getAlliance().equals(active)) {
                actionList.add(new MoveTileAction(initialPosition, targetPosition));
            }
        }
        return actionList;
    }

    public NanahoshiAction getAction(Coordinates targetPosition){
        return new MoveTileAction(initialPosition, targetPosition);
    }


    public class MoveTileAction extends NanahoshiAction {

        Coordinates targetPosition;

        MoveTileAction(Coordinates actionCoordinates, Coordinates targetPosition) {
            super(actionCoordinates);
            this.targetPosition = targetPosition;
        }

        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            Field startingField = gameState.getBoard().getField(initialPosition);
            Field targetField = gameState.getBoard().getField(targetPosition);
            Tile capturedTile = targetField.getFaceUp();
            Tile movingTile = startingField.getFaceUp();
            targetField.setFaceUp(movingTile);
            startingField.setFaceUp(null);
            movingTile.activity.lastSeen = targetPosition;
            if (capturedTile != null) {
                capture(gameState, capturedTile);
            }
            gameState.swapActive();
            return gameState;
        }

        private void capture(NanahoshiState gameState, Tile tile){
            tile.sink();
            gameState.getActorState().get(active).capture(tile);
        }

        @Override
        public Alliance getActor() {
            return active;
        }

        @Override
        public String toString() {
            return "MOVE " + initialPosition + " TO "+ targetPosition;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return "MOVE " + gameState.getBoard().getField(initialPosition).getFaceUp() + " TO "+ targetPosition;
        }
    }

}
