package parser.Tree;

/**
 * Internal node representing a binary arithmetic operation in an expression tree.
 * 
 * This node stores an operator (+, -, *, /) and maintains references to left
 * and right subtrees. During evaluation, it recursively evaluates both subtrees
 * and applies the operator to produce the result.
 * 
 * Supported operators:
 * <ul>
 *   <li>+: Addition</li>
 *   <li>-: Subtraction</li>
 *   <li>*: Multiplication</li>
 *   <li>/: Division</li>
 * </ul>
 * 
 * @author Ganesh
 * @version 1.0
 * @see TreeNode
 */
public class OperationNode extends TreeNode {
    /** The operator (+, -, *, /) to apply to the operands */
    private String op;

    /**
     * Constructs an OperationNode with the given operator and subtrees.
     * 
     * @param op The operator string (+, -, *, /)
     * @param left The left operand subtree
     * @param right The right operand subtree
     */
    public OperationNode(String op, TreeNode left, TreeNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /**
     * Recursively evaluates both subtrees and applies the binary operation.
     * 
     * Evaluates the left and right subtrees, then applies the operator.
     * Returns 0 if the operator is not recognized.
     * 
     * @return The result of applying the operator to the evaluated subtrees
     */
    @Override
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

    /**
     * Returns the operator symbol for display in tree visualization.
     * 
     * @return The operator string (+, -, *, or /)
     */
    @Override
    public String getDisplayValue() {
        return this.op;
    }
}
