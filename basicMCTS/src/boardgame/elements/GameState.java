package boardgame.elements;

import java.util.List;
import java.util.Map;

/**
 * Interface describing state of the game as minimal amount of information
 * needed to recreate single moment of the game.
 */
public interface GameState <GS extends  GameState, GA extends  GameActor, A extends Action<GS,GA>>{

    /**
     * Function gives actor who now has a chance to move
     * @return current actor
     */
    GA getCurrentActor();

    /**
     * During MCTS we often need to modify current state of the game
     * without breaking original object - hence cloning
     * @return clone of current GameState
     */
    GS copy();

    /**
     * Function checking if game has already concluded.
     * @return maps players to their endgame points or null if game is still in progress
     */
    Map<GA,Integer> getEndScore();


    /**
     * Function calculating which actions are possible to take given current state of the game
     * @return list of all currently possible action
     */
    List<A> getAllPossibleActions();
}
