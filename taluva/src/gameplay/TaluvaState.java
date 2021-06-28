package gameplay;

import boardgame.elements.GameState;
import elements.Color;
import elements.board.Board;
import elements.tiles.TileSet;
import gameplay.actions.TaluvaAction;
import mcts.ActorCycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static elements.Color.BROWN;
import static elements.Color.WHITE;

public class TaluvaState implements GameState<TaluvaState, Color, TaluvaAction> {

    Board board;
    TileSet tiles;
    ActorCycle<Color> actors;
    Color currentActor;


    public TaluvaState(TileSet tiles){
        this.tiles = new TileSet(tiles);
        this.board = new Board();

        List<Color> colors = new ArrayList<>();
        colors.add(BROWN);
        colors.add(WHITE);
        actors = new ActorCycle<>(colors);
        currentActor = actors.getNext();
    }

    public TaluvaState(TaluvaState state){
        this.tiles = new TileSet(state.tiles);
        this.board = new Board(state.board);
        this.actors = new ActorCycle<>(actors);
        this.currentActor = state.currentActor;
    }

    @Override
    public Color getCurrentActor() {
        return currentActor;
    }

    @Override
    public TaluvaState copy() {
        return new TaluvaState(this);
    }

    @Override
    public Map<Color, Integer> getEndScore() {
        //TODO
        Map<Color, Integer> endScore = new TreeMap<>();
        endScore.put(WHITE, 0);
        endScore.put(BROWN, 0);
        return endScore;
    }

    @Override
    public List<TaluvaAction> getAllPossibleActions() {
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public TileSet getTiles() {
        return tiles;
    }
}
