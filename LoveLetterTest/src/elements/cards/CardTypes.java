package elements.cards;

import elements.LoveLetterState;
import elements.Lover;
import elements.LoverState;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CardTypes implements CardType {

    STRAZNICZKA(1, 5){
        @Override
        public List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target) {

            return Arrays.stream(CardTypes.values())
                    .filter(card -> !card.equals(STRAZNICZKA))
                    .map(card -> new LoveLetterAction(actor, target, this, state -> {
                        LoverState targetedActor = state.getLoverState(target);
                        if(targetedActor.getCurrentIdentity().equals(card)){
                            state.inPlayActorCount--;
                            targetedActor.eliminate();
                        }
                        return null;
                    }, " (check if target has "+card+") "))
                    .collect(Collectors.toList());
        }
    },

    KSIADZ(2,2) {
        @Override
        public List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target) {
            List<LoveLetterAction> actions = new ArrayList<>();
            Function<LoveLetterState, Void> action = state -> null;
            actions.add(new LoveLetterAction(actor,target,this,action));
            return actions;
        }
    },

    BARON(3,2){
        @Override
        public List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target) {
            List<LoveLetterAction> actions = new ArrayList<>();
            Function<LoveLetterState, Void> action = state ->{
                LoverState actorState = state.getLoverState(actor);
                LoverState targetState = state.getLoverState(target);
                CardTypes otherCard = actorState.getCurrentIdentity();
                int actorValue = otherCard.getCardValue();
                int targetValue = targetState.getCurrentIdentity().getCardValue();
                if(actorValue > targetValue){
                    state.inPlayActorCount--;
                    targetState.eliminate();
                }
                if(actorValue < targetValue){
                    state.inPlayActorCount--;
                    actorState.eliminate();
                }
                return null;
            };
            actions.add(new LoveLetterAction(actor, target, this, action));
            return actions;
        }
    },

    POKOJOWKA(4,2){
        @Override
        public List<LoveLetterAction> getPossibleActions(LoveLetterState gameState) {
            List<LoveLetterAction> actions = new ArrayList<>();
            Lover actor = gameState.getCurrentActor();
            Function<LoveLetterState, Void> action = state -> {
                state.getLoverState(actor).shielded = true;
                return null;
            };
            actions.add(new LoveLetterAction(actor, actor, this, action));
            return actions;
        }
    },

    KSIAZE(5,2){
        @Override
        public List<LoveLetterAction> getPossibleActions(LoveLetterState gameState) {
            List<Lover> targets = gameState.getEnemyTargets();
            Lover actor = gameState.getCurrentActor();
            if(!targets.contains(actor))
                targets.add(actor);
            return targets.stream()
                    .map(target -> new LoveLetterAction(actor, target, this, state -> {
                        LoverState targetState = state.getLoverState(target);
                        if(targetState.getCurrentIdentity().equals(SZMULA)){
                            state.inPlayActorCount--;
                            targetState.eliminate();
                        }else{
                            CardTypes card = state.drawNew();
                            targetState.replaceCard(card);
                        }
                        return null;
                    })).collect(Collectors.toList());
        }
    },

    KROL(6,1){
        @Override
        public List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target) {
            List<LoveLetterAction> actions = new ArrayList<>();
            LoveLetterAction action = new LoveLetterAction(actor, target, this, state -> {
                        CardTypes actorsCard = state.getLoverState(actor).getCurrentIdentity();
                        CardTypes targetsCard = state.getLoverState(target).getCurrentIdentity();
                        state.getLoverState(actor).swapCard(targetsCard);
                        state.getLoverState(target).swapCard(actorsCard);
                        return null;
                    });
            actions.add(action);
            return actions;
        }
    },

    SZMACIURA(7,1){
        @Override
        public List<LoveLetterAction> getPossibleActions(LoveLetterState gameState) {
            List<LoveLetterAction> actions = new ArrayList<>();
            Lover actor = gameState.getCurrentActor();
            Function<LoveLetterState, Void> action = state -> null;
            actions.add(new LoveLetterAction(actor, actor, this, action));
            return actions;
        }
    },

    SZMULA(8,1){
        @Override
        public List<LoveLetterAction> getPossibleActions(LoveLetterState gameState) {
            List<LoveLetterAction> actions = new ArrayList<>();
            Lover actor = gameState.getCurrentActor();
            Function<LoveLetterState, Void> action = state -> {
                state.inPlayActorCount--;
                state.getLoverState(actor).eliminate();
                return null;
            };
            actions.add(new LoveLetterAction(actor, actor, this, action));
            return actions;
        }
    };

    CardTypes(int value, int number){
        this.value = value;
        this.number = number;
    }

    int value;
    int number;

    public static CardTypes getCardType(int n){
        for(CardTypes type : values()){
            if(type.value == n)
                return type;
        }
        return null;
    }

    @Override
    public List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target) {
        return null;
    }

    @Override
    public int getCardValue(){
        return value;
    }

    @Override
    public CardTypes getThis() {
        return this;
    }


}
