package parser.Tree;

public class NumberNode extends TreeNode {
    private double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(this.value);
    }
}
