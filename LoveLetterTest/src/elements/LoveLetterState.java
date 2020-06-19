package elements;

import boardgame.elements.GameState;
import elements.cards.CardSet;
import elements.cards.CardTypes;
import elements.cards.LoveLetterAction;

import java.util.*;

public class LoveLetterState implements GameState<LoveLetterState, Lover, LoveLetterAction> {

    Lover currentActor;
    List<CardTypes> visibleCards;
    CardSet cards;
    Map<Lover, LoverState> loverStates = new TreeMap<>();
    int allActorsCount;
    public int inPlayActorCount;
    public boolean finished = false;

    public LoveLetterState(){}

    public LoveLetterState(int allActorsCount, List<CardTypes> visibleCards, CardSet cardSet){
        this.currentActor = Lover.LOVER_1;
        this.visibleCards = visibleCards;
        this.cards = cardSet.copy();
        for(Lover lover : Lover.values()){
            if(lover.getId() < allActorsCount){
                loverStates.put(lover, new LoverState(lover, cards.drawOne()));
            }
        }
        this.allActorsCount = allActorsCount;
        this.inPlayActorCount = allActorsCount;
        loverStates.get(currentActor).drawCard(cards.drawOne());
    }

    public LoverState getLoverState(Lover lover){
        return loverStates.get(lover);
    }

    public void switchActors(){
        do{
            currentActor = currentActor.getNextLover(allActorsCount);
        }while(!loverStates.get(currentActor).playing);
        LoverState current = loverStates.get(currentActor);
        current.drawCard(cards.drawOne());
        current.shielded = false;
    }

    public CardTypes drawNew(){
        return cards.drawOne();
    }

    @Override
    public Lover getCurrentActor() {
        return currentActor;
    }

    @Override
    public LoveLetterState copy() {
        LoveLetterState copy = new LoveLetterState();
        copy.currentActor = currentActor;
        copy.loverStates = copyLoverStates();
        copy.visibleCards = new ArrayList<>(visibleCards);
        copy.cards = this.cards.copy();
        copy.inPlayActorCount = inPlayActorCount;
        copy.allActorsCount = allActorsCount;
        copy.finished = finished;
        return copy;
    }

    private Map<Lover,LoverState> copyLoverStates(){
        Map<Lover,LoverState> copy = new TreeMap<>();
        loverStates.forEach((key, value) -> copy.put(key, value.copy()));
        return copy;
    }

    @Override
    public Map<Lover, Integer> getEndScore() {
        if(inPlayActorCount <= 1 || finished){
            Map<Lover, Integer> endScore = new TreeMap<>();
            int winningNum = 0;
            for(Map.Entry<Lover, LoverState> entry : loverStates.entrySet()){
                LoverState loverState = entry.getValue();
                if(loverState.playing){
                    if(loverState.getCurrentIdentity().getCardValue()>winningNum){
                        winningNum = loverState.getCurrentIdentity().getCardValue();
                    }
                }
            }
//            int winningNum = loverStates.entrySet().stream()
//                    .filter(entry -> entry.getValue().playing)
//                    .max(Comparator.comparingInt(entry -> entry.getValue().getCurrentIdentity().getCardValue()))
//                    .get().getValue()
//                    .getCurrentIdentity()
//                    .getCardValue();
            for(Map.Entry<Lover, LoverState> entry : loverStates.entrySet()){
                if(!entry.getValue().playing || entry.getValue().getCurrentIdentity().getCardValue() < winningNum){
                    endScore.put(entry.getKey(),-1);
                }else{
                    endScore.put(entry.getKey(), allActorsCount-1);
                }
            }
            return endScore;
        }
        return null;
    }

    @Override
    public List<LoveLetterAction> getAllPossibleActions() {
        return loverStates.get(currentActor).getPossibleActions(this, false);
    }

    public List<Lover> getEnemyTargets(){
        List<Lover> possibleTargets = new ArrayList<>();
        for(Lover lover : loverStates.keySet()){
            if(!lover.equals(currentActor) && getLoverState(lover).canBeTargeted()){
                possibleTargets.add(lover);
            }
        }
        if(possibleTargets.isEmpty())
            possibleTargets.add(currentActor);
        return possibleTargets;
    }

    public CardSet getCards(){
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n----------\n\n");
        for(Map.Entry<Lover,LoverState> entry : loverStates.entrySet()){
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
}
