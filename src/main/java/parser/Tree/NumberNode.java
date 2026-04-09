package parser.Tree;

/**
 * Leaf node representing a numeric literal in an expression tree.
 * 
 * This node type stores and returns constant numeric values (integers or
 * floating-point numbers) during expression evaluation. It has no children
 * and always evaluates to its stored value.
 * 
 * @author Ganesh
 * @version 1.0
 * @see TreeNode
 */
public class NumberNode extends TreeNode {
    /** The constant numeric value of this node */
    private double value;

    /**
     * Constructs a NumberNode with the given numeric value.
     * 
     * @param value The constant value represented by this node
     */
    public NumberNode(double value) {
        this.value = value;
    }

    /**
     * Returns the constant value of this node.
     * 
     * @return The numeric value stored in this node
     */
    @Override
    public double evaluate() {
        return value;
    }

    /**
     * Returns the string representation of the numeric value.
     * 
     * @return The number as a string (e.g., "42", "3.14")
     */
    @Override
    public String getDisplayValue() {
        return String.valueOf(this.value);
    }
}
