import elements.TicTacToeConfig;
import gameplay.PlayOutTester;
import monte_carlo.MCTSBuilder;
import monte_carlo.MonteCarloTreeSearch;

public class TicTacToeGame {

    public static void main(String [] args){
        TicTacToeConfig tttConfig = new TicTacToeConfig();
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        PlayOutTester playOut = new PlayOutTester(tttConfig , mcts);
        playOut.playout();
    }

}
