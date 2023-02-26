package boardgame.elements;

/**
 * Action interface describing single unit of action.
 * Some real-game gameplay.actions can described as a chain of multiple single unit gameplay.actions
 */
public interface Action <GS extends GameState, GA extends GameActor>{

    //TODO: add system actions for randomness etc.

    /**
     * Applies action on given GameState object
     * @param gameState GameState object which should be modified by applying action
     * @return modified GameState object
     */
    GS apply(GS gameState);

    /**
     * Function describing who is the action actor
     * @return acting side
     */
    GA getActor();

}
