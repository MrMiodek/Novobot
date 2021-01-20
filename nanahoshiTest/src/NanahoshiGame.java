import boardgame.elements.Action;
import boardgame.elements.GameState;
import elements.NanahoshiConfig;
import elements.actions.NanahoshiAction;
import elements.actions.PickUpTile;
import elements.gamestate.NanahoshiState;
import gameplay.PlayOutTester;
import montecarlo.BasicUCT;
import montecarlo.MCTSBuilder;
import montecarlo.MCTSConfig;
import montecarlo.MonteCarloTreeSearch;
import tree.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class NanahoshiGame {

    public static void main(String [] args){
        NanahoshiConfig nanahoshiConfig = new NanahoshiConfig();
        MCTSBuilder mctsBuilder = new NanahoshiBuilder();
        mctsBuilder.withTimeCalculator( any -> 1000 );
        MonteCarloTreeSearch mcts = mctsBuilder.build();
        PlayOutTester test = configPlayoutTester(nanahoshiConfig, mcts);
        test.playout();
    }

    private static PlayOutTester configPlayoutTester(NanahoshiConfig nanahoshiConfig, MonteCarloTreeSearch mcts){
        return new PlayOutTester<NanahoshiState, NanahoshiAction>(nanahoshiConfig , mcts){
            @Override
            public void logAfterMove(NanahoshiState gameState, NanahoshiAction action) { }

            @Override
            public void logBeforeMove(NanahoshiState gameState, NanahoshiAction action) {
                System.out.println("("+(gameState.history.size()-1)+") " + gameState.getCurrentActor() + " - " + action.toStringBefore(gameState));
            }
        };
    }

}
