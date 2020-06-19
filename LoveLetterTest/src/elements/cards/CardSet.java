package elements.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardSet {

    public static Random r = new Random();

    public int cardsLeft;

    //TODO replace with Guava multiset
    public List<CardTypes> unKnownCards;

    public CardSet(List<CardTypes> unKnownCards, int cardsLeft){
        this.unKnownCards = new ArrayList<>(unKnownCards);
        this.cardsLeft = cardsLeft;
    }

    public CardSet copy(){
        return new CardSet(unKnownCards, cardsLeft);
    }

    public static CardSet newCardSet(){
        List<CardTypes> cards = new ArrayList<>();
        for(CardTypes cardType : CardTypes.values()){
            for(int i = 0; i < cardType.number; i++)
                cards.add(cardType);
        }
        Collections.shuffle(cards);
        return new CardSet(cards, cards.size());
    }

    public CardTypes drawOne(){
        cardsLeft--;
        return unKnownCards.remove(r.nextInt(unKnownCards.size()));
    }

    public void removeCard(CardTypes card){
        unKnownCards.remove(card);
    }

    public void lowerCardCount(){
        cardsLeft--;
    }

    public void collapse(){
        while(cardsLeft != unKnownCards.size()){
            unKnownCards.remove(r.nextInt(unKnownCards.size()));
        }
    }

    @Override
    public String toString() {
        return "Cards left "+cardsLeft;
    }
}
