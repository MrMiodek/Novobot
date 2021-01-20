package montecarlo;

import boardgame.customfunctions.ChildFinder;
import tree.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Basic implementation of UCT (Upper Confidence bounds applied to Trees)
 */
public class BasicUCT implements ChildFinder {

    /**
     * Calculates UCT value of given Node
     * @param totalVisit total number of parent node visits
     * @param nodeWinScore sum of node win score (usually number of wins)
     * @param nodeVisit number of node visits
     * @return UCT value of given node
     */
    public double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {

        return (nodeWinScore / (double) nodeVisit) +
                Math.sqrt(2 * Math.log(totalVisit) / (double) nodeVisit);
    }

    /**
     * Finds child Node with best UCT score
     * @param node Node for which we find best scoring child
     * @return best scoring child
     */
    public Node getBestChild(Node node) {
        int parentVisit = node.getVisitCount();
        List<Node> children = node.getChildArray();
        return Collections.max(
                children,
                Comparator.comparing(c ->
                        uctValue(parentVisit, c.getWinScore(), c.getVisitCount())));
    }


}
