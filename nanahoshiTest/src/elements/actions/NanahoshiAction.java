package elements.actions;

import boardgame.elements.Action;
import elements.Alliance;
import elements.board.Coordinates;
import elements.gamestate.NanahoshiState;

import java.util.List;

public abstract class NanahoshiAction implements Action<NanahoshiState, Alliance> {

    NanahoshiAction(Coordinates actionCoordinates){
        this.actionCoordinates = actionCoordinates;
    }

    public Coordinates actionCoordinates;

    public Coordinates getActionCoordinates(){
        return actionCoordinates;
    }

    public List<NanahoshiAction> getPossibleActions(NanahoshiState gameState){
        return ActionOrganiser.getAllPossibleActions(gameState, gameState.getCurrentActor());
    }

    @Override
    public NanahoshiState apply(NanahoshiState gameState) {
        NanahoshiState updated = applyBoardChanges(gameState);
        updated.setLastAction(this);
        return updated;
    }

    public abstract NanahoshiState applyBoardChanges(NanahoshiState gameState);

    public abstract String toStringBefore(NanahoshiState gameState);

    public static NanahoshiAction GAME_START = new NanahoshiAction(null) {

        private String ACTION_STRING = "GAME_START";

        @Override
        public NanahoshiState applyBoardChanges(NanahoshiState gameState) {
            return gameState;
        }

        @Override
        public Alliance getActor() {
            return null;
        }

        @Override
        public String toString() {
            return ACTION_STRING;
        }

        @Override
        public String toStringBefore(NanahoshiState gameState) {
            return ACTION_STRING;
        }
    };

}
