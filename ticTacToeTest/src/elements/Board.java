package elements;

import java.util.ArrayList;
import java.util.List;

public class Board {

    Sign[][] board = new Sign[3][3];
    int filled = 0;

    public Board copy(){
        Board board = new Board();
        for(int x = 0; x <=2 ; x++)
            System.arraycopy(this.board[x], 0, board.board[x], 0, 3);
        board.filled = this.filled;
        return board;
    }

    public boolean setSign(int x, int y, Sign sign){
        if(x < 0 || y< 0 || x> 2 || y > 2)
            return false;
        if(board[x][y] != null)
            return false;
        board[x][y] = sign;
        filled++;
        return true;
    }

    public Sign getWinner(){
        Sign winner = getDiagonalWinner();
        winner = winner != null ? winner : getHorizontalWinner();
        winner = winner != null ? winner : getVerticalWinner();
        return winner;
    }

    public boolean hasEnded(){
        return getWinner()!=null || filled==9;
    }

    public List<TicTacToeAction> getAllPossibleMoves(Sign sign){
        List<TicTacToeAction> actions = new ArrayList<>();
        for (int x = 0; x <= 2; x++){
            for(int y = 0; y <= 2; y++){
                if(board[x][y]==null){
                    actions.add(new TicTacToeAction(x,y,sign));
                }
            }
        }
        return actions;
    }

    private Sign getVerticalWinner(){
        for(int x = 0 ; x <=2 ; x++){
            Sign s = board[x][0];
            if(s!=null && board[x][1] == s && board[x][2]==s)
                return s;
        }
        return null;
    }

    private Sign getHorizontalWinner(){
        for(int y = 0 ; y <=2 ; y++){
            Sign s = board[0][y];
            if(s!=null && board[1][y] == s && board[2][y]==s)
                return s;
        }
        return null;
    }

    private Sign getDiagonalWinner(){
        Sign s = board[1][1];
        if(s!=null){
            if(board[0][0] == s && board[2][2]==s)
                return s;
            if(board[0][2] == s && board[2][0]==s)
                return s;
        }
        return null;
    }

    @Override
    public String toString(){
        return printRow(0) +
                printInterLine() +
                printRow(1) +
                printInterLine() +
                printRow(2);
    }

    private String printRow(int y){
        return " "+sign(0,y) + " | " + sign(1,y) + " | " + sign(2,y)+"\n";
    }

    private String sign(int x, int y){
        return board[x][y] != null ? board[x][y].toString() : " ";
    }

    private String printInterLine(){
        return "---|---|---\n";
    }



}
