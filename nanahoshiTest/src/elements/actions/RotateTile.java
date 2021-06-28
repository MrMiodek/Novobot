package elements.actions;


import elements.Alliance;
import elements.Coordinates;
import elements.Field;
import elements.directions.Direction;
import elements.directions.Rotation;
import elements.gamestate.NanahoshiState;
import elements.tiles.Tile;

import java.util.LinkedList;
import java.util.List;

public class RotateTile implements ActionType {

    private Coordinates coordinates;
    private Alliance active;

    public RotateTile(Coordinates position, Alliance active) {
        this.active = active;
        this.coordinates = position;
    }

    @Override
    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
        List<NanahoshiAction> actionList = new LinkedList<>();
        Direction initialDirection = gameState.getBoard().getField(coordinates).getFaceUp().getActivity().heading;
        for (Rotation rotation : Rotation.values()) {
            Direction targetDirection = initialDirection.rotate(rotation);
            actionList.add(new RotateTileAction(coordinates, targetDirection));
        }
        return actionList;
    }

    public NanahoshiAction getAction(Direction direction){
        return new RotateTileAction(coordinates, direction);
    }



    public class RotateTileAction extends NanahoshiAction {

        Direction finalDirection;

        RotateTileAction(Coordinates actionCoordinates, Direction finalDirection) {
            super(actionCoordinates);
            this.finalDirection = finalDirection;
        }


        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            Field field = gameState.getBoard().getField(getActionCoordinates());
            Tile tile = field.getFaceUp();
            tile.setCourse(finalDirection);
            gameState.swapActive();
            return gameState;
        }

        @Override
        public Alliance getActor() {
            return active;
        }

        @Override
        public String toString() {
            return coordinates + " FACE "+ finalDirection;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return gameState.getBoard().getField(coordinates).getFaceUp() + " FACE "+ finalDirection;
        }

    }

}
