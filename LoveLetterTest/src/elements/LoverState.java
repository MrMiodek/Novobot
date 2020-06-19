package elements;

import elements.cards.CardType;
import elements.cards.CardTypes;
import elements.cards.LoveLetterAction;

import java.util.*;
import java.util.function.Predicate;

import static elements.cards.CardTypes.*;

public class LoverState {

    private Lover actor;
    private CardTypes currentIdentity;

    private List<CardTypes> cardsPlayed = new ArrayList<>();
    private LoverKnowledge loverKnowledge = new LoverKnowledge();
    private CardTypes[] activeHand = new CardTypes[2];

    public boolean shielded = false;
    public boolean playing = true;

    public LoverState(Lover lover, CardTypes card){
        actor = lover;
        currentIdentity = card;
    }

    public boolean canBeTargeted(){
        return playing && !shielded;
    }

    public CardTypes getCurrentIdentity(){
        return currentIdentity;
    }

    public void eliminate(){
        playing = false;
        if(currentIdentity!=null){
            cardsPlayed.add(currentIdentity);
            currentIdentity=null;
        }
    }

    public LoverState copy(){
        LoverState loverState = new LoverState(actor, currentIdentity);
        loverState.shielded = shielded;
        loverState.playing = playing;
        loverState.cardsPlayed = new ArrayList<>(cardsPlayed);
        loverState.loverKnowledge = loverKnowledge;
        System.arraycopy(activeHand, 0, loverState.activeHand, 0, activeHand.length);
        return loverState;
    }

    public List<LoveLetterAction> getPossibleActions(LoveLetterState gameState, boolean playerHasHonorAndThinksThatSzmaciurasPlaceIsOnTheStreet){
        ArrayList<LoveLetterAction> possibleActions = new ArrayList<>();
        Optional<CardTypes> szmaciura = Arrays.stream(activeHand)
                .filter(Predicate.isEqual(CardTypes.SZMACIURA))
                .findAny();
        if(szmaciura.isPresent()){
            Optional<CardTypes> caughtWith = Arrays.stream(activeHand)
                    .filter(card -> card.equals(KSIAZE) || card.equals(KROL))
                    .findAny();
            if(caughtWith.isPresent() || playerHasHonorAndThinksThatSzmaciurasPlaceIsOnTheStreet){
                return szmaciura.get().getPossibleActions(gameState);
            }
        }
        for(CardTypes card : activeHand){
            possibleActions.addAll(card.getPossibleActions(gameState));
        }
        return possibleActions;
    }

    public void addPlayedCard(CardTypes card){
        cardsPlayed.add(card);
    }

    public void drawCard(CardTypes newCard){
        activeHand[0] = currentIdentity;
        activeHand[1] = newCard;
        currentIdentity = null;
    }

    public void cardPlayed(CardTypes played){
        cardsPlayed.add(played);
        Optional<CardTypes> left = Arrays.stream(activeHand)
                .filter(card -> !card.equals(played))
                .findAny();
        currentIdentity = left.orElse(played);
        activeHand[0] = null;
        activeHand[1] = null;
    }

    public void replaceCard(CardTypes card){
        cardsPlayed.add(currentIdentity);
        currentIdentity = card;
    }

    public LoverKnowledge getLoverKnowledge(){
        return loverKnowledge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(actor.toString() + " - ");
        for(CardTypes card : cardsPlayed){
            sb.append(card).append(" ");
        }
        if(currentIdentity!=null)
            sb.append("(").append(currentIdentity).append(")");
        if(activeHand[0]!=null){
            sb.append("(").append(activeHand[0]).append(" ").append(activeHand[1]).append(")");
        }
        if(!playing){
            sb.append(" (XXX) ");
        }
        sb.append("\n");
        return sb.toString();
    }

    public void swapCard(CardTypes card) {
        currentIdentity = card;
    }
}
