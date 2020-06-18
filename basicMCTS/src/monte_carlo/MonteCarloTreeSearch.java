package monte_carlo;


import boardgame.custom_functions.*;
import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameState;
import gameplay.Agent;
import tree.Node;
import tree.Tree;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Basic implementation of Monte Carlo tree search
 * It should be able to handle most of finite turn based games.
 */
public class MonteCarloTreeSearch
        <GS extends GameState,
        GA extends GameActor,
        A extends Action<GS,GA>,
        N extends Node<GS,GA,A,N>>

        implements Agent<GS,A> {

    /**
     * timeCalculator function calculate how much time can MCTS think on given game state
     */
    TimeCalculator<GS> timeCalculator;

    /**
     * Function calculating best scoring child of current situation node
     */
    ChildFinder<GS,GA,A,N> childFinder;

    /**
     * Function responsible to update node score after game concludes
     */
    ScoreUpdater<GA> scoreUpdater;

    /**
     * Function that chooses action to play for given GameState
     */
    ActionChooser<A,GS> actionChooser;


    /**
     * Create MCTS agent with functions specific for specific BoardGame
     * @param config object containing functions specific for given game and its configuration
     */
    public MonteCarloTreeSearch(MCTSConfig config){
        this.timeCalculator = config.timeCalculator;
        this.childFinder = config.bestChildFinder;
        this.scoreUpdater = config.scoreUpdater;
        this.actionChooser = config.actionChooser;
    }

    @Override
    public A chooseActionToPlay(GS gameState) {
        N analysis = analyzePossibleMoves(gameState);
        return analysis.getChildArray().stream()
                .max(Comparator.comparing(N::getVisitCount))
                .get()
                .getAction();
    }

    /**
     * Function analyzing current situation
     * @param gameState state of the game to analyze
     * @return root node of the analysis tree
     */
    private N analyzePossibleMoves(GS gameState){
        long start = System.currentTimeMillis();
        long end = start + timeCalculator.getTimeToThink(gameState);
        Tree<N> tree = new Tree();
        N rootNode = tree.getRoot();
        do{
            GS tmpState = (GS) gameState.copy();
            // 1. Move down the tree up to promising child node
            N promisingNode = runGameUpToPromisingNode(tmpState, rootNode);
            // 2. Expand promising node
            if (tmpState.getEndScore() == null)
                expandNode(tmpState, promisingNode);

            // 3. Simulate game
            N nodeToExplore = promisingNode;
            if (promisingNode.getChildArray().size() > 0) {
                nodeToExplore = promisingNode.getRandomChildNode();
                nodeToExplore.getAction().apply(tmpState);
            }
            Map<GA,Integer> endScores = simulateRandomPlayout(tmpState, nodeToExplore);
            // 4. Update Nodes values
            backPropogation(nodeToExplore, endScores);
        } while (System.currentTimeMillis() < end);
        return rootNode;
    }

    private N runGameUpToPromisingNode(GS gameState, N rootNode) {
        N node = rootNode;
        while (node.getChildArray().size() != 0) {
            node = childFinder.getBestChild(node);
            node.getAction().apply(gameState);
        }
        return node;
    }

    private void expandNode(GS gameState, N node) {
        node.setChildArray(
                (List) gameState.getAllPossibleActions().stream()
                .map(action -> {
                    Node newNode = new Node((A) action);
                    newNode.setParent(node);
                    return newNode;
                })
                .collect(Collectors.toList()));
    }

    private void backPropogation(N nodeToExplore, Map<GA,Integer> endScore) {
        N tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.incrementVisit();
            scoreUpdater.updateNodeScore(tempNode, endScore);
            tempNode = tempNode.getParent();
        }
    }

    private Map<GA,Integer> simulateRandomPlayout(GS gameState, N node) {
        N tmpNode = (N) new Node<>(node);
        GS tmpState = (GS) gameState.copy();
        Map<GA, Integer> endScore = tmpState.getEndScore();
        if(endScore != null){
            GA actor = (GA) tmpNode.getPreviousActor();
            Integer score = endScore.get(actor);
            tmpNode.addScore(score);
        }
        while(endScore==null){
            tmpState = playSmartMove(tmpState);
            endScore = tmpState.getEndScore();
        }
        return endScore;
    }

    private GS playSmartMove(GS gameState){
        Action<GS, GA> action = actionChooser.chooseAction(gameState);
        action.apply(gameState);
        return gameState;
    }
}
