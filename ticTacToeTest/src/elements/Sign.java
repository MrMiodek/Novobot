package elements;

import boardgame.elements.GameActor;

public enum Sign implements GameActor {

    X,Y;

    public Sign getOpponent(){
        return this.equals(X) ? Y : X;
    }

}
