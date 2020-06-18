package elements;

import boardgame.elements.GameState;
import tree.Node;

import java.util.*;

public class TicTacToeState implements GameState<TicTacToeState, Sign, TicTacToeAction> {

    Board board = new Board();
    Sign currentActor = Sign.X;
    Node node = new Node(TicTacToeAction.setUp(this));

    public TicTacToeState(){ }

    public TicTacToeState(Board board, Sign sign){
        this.board = board.copy();
        currentActor = sign;
    }

    @Override
    public Sign getCurrentActor() {
        return currentActor;
    }

    public void switchActor(){
        currentActor = currentActor.getOpponent();
    }

    public TicTacToeState copy() {
        return new TicTacToeState(board, currentActor);
    }

    @Override
    public Map<Sign, Integer> getEndScore() {
        boolean hasEnded = board.hasEnded();
        if(hasEnded){
            Sign winner = board.getWinner();
            Map<Sign, Integer> map = new TreeMap<>();
            for(Sign s : Sign.values()){
                if(winner==null){
                    map.put(s,0);
                } else {
                    map.put(s, s.equals(winner) ? 1 : -1);
                }
            }
            return map;
        }
        return null;
    }

    @Override
    public List<TicTacToeAction> getAllPossibleActions() {
        return board.getAllPossibleMoves(currentActor);
    }

    @Override
    public String toString(){
        return "\n------\n" +
                "Next to move: " + currentActor + "\n\n" +
                board.toString() +
                "\n";
    }

    public Board getBoard(){
        return board;
    }

    public void setNextNode(Node node){
        ArrayList<Node> child = new ArrayList();
        child.add(node);
        this.node.setChildArray(child);
        this.node = node;
    }
}
