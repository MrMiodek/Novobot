package elements.tiles;

public class Tile {

    Terrain left;
    Terrain right;

    public Tile(Terrain left, Terrain right){
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        return left + " "+ right;
    }

    public Terrain getLeft() {
        return left;
    }

    public Terrain getRight() {
        return right;
    }
}
