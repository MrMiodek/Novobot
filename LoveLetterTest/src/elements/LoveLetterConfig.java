package elements;

import boardgame.elements.GameConfig;
import elements.cards.CardSet;
import elements.cards.CardTypes;

import java.util.ArrayList;
import java.util.List;

public class LoveLetterConfig implements GameConfig<LoveLetterState> {

    int playerCount;

    public LoveLetterConfig(int playerCount){
        this.playerCount = playerCount;
    }

    @Override
    public LoveLetterState getInitialGameState() {
        CardSet cards = CardSet.newCardSet();
        List<CardTypes> visibleCards = new ArrayList<>();
        if(playerCount == 2) {
            for(int i = 0; i < 3 ; i++)
                visibleCards.add(cards.drawOne());
        }
        return new LoveLetterState(playerCount, visibleCards, cards);
    }

}
