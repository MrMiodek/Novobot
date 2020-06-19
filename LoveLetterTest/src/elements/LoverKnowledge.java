package elements;

import elements.cards.CardType;
import elements.cards.CardTypes;

import java.util.Map;
import java.util.TreeMap;

public class LoverKnowledge {

    Map<Lover, Map<CardTypes,Double>> loverKnowledge = new TreeMap<>();

    public void resetInfoAbout(Lover lover){

    }

    public void loverHas(Lover lover, CardTypes card){
        Map<CardTypes, Double> map = new TreeMap<>();
        map.put(card, 1.0);
        loverKnowledge.put(lover, map);
    }

    public void loverHasnt(Lover lover, CardTypes card){
        loverKnowledge.get(lover).remove(card);
    }

    public void loverSurvived(Lover lover, CardType card){

    }

    public void loversTie(Lover actor, Lover target){

    }

}
