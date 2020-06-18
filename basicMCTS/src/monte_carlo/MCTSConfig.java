package monte_carlo;

import boardgame.custom_functions.*;
import boardgame.elements.Action;

import java.util.List;
import java.util.Random;

public class MCTSConfig {

    private Random r = new Random();

    TimeCalculator timeCalculator = any -> 100;

    ChildFinder bestChildFinder = new BasicUCT();

    ScoreUpdater scoreUpdater = (node, endScore) ->{
        if(node.getParent() != null)
            node.addScore((Integer) endScore.get(node.getPreviousActor()));
    };

    ActionChooser actionChooser = gameState -> {
        List<Action> actions = gameState.getAllPossibleActions();
        return actions.get(r.nextInt(actions.size()));
    };

}
