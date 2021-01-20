package boardgame.customfunctions;

import boardgame.elements.GameState;

public interface TimeCalculator<GS extends GameState> {

    /**
     * Calculates time after which MCTS agent should make its decision
     * @param gameState we can base our decision on game state - no need to think a lot in some cases
     * @return number of milliseconds in which agent can 'think' freely
     */
    Integer getTimeToThink(GS gameState);

}
