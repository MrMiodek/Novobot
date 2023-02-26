package elements;

import boardgame.elements.GameConfig;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeConfig implements GameConfig<TicTacToeState, Sign> {

    @Override
    public TicTacToeState getInitialGameState() {
        return new TicTacToeState();
    }

    @Override
    public List<Sign> getListOfActors() {
        ArrayList<Sign> actors = new ArrayList<>();
        actors.add(Sign.X);
        actors.add(Sign.Y);
        return actors;
    }

}
