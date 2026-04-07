package parser.Tree;

public class OperationNode extends TreeNode {
    private String op;
    private TreeNode left, right;

    public OperationNode(String op, TreeNode left, TreeNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public double evaluate() {
        switch (op) {
            case "+":
                return left.evaluate() + right.evaluate();
            case "-":
                return left.evaluate() - right.evaluate();
            case "*":
                return left.evaluate() * right.evaluate();
            case "/":
                return left.evaluate() / right.evaluate();
            default:
                return 0;
        }
    }
}
