package elements;

import boardgame.elements.GameActor;

public enum Lover implements GameActor {

    LOVER_1(0), LOVER_2(1), LOVER_3(2), LOVER_4(3);

    private int id;

    Lover(int id){
        this.id = id;
    }

    static Lover getLover(int id){
        for(Lover lover : values()){
            if(lover.id == id){
                return lover;
            }
        }
        return null;
    }

    Lover getNextLover(int numberOfPlayers){
        return getLover(( id +1) % numberOfPlayers);
    }

    public int getId(){
        return id;
    }

}
