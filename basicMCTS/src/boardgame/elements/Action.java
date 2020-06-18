package boardgame.elements;

/**
 * Action interface describing single unit of action.
 * Some real-game actions can described as a chain of multiple single unit actions
 */
public interface Action <GS extends GameState, GA extends GameActor>{

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
