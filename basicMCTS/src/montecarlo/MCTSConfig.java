package montecarlo;

import boardgame.customfunctions.*;
import boardgame.elements.Action;

import java.util.List;
import java.util.Random;

/**
 * Class storing basic implementations of game dependent functions used in Monte-Carlo Tree Search
 * Object of this class (with possibly changed implementations)
 * can be used in MCTSBuilder to create game dependent MonteCarloTreeSearch object.
 */
public class MCTSConfig {

    private Random r = new Random();

    /**
     * Function calculating time-to-think in milliseconds based on state of the game
     * @see TimeCalculator
     * Default value:
     * 100ms
     */
    public TimeCalculator timeCalculator = any -> 100;

    /**
     * Function that chooses child node in MCTS tree from which we should start next simulation
     * @see ChildFinder
     * Default value:
     * @see BasicUCT
     */
    public ChildFinder bestChildFinder = new BasicUCT();

    /**
     * Function that updates MCTS node score after getting simulation result (endScore)
     * @see ScoreUpdater
     * Default value:
     * adding endscore to current node value
     */
    public ScoreUpdater scoreUpdater = (node, endScore) ->{
        if(node.getParent() != null)
            node.addScore( (Integer) endScore.get(node.getPreviousActor()));
    };

    /**
     * Function that chooses action to take during simulations
     * @see ActionChooser
     * Default value:
     * random legal action
     */
    public ActionChooser simulatedActionChooser = gameState -> {
        List<Action> actions = gameState.getAllPossibleActions();
        return actions.get(r.nextInt(actions.size()));
    };

}
