package parser.Tree;

// import java.util.*;

public class BinaryTreeVisualizer {
    //
    // public static void printTree(TreeNode root) {
    // int maxLevel = maxLevel(root);
    // printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    // }
    //
    // private static void printNodeInternal(List<TreeNode> nodes, int level, int
    // maxLevel) {
    // if (nodes.isEmpty() || isAllElementsNull(nodes))
    // return;
    // int floor = maxLevel - level;
    // int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0))) + 2;
    // int firstSpaces = (int) Math.pow(2, (floor + 2)) - 1;
    // int betweenSpaces = (int) Math.pow(2, (floor + 2.5)) - 1;
    //
    // printWhitespaces(firstSpaces);
    //
    // List<TreeNode> newNodes = new ArrayList<>();
    //
    // for (TreeNode node : nodes) {
    // if (node != null) {
    // String nodeValue = node.getDisplayValue();
    // System.out.print(nodeValue);
    // newNodes.add(node.left);
    // newNodes.add(node.right);
    //
    // // int adjust = betweenSpaces - (nodeValue.length() - 1);
    // // printWhitespaces(Math.max(adjust, 0));
    // } else {
    // newNodes.add(null);
    // newNodes.add(null);
    // System.out.print(" ");
    // }
    // printWhitespaces(betweenSpaces);
    // }
    // System.out.println();
    // for (int i = 1; i <= edgeLines; i++) {
    // for (TreeNode node : nodes) {
    // printWhitespaces(firstSpaces - i);
    // if (node == null) {
    // printWhitespaces(edgeLines + edgeLines + i + 1);
    // continue;
    // }
    // if (node.left != null)
    // System.out.print("/");
    // else
    // printWhitespaces(1);
    // printWhitespaces(i + i - 1);
    // if (node.right != null)
    // System.out.print("\\");
    // else
    // printWhitespaces(1);
    // printWhitespaces(edgeLines + edgeLines - i);
    // }
    // System.out.println();
    // }
    // printNodeInternal(newNodes, level + 1, maxLevel);
    // }
    //
    // private static void printWhitespaces(int count) {
    // for (int i = 0; i < count; i++)
    // System.out.print(" ");
    // }
    //
    // private static int maxLevel(TreeNode node) {
    // if (node == null)
    // return 0;
    // return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    // }
    //
    // private static boolean isAllElementsNull(List<TreeNode> list) {
    // for (TreeNode node : list) {
    // if (node != null)
    // return false;
    // }
    // return true;
    // }

    public static void print(TreeNode root) {
        print(root, "", true);
    }

    private static void print(TreeNode node, String prefix, boolean isTail) {
        if (node == null)
            return;

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getDisplayValue());

        String newPrefix = prefix + (isTail ? "    " : "│   ");

        if (node.left != null || node.right != null) {
            if (node.right != null) {
                print(node.right, newPrefix, node.left == null);
            }
            if (node.left != null) {
                print(node.left, newPrefix, true);
            }
        }
    }
}
