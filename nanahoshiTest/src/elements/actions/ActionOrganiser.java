package elements.actions;

import elements.Alliance;
import elements.gamestate.NanahoshiState;
import elements.tiles.Tile;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ActionOrganiser {

    public static List<NanahoshiAction> getAllPossibleActions(NanahoshiState gameState, Alliance active) {
        LinkedList<NanahoshiAction> possibleActions = new LinkedList<>();
        for (Iterator<Tile> it = gameState.getOnBoardTiles(); it.hasNext(); ) {
            Tile tile = it.next();
            possibleActions.addAll(getPossibleTileAction(gameState, active, tile));
        }
        return possibleActions;
    }

    private static List<NanahoshiAction> getPossibleTileAction(NanahoshiState gameState, Alliance active, Tile tile){
        if(tile.isHidden()) {
            return new PickUpTile(tile.getPosition()).getPossibleActions(gameState);
        } else {
            if (tile.ship.getAlliance().equals(active)) {
                List<NanahoshiAction> possibleActions = new MoveTile(tile.getPosition(), active).getPossibleActions(gameState);
                possibleActions.addAll(new RotateTile(tile.getPosition(), active).getPossibleActions(gameState));
                return possibleActions;
            }
        }
        return new LinkedList<>();
    }

}
