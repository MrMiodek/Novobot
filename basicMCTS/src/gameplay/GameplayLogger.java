package gameplay;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameState;

import java.util.Map;

/**
 * Class for gameplay logs settings. This class is meant to be extended or its methods overwritten based on any needs.
 */
public class GameplayLogger
        <GS extends GameState<GS, GA, AC>,
                AC extends Action<GS,GA>,
                GA extends GameActor>{
    public void logBeforeGame(GS gameState){
        System.out.println(gameState);
    }

    public void logBeforeMove(GS gameState, AC action){}

    public void logAfterMove(GS gameState, AC action){
        System.out.println(gameState);
    }

    public void logAfterGame(GS gameState){
        Map<GA, Integer> endScore = gameState.getEndScore();
        for(Map.Entry<GA, Integer>  entry : endScore.entrySet()){
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
    }

}
