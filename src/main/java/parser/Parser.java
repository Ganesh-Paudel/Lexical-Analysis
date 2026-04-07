package parser;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Lexer.Token.Tokens;
import Utils.Helpers;
import Utils.LexemeData;
import parser.Utils.InfixToPostfix;
import parser.Tree.*;

public class Parser {

    private ArrayList<TreeNode> parseTrees = new ArrayList<>();

    public ArrayList<TreeNode> parseAll(ArrayList<LexemeData> tokenizedData) {
        ArrayList<LexemeData> lineBuffer = new ArrayList<>();

        for (LexemeData token : tokenizedData) {

            if (token.getToken() == Tokens.SEMICOLON || token.getToken() == Tokens.EOF) {
                processLine(lineBuffer);
                lineBuffer.clear();
            } else {
                lineBuffer.add(token);
            }
        }

        return parseTrees;
    }

    private void processLine(ArrayList<LexemeData> statement) {
        if (statement.isEmpty()) {
            return;
        }

        TreeNode root;

        if (statement.size() > 1 && statement.get(0).getToken() == Tokens.IDENTIFIER
                && statement.get(1).getToken() == Tokens.ASSIGN_OP) {
            String variableName = statement.get(0).getValue();

            ArrayList<LexemeData> rightSide = new ArrayList<>(statement.subList(2, statement.size()));
            ArrayList<LexemeData> postFix = InfixToPostfix.infixToPostfix(rightSide);
            root = new AssignmentNode(variableName, buildTree(postFix));
            System.out.print("For Statement: " + Helpers.getStringFromLexemeData(statement));
        } else {
            ArrayList<LexemeData> postFix = InfixToPostfix.infixToPostfix(statement);
            root = buildTree(postFix);
            System.out.print("For Statement: " + Helpers.getStringFromLexemeData(postFix));
        }
        parseTrees.add(root);
        System.out.println("Parse Tree for the above statement: ");
        BinaryTreeVisualizer.print(root);
        System.out.println("Result: " + root.evaluate());

    }

    private TreeNode buildTree(ArrayList<LexemeData> postFixData) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        for (LexemeData token : postFixData) {
            if (token.getToken() == Tokens.INT || token.getToken() == Tokens.FLOAT) {
                double value = Double.parseDouble(token.getValue());
                stack.push(new NumberNode(value));
            } else if (token.getToken() == Tokens.IDENTIFIER) {
                stack.push(new VariableNode(token.getValue()));
            } else {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                stack.push(new OperationNode(token.getValue(), left, right));
            }
        }
        return stack.pop();
    }

}
