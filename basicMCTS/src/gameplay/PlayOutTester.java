package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameConfig;
import boardgame.elements.GameState;
import montecarlo.MonteCarloTreeSearch;

import java.util.Map;


/**
 * Simple class for play-testing different games / mcts-actors configurations
 * This class can be extended and / or its methods can be overwritten
 * in order to (for example) set different logs and enable more agent settings
 */
public class PlayOutTester<GS extends GameState, AC extends Action> {

    Agent<GS,AC> agent;
    GameConfig<GS> gameConfig;

    public PlayOutTester(GameConfig<GS> gameConfig, MonteCarloTreeSearch agent){
        this.gameConfig = gameConfig;
        this.agent = agent;
    }

    /**
     * This function does create single game and run it to the end.
     * For clarity it probably should have any noticeable side effects between runs.
     * Self-improving agents should modify copy of themselves (?)
     */
    public void playout(){
        GS gameState = gameConfig.getInitialGameState();
        logBeforeGame(gameState);
        while(gameState.getEndScore() == null){
            AC action = agent.chooseActionToPlay(gameState);
            logBeforeMove(gameState,action);
            action.apply(gameState);
            logAfterMove(gameState, action);
        }
        logAfterGame(gameState);
    }

    public void logBeforeGame(GS gameState){
        System.out.println(gameState);
    }

    public void logBeforeMove(GS gameState, AC action){}

    public void logAfterMove(GS gameState, AC action){
        System.out.println(gameState);
    }

    public void logAfterGame(GS gameState){
        Map<GameActor, Integer> endScore = gameState.getEndScore();
        for(Map.Entry<GameActor, Integer>  entry : endScore.entrySet()){
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
    }

}
