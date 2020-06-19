import boardgame.elements.Action;
import boardgame.elements.GameState;
import elements.LoveLetterConfig;
import gameplay.PlayOutTester;
import monte_carlo.MCTSBuilder;
import monte_carlo.MonteCarloTreeSearch;

public class LoveLetterGame {

    public static void main(String [] args){
        LoveLetterConfig llConfig = new LoveLetterConfig(4);
        MCTSBuilder mctsBuilder = new MCTSBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        PlayOutTester playOut = new PlayOutTester(llConfig , mcts){
            @Override
            public void logBeforeMove(GameState gameState, Action action) {
                System.out.println(action);
            }
        };
        playOut.playout();
    }

}
