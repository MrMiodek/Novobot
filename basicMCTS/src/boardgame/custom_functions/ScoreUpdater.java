package boardgame.custom_functions;

import boardgame.elements.GameActor;
import tree.Node;

import java.util.Map;

public interface ScoreUpdater<GA extends GameActor> {

    /**
     * Updating node score can be different for different games and MCTS types.
     * @param node node to update
     * @param endScore finishing score of simulation
     */
    void updateNodeScore(Node node, Map<GA, Integer> endScore);

}
