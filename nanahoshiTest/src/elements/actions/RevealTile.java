package elements.actions;

import elements.Alliance;
import elements.Coordinates;
import elements.Field;
import elements.gamestate.NanahoshiState;
import elements.tiles.Ship;
import elements.tiles.Tile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RevealTile implements ActionType {

    private Coordinates position;
    private Alliance active;

    RevealTile(Coordinates position, Alliance active) {
        this.active = active;
        this.position = position;
    }

    @Override
    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
        List<NanahoshiAction> actionList = new ArrayList<>();
        for (Iterator<Ship> it = gameState.getConcealedShips(); it.hasNext(); ) {
            Ship ship = it.next();
            actionList.add(new RevealTileAction(position, ship));
        }
        return actionList;
    }


    public class RevealTileAction extends NanahoshiAction {

        Ship ship;

        RevealTileAction(Coordinates actionCoordinates, Ship ship) {
            super(actionCoordinates);
            this.ship = ship;
        }

        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            Field field = gameState.getBoard().getField(position);
            Tile tile = field.getFaceUp();
            gameState.getTileSet().revealShip(tile, ship);
            return gameState;
        }

        @Override
        public Alliance getActor() {
            return active;
        }

        @Override
        public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState) {
            SetTileDirection setDirection = new SetTileDirection(position, active);
            return setDirection.getPossibleActions(gameState);
        }

        @Override
        public String toString() {
            return "REVEAL "+ ship + " AT "+ position;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return "REVEAL "+ ship + " AT "+ position;
        }

    }

}
