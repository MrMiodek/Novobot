package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameConfig;
import boardgame.elements.GameState;
import montecarlo.MonteCarloTreeSearch;

import java.util.ArrayList;
import java.util.Map;


/**
 * Simple class for play-testing selfplay of mcts-actors configurations.
 * This class can be extended and / or its methods can be overwritten.
 * in order to (for example) set enable more agent settings.
 */
public class PlayOutTester
        <GS extends GameState<GS, GA, AC>,
        AC extends Action<GS,GA>,
        GA extends GameActor>{

    GameRunner<GS, AC, GA > runner;

    GameplayLogger<GS, AC, GA > logger = new GameplayLogger<GS, AC, GA >();
    

    public PlayOutTester(GameConfig gameConfig, MonteCarloTreeSearch agent) throws RunnerException {
        int n = gameConfig.getListOfActors().size();
        ArrayList<Agent<GS,AC>> agents = new ArrayList();
        for(int i =0; i < n; i++){
            agents.add(agent);
        }
        this.runner = new GameRunner<GS, AC, GA>(gameConfig, agents);
    }

    public PlayOutTester(GameConfig gameConfig, MonteCarloTreeSearch agent, GameplayLogger logger) throws RunnerException {
        int n = gameConfig.getListOfActors().size();
        ArrayList<Agent<GS,AC>> agents = new ArrayList();
        for(int i =0; i < n; i++){
            agents.add(agent);
        }
        this.runner = new GameRunner<GS, AC, GA>(gameConfig, agents, logger);
    }

    /**
     * This function does create single game and run it to the end.
     */
    public void playout(){
        runner.playout();
    }

}
