package parser.Tree;

public abstract class TreeNode {
  public TreeNode left = null;
  public TreeNode right = null;

  public abstract double evaluate();

  public abstract String getDisplayValue();
}
