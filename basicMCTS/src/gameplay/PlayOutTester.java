package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameConfig;
import boardgame.elements.GameState;
import monte_carlo.MonteCarloTreeSearch;

import java.util.Map;


/**
 * Simple class for play-testing different games / mcts-actors configurations
 * This class can be extended and / or its methods can be overwritten
 * in order to (for example) set different logs and enable more agent settings
 */
public class PlayOutTester {

    MonteCarloTreeSearch agent;
    GameConfig gameConfig;

    public PlayOutTester(GameConfig gameConfig, MonteCarloTreeSearch agent){
        this.gameConfig = gameConfig;
        this.agent = agent;
    }

    /**
     * This function does create single game and run it to the end.
     * For clarity it probably should have any noticeable side effects between runs.
     * Self-improving agents should modify copy of themselves (?)
     */
    public void playout(){
        GameState gameState = gameConfig.getInitialGameState();
        logBeforeGame(gameState);
        while(gameState.getEndScore() == null){
            Action action = agent.chooseActionToPlay(gameState);
            logBeforeMove(gameState,action);
            action.apply(gameState);
            logAfterMove(gameState, action);
        }
        logAfterGame(gameState);
    }

    public void logBeforeGame(GameState gameState){
        System.out.println(gameState);
    }

    public void logBeforeMove(GameState gameState, Action action){}

    public void logAfterMove(GameState gameState, Action action){
        System.out.println(gameState);
    }

    public void logAfterGame(GameState gameState){
        Map<GameActor, Integer> endScore = gameState.getEndScore();
        for(Map.Entry<GameActor, Integer>  entry : endScore.entrySet()){
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
    }

}
