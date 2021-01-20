package montecarlo;


import boardgame.customfunctions.*;
import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameState;
import gameplay.MCTSAgent;
import tree.Node;
import tree.Tree;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Basic implementation of Monte-Carlo Tree Search
 * It should be able to handle most of finite turn based games.
 */
public class MonteCarloTreeSearch
        <GS extends GameState,
        GA extends GameActor,
        A extends Action<GS,GA>,
        N extends Node<GS,GA,A,N>>

        implements MCTSAgent<GS,A, N> {

    /**
     * timeCalculator function calculate how much time can MCTS think on given game state
     * @see TimeCalculator
     */
    TimeCalculator<GS> timeCalculator;

    /**
     * Function calculating best scoring child of current situation node
     * @see ChildFinder
     */
    ChildFinder<GS,GA,A,N> childFinder;

    /**
     * Function responsible to update node score after game concludes
     * @see ScoreUpdater
     */
    ScoreUpdater<GA> scoreUpdater;

    /**
     * Function that chooses action to play for given GameState during simulations
     * @see ActionChooser
     */
    ActionChooser<A,GS> simulatedActionChooser;


    /**
     * Create MCTS agent with functions adjusted for specific BoardGame
     * @param config object containing functions specific for given game and its configuration
     */
    public MonteCarloTreeSearch(MCTSConfig config){
        this.timeCalculator = config.timeCalculator;
        this.childFinder = config.bestChildFinder;
        this.scoreUpdater = config.scoreUpdater;
        this.simulatedActionChooser = config.simulatedActionChooser;
    }

    /**
     * Function that makes decision on what action to make based on current state of the game
     */
    @Override
    public A chooseActionToPlay(GS gameState) {
        return getDefaultActionChooser().chooseAction(gameState);
    }

    @Override
    public ActionChooser<A,GS> getDefaultActionChooser(){
        return gameState -> {
            N analysis = analyzePossibleMoves(gameState);
            return chooseActionAfterSimulations(analysis);
        };
    }

    /**
     * In general MCTS chooses actions that were taken in most amount of simulations
     */
    @Override
    public A chooseActionAfterSimulations(N analysis){
        System.out.println(analysis);
        return analysis.getChildArray().stream()
                .max(Comparator.comparing(N::getVisitCount))
                .get()
                .getAction();
    }

    /**
     * Function analyzing current state of the game and giving score to analysed moves
     * Analysis is made at least for a time returned by set timeCalculator
     * @see TimeCalculator
     * @param gameState state of the game to analyze
     * @return root node of the analysis tree
     */
    @Override
    public N analyzePossibleMoves(GS gameState){
        long start = System.currentTimeMillis();
        long end = start + timeCalculator.getTimeToThink(gameState);
        Tree<N> tree = new Tree();
        N rootNode = tree.getRoot();
        do{
            GS tmpState = (GS) gameState.copy();
            // 1. Move down the tree up to promising child node
            N promisingNode = runGameUpToPromisingLeaf(tmpState, rootNode);
            // 2. Expand promising node
            if (tmpState.getEndScore() == null)
                expandNode(tmpState, promisingNode);

            // 3. Simulate game
            N nodeToExplore = promisingNode;
            if (promisingNode.getChildArray().size() > 0) {
                nodeToExplore = promisingNode.getRandomChildNode();
                nodeToExplore.getAction().apply(tmpState);
            }
            Map<GA,Integer> endScores = simulatePlayout(tmpState, nodeToExplore);
            // 4. Update Nodes values
            backPropogation(nodeToExplore, endScores);
        } while (System.currentTimeMillis() < end);
        return rootNode;
    }

    /**
     * Function that chooses next promising child nodes until the last game state covered by MCTS tree
     * @see ChildFinder
     * @return promising leaf node of given MCTS tree
     */
    private N runGameUpToPromisingLeaf(GS gameState, N rootNode) {
        N node = rootNode;
        while (node.getChildArray().size() != 0) {
            node = childFinder.getBestChild(node);
            node.getAction().apply(gameState);
        }
        return node;
    }

    /**
     * Create children for given leaf node based on list
     * of all possible actions in current state of the game
     */
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

    /**
     * Function that traverse from leaf node
     * up to root of MCTS tree to update score of all visited nodes
     * @see ScoreUpdater
     */
    private void backPropogation(N nodeToExplore, Map<GA,Integer> endScore) {
        N tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.incrementVisit();
            scoreUpdater.updateNodeScore(tempNode, endScore);
            tempNode = tempNode.getParent();
        }
    }

    /**
     * Function that simulate playout - complete the game
     * from given state of the game. Usually used on leaf of MCTS tree
     * with current state of the game
     * @return map of agents and their results at the end of the playout
     */
    private Map<GA,Integer> simulatePlayout(GS gameState, N node) {
        N tmpNode = (N) new Node<>(node);
        GS tmpState = (GS) gameState.copy();
        Map<GA, Integer> endScore = tmpState.getEndScore();
        if(endScore != null){
            GA actor = tmpNode.getPreviousActor();
            Integer score = endScore.get(actor);
            tmpNode.addScore(score);
        }
        while(endScore==null){
            tmpState = simulatePlayoutMove(tmpState);
            endScore = tmpState.getEndScore();
        }
        return endScore;
    }

    /**
     * Function that makes single move in simulation playout
     * Move is chosen based on set actionChooser
     * @see ActionChooser
     * @return state of the game after simulated move
     */
    private GS simulatePlayoutMove(GS gameState){
        Action<GS, GA> action = simulatedActionChooser.chooseAction(gameState);
        action.apply(gameState);
        return gameState;
    }

}
