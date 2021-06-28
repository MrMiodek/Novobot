package elements.tiles;

import elements.Alliance;
import elements.Coordinates;
import elements.directions.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TileSet {

    private ArrayList<Tile> knownTiles;
    private ArrayList<Ship> concealedShips;

    public TileSet() {
        knownTiles = new ArrayList<>();
        concealedShips = new ArrayList<>();
        for (Alliance side : Alliance.values())
            for (ShipClass type : ShipClass.values())
                for (int i = 0; i < 2; i++)
                    concealedShips.add(new Ship(side, type));
        Collections.shuffle(concealedShips);
        for (Iterator<Coordinates> it = Coordinates.getLegal(); it.hasNext(); ) {
            Coordinates position = it.next();
            knownTiles.add(new Tile(position));
        }
    }

    public TileSet(TileSet set) {
        knownTiles = new ArrayList<>();
        concealedShips = new ArrayList<>();
        for (Tile tile : set.knownTiles) {
            knownTiles.add(new Tile(tile));
        }
        concealedShips.addAll(set.concealedShips);
        Collections.shuffle(concealedShips);
    }

    public ArrayList<Tile> getKnownTiles() {
        return knownTiles;
    }

    public void setKnownTiles(ArrayList<Tile> knownTiles) {
        this.knownTiles = knownTiles;
    }

    public ArrayList<Ship> getConcealedShips() {
        return concealedShips;
    }

    public void setConcealedShips(ArrayList<Ship> concealedShips) {
        this.concealedShips = concealedShips;
    }

    public void revealShip(Tile tile, Ship ship){
        concealedShips.remove(ship);
        tile.ship = ship;
        tile.activity.heading = Direction.IMMOBILE;
    }

    public boolean isBadLuck(){
        if(knownTiles.isEmpty() || concealedShips.isEmpty())
            return false;

        Alliance known = knownTiles.get(0).ship.alliance;
        for(Tile tile : knownTiles){
            if(tile.ship.alliance != known) {
                known = null;
                break;
            }
        }
        if(known==null)
            return false;


        Alliance concealed = concealedShips.get(0).alliance;
        for(Ship ship : concealedShips){
            if(ship.alliance != concealed) {
                concealed = null;
                break;
            }
        }

        return concealed != null;

    }

}
