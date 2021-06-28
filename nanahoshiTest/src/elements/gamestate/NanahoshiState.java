package elements.gamestate;

import boardgame.elements.GameState;
import elements.Alliance;
import elements.actions.NanahoshiAction;
import elements.Board;
import elements.tiles.Ship;
import elements.tiles.Tile;
import elements.tiles.TileSet;

import java.util.*;
import java.util.stream.Collectors;

public class NanahoshiState implements GameState<NanahoshiState, Alliance, NanahoshiAction> {

    private TileSet tileSet;
    private Board board;
    private Alliance previousActor;
    private Alliance currentActor;
    private Map<Alliance, ActorState> actorState;
    public List<NanahoshiAction> history;
    private List<NanahoshiAction> possibleActions;
    private NanahoshiAction lastAction;

    //TODO wersja do X punktów
    public NanahoshiState(Alliance startingActor){
        this.tileSet = new TileSet();
        this.board = new Board(tileSet);
        this.currentActor = startingActor;
        actorState = new TreeMap<>();
        for(Alliance side : Alliance.values()){
            actorState.put(side, new ActorState());
        }
        this.lastAction = NanahoshiAction.GAME_START;
        this.history = new ArrayList<>();
        history.add(lastAction);
    }

    private NanahoshiState(NanahoshiState gameState){
        this.tileSet = new TileSet(gameState.tileSet);
        this.board = new Board(tileSet);
        this.previousActor = gameState.previousActor;
        this.currentActor = gameState.currentActor;
        actorState = new TreeMap<>();
        for(Alliance side : Alliance.values()){
            ActorState state = gameState.actorState.get(side);
            actorState.put(side, new ActorState(state));
        }
        this.lastAction = gameState.lastAction;
        this.history = new ArrayList<>(gameState.history);
        if(gameState.possibleActions != null){
            this.possibleActions = new ArrayList<>(gameState.possibleActions);
        }
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public Alliance getCurrentActor() {
        return currentActor;
    }

    @Override
    public NanahoshiState copy() {
        return new NanahoshiState(this);
    }

    @Override
    public Map<Alliance, Integer> getEndScore() {
        //TODO posiible endScoreFunction from config
        if( actorState.values().stream()
                .anyMatch(state -> state.getSmallPoints()>=7)
                || history.size()>200
                || getAllPossibleActions().isEmpty()
                || tileSet.isBadLuck()){
            Map<Alliance, Integer> endScore = new TreeMap<>();
            for(Alliance side : Alliance.values()){
                endScore.put(side, actorState.get(side).getSmallPoints());
            }
            return endScore;
        }else {
            return null;
        }
    }

    @Override
    public List<NanahoshiAction> getAllPossibleActions() {
        if(possibleActions==null){
            possibleActions = lastAction.getPossibleActions(this);
        }
        return possibleActions;
    }

    public Iterator<Tile> getOnBoardTiles(){
       return tileSet.getKnownTiles().stream()
               .filter(Tile::isOnBoard)
               .collect(Collectors.toList())
               .iterator();
    }

    public Iterator<Ship> getConcealedShips(){
        return tileSet.getConcealedShips().iterator();
    }

    public TileSet getTileSet(){
        return tileSet;
    }

    public void swapActive(){
        previousActor = currentActor;
        currentActor = currentActor.getOpponent();
    }

    public void setLastAction(NanahoshiAction lastAction) {
        this.lastAction = lastAction;
        this.history.add(lastAction);
        this.possibleActions = null;
    }

    public Map<Alliance, ActorState> getActorState() {
        return actorState;
    }

    public NanahoshiAction getLastAction(){
        return lastAction;
    }

}
