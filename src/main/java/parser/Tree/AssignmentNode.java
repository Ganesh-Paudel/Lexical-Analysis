package parser.Tree;

import parser.Memory;

public class AssignmentNode extends TreeNode {
    private String varName;

    public AssignmentNode(String varName, TreeNode expression) {
        this.varName = "=";
        this.left = new VariableNode(varName);
        this.right = expression;
    }

    @Override
    public double evaluate() {
        double val = this.right.evaluate();
        Memory.set(((VariableNode) left).getDisplayValue(), val);
        return val;

    }

    @Override
    public String getDisplayValue() {
        return this.varName;
    }

}
