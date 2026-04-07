package parser.Tree;

import parser.Memory;

public class VariableNode extends TreeNode {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    @Override
    public double evaluate() {
        return Memory.get(name);
    }

    @Override
    public String getDisplayValue() {
        return this.name;
    }
}
