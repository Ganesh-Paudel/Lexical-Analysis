package parser.Tree;

import parser.Memory;

public class AssignmentNode extends TreeNode {
    private String varName;
    private TreeNode expression;

    public AssignmentNode(String varName, TreeNode expression) {
        this.varName = varName;
        this.expression = expression;
    }

    public double evaluate() {
        double val = expression.evaluate();
        Memory.set(varName, val);
        return val;

    }
}
