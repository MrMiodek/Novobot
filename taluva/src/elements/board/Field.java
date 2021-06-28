package elements.board;

import elements.Building;
import elements.tiles.Terrain;

public class Field {

    Terrain terrain;
    int height;
    Building building;
    Position position;

    public Field(Position position, Terrain terrain, int height){
        this.position = position;
        this.terrain = terrain;
        this.height = height;
    }

    public Field(Field field){
        this.terrain = field.terrain;
        this.height = field.height;
        this.building = field.building;
        this.position = field.position;
    }

    public void placeBuilding(Building building){
        this.building = building;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getHeight() {
        return height;
    }

    public Building getBuilding() {
        return building;
    }

    public Position getPosition() {
        return position;
    }

}
