package parser.Tree;

/**
 * Abstract base class for all nodes in an expression tree.
 * 
 * This class defines the common interface for all node types in a binary
 * expression tree. Concrete subclasses implement specific behaviors for
 * leaf nodes (NumberNode, VariableNode) and internal nodes (OperationNode, AssignmentNode).
 * 
 * Each node maintains left and right subtree references for building binary trees.
 * 
 * @author Ganesh
 * @version 1.0
 * @see NumberNode
 * @see VariableNode
 * @see OperationNode
 * @see AssignmentNode
 */
public abstract class TreeNode {
  /** Reference to left subtree (if any) */
  public TreeNode left = null;
  /** Reference to right subtree (if any) */
  public TreeNode right = null;

  /**
   * Evaluates this node and returns its numeric value.
   * 
   * The evaluation semantics depend on the node type:
   * <ul>
   *   <li>NumberNode: Returns the constant value</li>
   *   <li>VariableNode: Looks up and returns the variable value from Memory</li>
   *   <li>OperationNode: Recursively evaluates subtrees and applies the operation</li>
   *   <li>AssignmentNode: Evaluates RHS, stores result in Memory, returns value</li>
   * </ul>
   * 
   * @return The numeric value represented by this node and its subtrees
   */
  public abstract double evaluate();

  /**
   * Returns the display value for tree visualization.
   * 
   * Used by BinaryTreeVisualizer to render the tree structure. Each node type
   * returns a string representing its semantic meaning (e.g., operator symbol,
   * number value, variable name).
   * 
   * @return A string representation for display in the tree visualization
   */
  public abstract String getDisplayValue();
}
