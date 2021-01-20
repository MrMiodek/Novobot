package boardgame.customfunctions;

import boardgame.elements.Action;
import boardgame.elements.GameActor;
import boardgame.elements.GameState;
import tree.Node;

public interface ChildFinder<GS extends GameState,GA extends GameActor,A extends Action<GS,GA>,N extends Node<GS,GA,A,N>> {

    /**
     * Function determining most promising leaf given root node
     * @param node root of the search
     * @return most promising leaf
     */
    N getBestChild(N node);

}
