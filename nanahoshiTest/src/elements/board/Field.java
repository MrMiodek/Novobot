package elements.board;

import elements.tiles.Tile;

public class Field {

    final Coordinates position;

    private Tile faceDown;
    private Tile faceUp;

    Field(Coordinates position) {
        this.position = position;
    }

    public Tile getFaceDown() {
        return faceDown;
    }

    public void setFaceDown(Tile faceDown) {
        this.faceDown = faceDown;
    }

    public Tile getFaceUp() {
        return faceUp;
    }

    public void setFaceUp(Tile faceUp) {
        this.faceUp = faceUp;
    }

}
