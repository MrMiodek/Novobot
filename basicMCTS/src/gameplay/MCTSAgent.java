package gameplay;

import boardgame.customfunctions.ActionChooser;
import boardgame.elements.Action;
import boardgame.elements.GameState;
import tree.Node;

public interface MCTSAgent<GS extends GameState,A extends Action, N extends Node> extends Agent<GS, A>{


    ActionChooser<A, GS> getDefaultActionChooser();

    A chooseActionAfterSimulations(N analysis);

    /**
     * Function that analyse current state of the game
     * @return game tree with current gamestate as a root
     */
    N analyzePossibleMoves(GS gameState);

}
