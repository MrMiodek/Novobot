import config.TaluvaConfig;
import gameplay.PlayOutTester;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

public class TaluvaGame {

    public static void main(String [] args){
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        TaluvaConfig config = new TaluvaConfig();
        PlayOutTester playOut = new PlayOutTester(config , mcts);
        playOut.playout();
    }

}
