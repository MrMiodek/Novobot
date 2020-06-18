package boardgame.elements;

public interface GameConfig <GS extends GameState>{

    /**
     * Function creating initial state of the game based on this object configuration
     * @return inittial state of the game
     */
    GS getInitialGameState();


}
