package parser.Tree;

import parser.Memory;

public class VariableNode extends TreeNode {
    private String name;

    public VariableNode(String name) {
        this.name = name;
    }

    public double evaluate() {
        return Memory.get(name);
    }
}
