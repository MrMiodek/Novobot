package elements.cards;

import boardgame.elements.Action;
import elements.LoveLetterState;
import elements.Lover;

import java.util.function.Function;

public class LoveLetterAction implements Action<LoveLetterState, Lover> {

    Function<LoveLetterState, Void> action;
    String additionalDescription = "";
    CardTypes card;
    Lover actor;
    Lover target;


    public LoveLetterAction (Lover actor, Lover target, CardTypes card, Function<LoveLetterState, Void> action){
        this.actor = actor;
        this.target = target;
        this.action = action;
        this.card = card;
    }

    public LoveLetterAction (Lover actor, Lover target, CardTypes card, Function<LoveLetterState, Void> action, String additionalDescription){
        this(actor,target,card, action);
        this.additionalDescription = additionalDescription;
    }

    @Override
    public LoveLetterState apply(LoveLetterState gameState) {
        gameState.getLoverState(actor).cardPlayed(card);
        action.apply(gameState);
        if(gameState.getEndScore()==null && gameState.getCards().cardsLeft > 1) {
            gameState.switchActors();
        }else{
            gameState.finished = true;
        }
        return gameState;
    }

    @Override
    public String toString(){
        return actor + " play " + card + additionalDescription + " on " + target;
    }

    @Override
    public Lover getActor() {
        return actor;
    }
}
