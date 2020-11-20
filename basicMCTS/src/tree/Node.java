package tree;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents single point of time in the game.
 * It should be very light as multiple of them will be needed in memory at once
 * Given all recursive parents and initial game state,
 * you should be able to fully recreate current state of the game
 */
public class Node<GS extends GameState, GA extends GameActor, A extends Action<GS,GA>, N extends Node<GS,GA,A,N>> {

    /**
     * last action that lead us to this Node
     */
    private A action;

    /**
     * Node representing point of the game before last action happened
     */
    private N parent;

    /**
     * optional list of game situations that can be achieved within one action unit.
     */
    private List<N> childArray;

    /**
     * Number of times this node has been visited in MCTS simulations
     */
    private int visitCount;

    /**
     * Estimated value of this node for previous actor
     */
    private int winScore;

    /**
     * Function returning short description of last action, current score and visit count of the node.
     * @return short description of current node
     */
    @Override
    public String toString(){
        if(action!=null)
            return action.toString()+" "+winScore+"/"+visitCount;
        else
            return "root"+" "+winScore+"/"+visitCount;
    }


    //TODO should game randomness be game actor? and if so, how should it be handled?
    /**
     * @return actor who's action led us to current state of the game or null if this is root node
     */
    public GA getPreviousActor(){
        if(action!=null){
            return action.getActor();
        }
        return null;
    }

    public Node(){
        childArray = new ArrayList<>();
    }

    public Node(A action) {
        this.action = action;
        childArray = new ArrayList<>();
    }

    public Node(N node) {
        this.action = node.getAction();
        this.parent = node.getParent();
        this.childArray = node.getChildArray().stream()
                .map(childNode -> (N) new Node<>(childNode))
                .collect(Collectors.toList());
    }

    public N getRandomChildNode() {
        int noOfPossibleMoves = this.childArray.size();
        int selectRandom = (int) (Math.random() * noOfPossibleMoves);
        return this.childArray.get(selectRandom);
    }

    public void incrementVisit() {
        this.visitCount++;
    }

    public void addScore(int score) {
        if (this.winScore != Integer.MIN_VALUE || this.winScore != Integer.MAX_VALUE)
            this.winScore += score;
    }

    public A getAction() {
        return action;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public List<N> getChildArray() {
        return childArray;
    }

    public void setChildArray(List<N> childArray) {
        this.childArray = childArray;
    }

    public N getParent() {
        return parent;
    }

    public void setParent(N parent) {
        this.parent = parent;
    }

    public double getWinScore() {
        return winScore;
    }

}
