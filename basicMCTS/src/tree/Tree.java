package tree;

/**
 * Basic Tree implementation
 */
public class Tree<N extends Node> {
    private N root;

    public Tree(N root) {
        this.root = root;
    }

    public Tree() {
        this.root = (N) new Node();
    }

    public N getRoot() {
        return root;
    }

}
