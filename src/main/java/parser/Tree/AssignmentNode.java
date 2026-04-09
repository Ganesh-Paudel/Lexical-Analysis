package parser.Tree;

import parser.Memory;

/**
 * Root node representing an assignment statement in an expression tree.
 * 
 * This node represents a variable assignment (variable = expression). It stores
 * the variable name as the left child (VariableNode) and the expression to be
 * evaluated and assigned as the right child. When evaluated, it computes the
 * right-side expression and stores the result in Memory under the variable name.
 * 
 * Assignment statement format:
 * <pre>
 *      =
 *     / \
 *    x   expr
 * </pre>
 * 
 * @author Ganesh
 * @version 1.0
 * @see TreeNode
 * @see VariableNode
 * @see Memory
 */
public class AssignmentNode extends TreeNode {
    /** Display value for this node (always "=") */
    private String varName;

    /**
     * Constructs an AssignmentNode for the given variable name and expression.
     * 
     * The left child is set to a VariableNode with the variable name, and the
     * right child is set to the expression tree to be evaluated.
     * 
     * @param varName The name of the variable to assign to
     * @param expression The expression tree whose value will be assigned
     */
    public AssignmentNode(String varName, TreeNode expression) {
        this.varName = "=";
        this.left = new VariableNode(varName);
        this.right = expression;
    }

    /**
     * Evaluates the right-side expression and stores the result in Memory.
     * 
     * Evaluates the expression tree on the right side and assigns the computed
     * value to the variable named in the left child node. Returns the assigned
     * value.
     * 
     * @return The value that was assigned to the variable
     */
    @Override
    public double evaluate() {
        double val = this.right.evaluate();
        Memory.set(((VariableNode) left).getDisplayValue(), val);
        return val;

    }

    /**
     * Returns the assignment operator symbol for display.
     * 
     * @return The string "=" representing the assignment operator
     */
    @Override
    public String getDisplayValue() {
        return this.varName;
    }

}
