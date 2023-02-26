import boardgame.elements.Action;
import boardgame.elements.GameState;
import elements.LoveLetterConfig;
import gameplay.GameplayLogger;
import gameplay.PlayOutTester;
import gameplay.RunnerException;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

public class LoveLetterTester {

    public static void main(String [] args) throws RunnerException {
        LoveLetterConfig llConfig = new LoveLetterConfig(4);
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        GameplayLogger logger = new GameplayLogger(){
            @Override
            public void logBeforeMove(GameState gameState, Action action) {
                System.out.println(action);
            }
        };
        PlayOutTester playOut = new PlayOutTester(llConfig , mcts, logger);
        playOut.playout();
    }

}
