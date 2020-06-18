package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameState;

public interface Agent<T extends GameState, A extends Action> {

    A chooseActionToPlay(T gameState);

}
