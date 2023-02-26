package boardgame.elements;

import java.util.List;

/**
 * Interface that describes game configuration object.
 */
public interface GameConfig <GS extends GameState, GA extends GameActor>{

    /**
     * Function creating initial state of the game based on this object configuration
     * @return initial state of the game
     */
    GS getInitialGameState();

    /**
     * Function returning list of in-game actors
     * @return list of in-game actors
     */
    List<GA> getListOfActors();

}