package elements;

import boardgame.elements.Action;
import tree.Node;

public class TicTacToeAction implements Action<TicTacToeState, Sign> {

    private int x;
    private int y;
    private Sign s;

    public TicTacToeAction(int x, int y, Sign s){
        this.x = x;
        this.y = y;
        this.s = s;
    }

    @Override
    public TicTacToeState apply(TicTacToeState gameState) {
        gameState.getBoard().setSign(x,y,s);
        gameState.switchActor();
        gameState.setNextNode(new Node(this));
        return gameState;
    }

    @Override
    public Sign getActor() {
        return s;
    }

    @Override
    public String toString(){
        int xCoordinateInt = 65+x;
        int yCoordinate = y+1;
        char xCoordinate = (char) xCoordinateInt;
        return "Put "+s+" at ("+xCoordinate+","+yCoordinate+")";
    }


    public static TicTacToeAction setUp(TicTacToeState gameState){
        return new TicTacToeAction(0,0,null){
            @Override
            public TicTacToeState apply(TicTacToeState any) {
                return gameState;
            }
            @Override
            public Sign getActor() {
                return Sign.Y;
            }
        };
    }

}
