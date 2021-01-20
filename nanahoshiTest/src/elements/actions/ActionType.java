package elements.actions;

import elements.gamestate.NanahoshiState;

import java.util.List;

public interface ActionType {

  List<NanahoshiAction> getPossibleActions(NanahoshiState gameState);

}
