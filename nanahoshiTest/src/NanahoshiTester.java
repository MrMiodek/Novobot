import elements.Alliance;
import elements.NanahoshiConfig;
import elements.actions.NanahoshiAction;
import elements.gamestate.NanahoshiState;
import gameplay.GameplayLogger;
import gameplay.PlayOutTester;
import gameplay.RunnerException;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;

public class NanahoshiTester {

    public static void main(String [] args) throws RunnerException {
        NanahoshiConfig nanahoshiConfig = new NanahoshiConfig();
        MCTSBuilder mctsBuilder = new NanahoshiBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        PlayOutTester test = configPlayoutTester(nanahoshiConfig, mcts);
        test.playout();
    }

    private static PlayOutTester configPlayoutTester(NanahoshiConfig nanahoshiConfig, MonteCarloTreeSearch mcts) throws RunnerException {

        GameplayLogger<NanahoshiState, NanahoshiAction, Alliance> logger = new GameplayLogger<>(){
            @Override
            public void logAfterMove(NanahoshiState gameState, NanahoshiAction action) { }

            @Override
            public void logBeforeMove(NanahoshiState gameState, NanahoshiAction action) {
                System.out.println("("+(gameState.history.size()-1)+") " + gameState.getCurrentActor() + " - " + action.toStringBefore(gameState));
            }
        };

        return new PlayOutTester<NanahoshiState, NanahoshiAction, Alliance>(nanahoshiConfig , mcts, logger);
    }

}
