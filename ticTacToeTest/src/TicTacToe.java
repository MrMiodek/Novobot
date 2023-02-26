import elements.Sign;
import elements.TicTacToeAction;
import elements.TicTacToeConfig;
import elements.TicTacToeState;
import gameplay.*;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {

    public static void main(String [] args) throws RunnerException {
        TicTacToeConfig tttConfig = new TicTacToeConfig();
        GameplayLogger logger = getGameplayLogger();
        ArrayList<Agent<TicTacToeState, TicTacToeAction>> agents = getPvPAgents();
        GameRunner<TicTacToeState,TicTacToeAction, Sign> tttGame = new GameRunner<TicTacToeState,TicTacToeAction, Sign>(tttConfig , agents, logger);
        tttGame.playout();
    }

    public static ArrayList<Agent<TicTacToeState, TicTacToeAction>> getPvPAgents(){
        ArrayList<Agent<TicTacToeState, TicTacToeAction>> agents = new ArrayList<>();
        agents.add(getHumanPlayer());
        agents.add(getHumanPlayer());
        return agents;
    }

    public static ArrayList<Agent<TicTacToeState, TicTacToeAction>> getPvCAgents(){
        ArrayList<Agent<TicTacToeState, TicTacToeAction>> agents = new ArrayList<>();
        agents.add(getHumanPlayer());
        agents.add(getMCTSPlayer());
        return agents;
    }

    public static BasicHumanAgent getHumanPlayer(){
        return new BasicHumanAgent<TicTacToeState, TicTacToeAction>();
    }

    public static MonteCarloTreeSearch getMCTSPlayer(){
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        return mcts;
    }

    public static GameplayLogger getGameplayLogger(){
        // This anonymous class was created with ChatGPT help - overriding with generics is quite hard
        return new GameplayLogger<TicTacToeState, TicTacToeAction, Sign>(){
            @Override
            public void logBeforeMove(TicTacToeState gameState, TicTacToeAction chosenAction){
                int i = 0;
                System.out.println("------");
                System.out.println("Possible actions:");
                for (TicTacToeAction action : gameState.getAllPossibleActions()) {
                    System.out.println(i+": "+action.toString());
                    i++;
                }
                System.out.println("------");
                System.out.println("Action chosen: "+chosenAction.toString());
                System.out.println("------");
            }
        };
    }

}
