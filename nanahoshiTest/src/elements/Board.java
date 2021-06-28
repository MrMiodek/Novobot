package elements;

import elements.tiles.Tile;
import elements.tiles.TileSet;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {

    private Field[][] fields;
    private ArrayList<Field> fieldList;

    public Board(TileSet tiles) {
        fields = new Field[4][4];
        fieldList = new ArrayList<>();
        for (Iterator<Coordinates> it = Coordinates.getLegal(); it.hasNext(); ) {
            Coordinates pos = it.next();
            Field field = new Field(pos);
            fields[pos.x][pos.y] = field;
            fieldList.add(field);
        }
        placeTileSet(tiles);
    }

    private void placeTileSet(TileSet tiles){
        for(Tile tile : tiles.getKnownTiles()){
            if(tile.isOnBoard()){
                Coordinates position = tile.getPosition();
                if(tile.isHidden()){
                    getField(position).setFaceDown(tile);
                }else{
                    getField(position).setFaceUp(tile);
                }
            }
        }
    }

    public Field getField(Coordinates pos) {
        if(pos.isLegal())
            return fields[pos.x][pos.y];
        else
            return null;
    }

    public Iterator<Field> getFieldsIterator() {
        return fieldList.iterator();
    }

}
