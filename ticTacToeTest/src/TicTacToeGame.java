import elements.TicTacToeConfig;
import gameplay.PlayOutTester;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

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
