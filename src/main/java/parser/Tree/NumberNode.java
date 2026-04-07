package parser.Tree;

public class NumberNode extends TreeNode {
    private double value;

    public NumberNode(double value) {
        this.value = value;
    }

    public double evaluate() {
        return value;
    }
}
