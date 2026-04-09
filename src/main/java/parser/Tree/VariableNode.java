package parser.Tree;

import parser.Memory;

/**
 * Leaf node representing a variable reference in an expression tree.
 * 
 * This node type stores a variable name and looks up its value in the Memory
 * class during evaluation. It has no children and evaluates to the current
 * value of the named variable.
 * 
 * @author Ganesh
 * @version 1.0
 * @see TreeNode
 * @see Memory
 */
public class VariableNode extends TreeNode {
    /** The name of the variable referenced by this node */
    private String name;

    /**
     * Constructs a VariableNode with the given variable name.
     * 
     * @param name The name of the variable to reference
     */
    public VariableNode(String name) {
        this.name = name;
    }

    /**
     * Returns the value of the variable from Memory.
     * 
     * Looks up the variable by name in the Memory class. Prints a warning if
     * the variable has not been defined (assigned) yet.
     * 
     * @return The current value of the variable
     */
    @Override
    public double evaluate() {
        return Memory.get(name);
    }

    /**
     * Returns the variable name for display in tree visualization.
     * 
     * @return The name of the variable stored in this node
     */
    @Override
    public String getDisplayValue() {
        return this.name;
    }
}
