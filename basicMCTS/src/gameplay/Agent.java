package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameState;

/**
 *  Interface describing person / program that makes decision during the game
 */
public interface Agent<T extends GameState, A extends Action> {

    /**
     * Function that makes decision on what action to make based on current state of the game
     */
    A chooseActionToPlay(T gameState);

    //TODO: add agent id/name

}
