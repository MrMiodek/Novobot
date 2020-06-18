package elements;

import boardgame.elements.GameConfig;

public class TicTacToeConfig implements GameConfig<TicTacToeState> {

    @Override
    public TicTacToeState getInitialGameState() {
        return new TicTacToeState();
    }

}
