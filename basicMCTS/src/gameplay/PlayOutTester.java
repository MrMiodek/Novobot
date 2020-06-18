package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameConfig;
import boardgame.elements.GameState;
import monte_carlo.MCTSBuilder;
import monte_carlo.MCTSConfig;
import monte_carlo.MonteCarloTreeSearch;

import java.util.Map;

public class PlayOutTester {

    MonteCarloTreeSearch agent;
    GameConfig gameConfig;

    public PlayOutTester(GameConfig gameConfig, MonteCarloTreeSearch agent){
        this.gameConfig = gameConfig;
        this.agent = agent;
    }

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
