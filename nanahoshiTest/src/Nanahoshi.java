import elements.Alliance;
import elements.NanahoshiConfig;
import elements.actions.NanahoshiAction;
import elements.gamestate.NanahoshiState;
import gameplay.*;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

import java.util.ArrayList;

public class Nanahoshi {

    public static void main(String [] args) throws RunnerException {
        NanahoshiConfig nanahoshiConfig = new NanahoshiConfig();
        GameplayLogger logger = getGameplayLogger();
        ArrayList<Agent<NanahoshiState, NanahoshiAction>> agents = getPvCAgents();
        GameRunner<NanahoshiState, NanahoshiAction, Alliance> nanahoshiGame = new GameRunner<NanahoshiState, NanahoshiAction, Alliance>(nanahoshiConfig , agents, logger);
        nanahoshiGame.playout();
    }

    public static ArrayList<Agent<NanahoshiState, NanahoshiAction>> getPvPAgents(){
        ArrayList<Agent<NanahoshiState, NanahoshiAction>> agents = new ArrayList<>();
        agents.add(getHumanPlayer());
        agents.add(getHumanPlayer());
        return agents;
    }

    public static ArrayList<Agent<NanahoshiState, NanahoshiAction>> getPvCAgents(){
        ArrayList<Agent<NanahoshiState, NanahoshiAction>> agents = new ArrayList<>();
        agents.add(getHumanPlayer());
        agents.add(getMCTSPlayer());
        return agents;
    }

    public static BasicHumanAgent getHumanPlayer(){
        return new BasicHumanAgent<NanahoshiState, NanahoshiAction>();
    }

    public static MonteCarloTreeSearch getMCTSPlayer(){
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        return mcts;
    }

    public static GameplayLogger getGameplayLogger(){
        return new GameplayLogger<NanahoshiState, NanahoshiAction, Alliance>(){
            @Override
            public void logAfterMove(NanahoshiState gameState, NanahoshiAction action) { }

            @Override
            public void logBeforeMove(NanahoshiState gameState, NanahoshiAction action) {
                System.out.println("("+(gameState.history.size()-1)+") " + gameState.getCurrentActor() + " - " + action.toStringBefore(gameState));
            }
        };
    }

}
