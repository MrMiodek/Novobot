package elements;

import boardgame.elements.GameConfig;
import elements.gamestate.NanahoshiState;

import java.util.ArrayList;
import java.util.List;

public class NanahoshiConfig implements GameConfig<NanahoshiState, Alliance> {

    @Override
    public NanahoshiState getInitialGameState() {
        Alliance startingActor = Alliance.getRandom();
        return new NanahoshiState(startingActor);
    }

    @Override
    public List<Alliance> getListOfActors() {
        ArrayList<Alliance> actors = new ArrayList<>();
        actors.add(Alliance.NAVY);
        actors.add(Alliance.PIRATES);
        return actors;
    }

}
