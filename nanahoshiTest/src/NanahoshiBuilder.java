import boardgame.customfunctions.ChildFinder;
import elements.Alliance;
import elements.actions.NanahoshiAction;
import elements.actions.PickUpTile;
import elements.gamestate.NanahoshiState;
import montecarlo.BasicUCT;
import montecarlo.MCTSBuilder;
import montecarlo.MonteCarloTreeSearch;
import tree.Node;

import java.util.List;
import java.util.Random;

public class NanahoshiBuilder extends MCTSBuilder {

    public NanahoshiBuilder(){
        config.bestChildFinder = getCustomChildFinder();
    }

    static Random r = new Random();

    //TODO this feels a bit dirty, make it more generic

    @Override
    public MonteCarloTreeSearch<
            NanahoshiState,
            Alliance,
            NanahoshiAction,
            NanahoshiNode> build(){
        return new MonteCarloTreeSearch<>(config){

            @Override
            public NanahoshiAction chooseActionToPlay(NanahoshiState gameState) {
                if(gameState.getLastAction() instanceof PickUpTile.PickUpTileAction){
                    List<NanahoshiAction> actions = gameState.getAllPossibleActions();
                    return actions.get(r.nextInt(actions.size()));
                }else{
                    return getDefaultActionChooser().chooseAction(gameState);
                }
            }

        };
    }

    private ChildFinder getCustomChildFinder(){
        return new BasicUCT(){
            @Override
            public Node getBestChild(Node node) {
                List<Node> children = node.getChildArray();
                if(node.getAction() instanceof PickUpTile){
                    Random r = new Random();
                    return children.get(r.nextInt(children.size()));
                }
                return super.getBestChild(node);
            }
        };
    }

}
class NanahoshiNode extends Node<NanahoshiState, Alliance, NanahoshiAction, NanahoshiNode>{ }

