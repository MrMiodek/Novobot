package elements.tiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileSet {

    ArrayList<Tile> tiles;

    public TileSet(TileSet set){
        tiles = new ArrayList<>(set.tiles);
        Collections.shuffle(tiles);
    }

    public TileSet(List<Tile> tileSet){
        this.tiles = new ArrayList<>(tileSet);
        Collections.shuffle(tiles);
    }

    public Tile getTile(){
        return tiles.remove(tiles.size()-1);
    }

    public int getSize(){
        return tiles.size();
    }

}
