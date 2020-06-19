package elements.cards;

import elements.LoveLetterState;
import elements.Lover;
import elements.LoverState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface CardType {

    default List<LoveLetterAction> getPossibleActions(LoveLetterState gameState){
        Lover actor = gameState.getCurrentActor();
        List<Lover> targets = getPossibleTargets(gameState);
        List<LoveLetterAction> actions = new ArrayList<>();
        for(Lover target : targets){
            if(target.equals(actor)){
                actions.add(getSelfTargetedAction(gameState, target));
            }else{
                actions.addAll(getStandardActions(gameState, actor, target));
            }
        }
        return actions;
    }

    default List<Lover> getPossibleTargets(LoveLetterState gameState){
        return gameState.getEnemyTargets();
    }

    default LoveLetterAction getSelfTargetedAction(LoveLetterState gameState, Lover actor){
        return new LoveLetterAction(actor, actor, getThis(), state -> null);
    }

    List<LoveLetterAction> getStandardActions(LoveLetterState gameState, Lover actor, Lover target);

    int getCardValue();

    CardTypes getThis();

}
