package elements;

import boardgame.elements.GameConfig;
import elements.gamestate.NanahoshiState;

public class NanahoshiConfig implements GameConfig<NanahoshiState> {

    @Override
    public NanahoshiState getInitialGameState() {
        Alliance startingActor = Alliance.getRandom();
        return new NanahoshiState(startingActor);
    }

}
