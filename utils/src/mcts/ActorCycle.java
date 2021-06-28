package mcts;

import boardgame.elements.GameActor;

import java.util.ArrayList;
import java.util.List;

public class ActorCycle<T extends GameActor>{

    ArrayList<T> actors;
    int idx=0;

    public ActorCycle(List<T> actors){
        this.actors = new ArrayList<T>(actors);
    }

    public ActorCycle(ActorCycle<T> cycle){
        this.actors = cycle.actors;
        this.idx = cycle.idx;
    }

    public T getNext(){
        T next = actors.get(idx);
        update();
        return next;
    }

    public boolean setNext(T actor){
        int next = actors.indexOf(actor);
        if(next>=0){
            idx = next;
            return true;
        }else{
            return false;
        }
    }

    public boolean setNext(int idx){
        if(idx>=0 && idx<actors.size()){
            this.idx = idx;
            return true;
        }else{
            return false;
        }
    }

    public void resetCycle(){
        idx=0;
    }

    private void update(){
        idx++;
        if(idx==actors.size()){
            idx=0;
        }
    }

}
