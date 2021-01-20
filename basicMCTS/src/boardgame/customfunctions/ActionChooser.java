package boardgame.customfunctions;

import boardgame.elements.Action;
import boardgame.elements.GameState;

public interface ActionChooser<A extends Action, GS extends GameState> {

    /**
     * Function describing politic by which agent will choose action to play
     * @param gameState State of the game for which we want to choose the action
     * @return chosen action
     */
    A chooseAction(GS gameState);

}
