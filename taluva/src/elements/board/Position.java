package elements.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {

    int x;
    int y;
    int z;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        this.z = -x-y;
    }

    //TODO potentially could be an object that gives one Position at a time and store only one state
    public List<Position> getNeighbours(){
        ArrayList<Position> neighbours = new ArrayList<>();
        neighbours.add(new Position(x-1, y+1));
        neighbours.add(new Position(x+1, y+1));
        neighbours.add(new Position(x, y+1));
        neighbours.add(new Position(x, y+1));
        neighbours.add(new Position(x, y+1));
        neighbours.add(new Position(x, y+1));
        return neighbours;
    }

    public Position move(Position vector){
        return new Position(x+vector.x, y+ vector.y);
    }

    public Position getVectorTo(Position end){
        int dx = end.x - x;
        int dy = end.y - y;
        return new Position(dx,dy);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Position){
            Position objP = (Position) obj;
            return x==objP.x &&
                    y==objP.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
