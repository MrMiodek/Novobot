import elements.Sign;
import elements.TicTacToeAction;
import elements.TicTacToeConfig;
import elements.TicTacToeState;
import gameplay.GameplayLogger;
import gameplay.PlayOutTester;
import gameplay.RunnerException;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

public class TicTacToeTester {

    public static void main(String [] args) throws RunnerException {
        TicTacToeConfig tttConfig = new TicTacToeConfig();
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();

        // This anonymous class was created with ChatGPT help - overriding with generics is quite hard
        GameplayLogger<TicTacToeState, TicTacToeAction, Sign> logger = new GameplayLogger<TicTacToeState, TicTacToeAction, Sign>(){
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
        PlayOutTester<TicTacToeState,TicTacToeAction, Sign> playOut = new PlayOutTester<TicTacToeState,TicTacToeAction, Sign>(tttConfig , mcts, logger);
        playOut.playout();
    }

}
