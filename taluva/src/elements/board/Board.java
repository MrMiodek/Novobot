package elements.board;

import java.util.HashMap;
import java.util.Map;

public class Board {

    Map<Position, Field> map;

    public Board(){
        map = new HashMap<>();
    }

    public Board(Board board){
        this.map = new HashMap<>(board.map);
    }

}
