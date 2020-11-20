package boardgame.elements;

/**
 * Interface that describes game configuration object.
 */
public interface GameConfig <GS extends GameState>{

    /**
     * Function creating initial state of the game based on this object configuration
     * @return initial state of the game
     */
    GS getInitialGameState();

}